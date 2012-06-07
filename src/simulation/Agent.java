/**
 * 
 */
package simulation;

/**
 * @author Patrick
 *
 */

import java.io.IOException;

import org.apache.log4j.Logger;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.geo.PointMoveTo;
import simulation_berechnungen.Auftraegs_Fahrkarten_Erzeugen;
import simulation_berechnungen.Reporting_Erzeugen;
import simulation_daten.Array_Auftraege_Status;
import simulation_daten.Array_Fahrkarten;
import simulation_koordinaten.Orte_Koordinaten;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

/**
 * @param args
 */

public class Agent implements Steppable {
	
	private static Logger logger = Logger.getLogger( Agent.class );
	private static final long serialVersionUID = -1113018274619047013L;
	public int vorgang = 0;
	
	
	
	// Allgemeine Schiffsdaten
	public int schiffs_id;
	public int geschw_revierfahrt;
	public int geschw_marschfahrt;
	public String heimathafen;
	public String schiff_name;
	public Point startpunkt;
	
	// Auftragsdaten
	public int Auftragsnummer;
	public Boolean schiff_hat_keinen_auftrag_mehr = false;
	public int laenge_der_fahrkarte;
	public Boolean schiff_ist_beschaeftigt = false;
	public Object freie_auftragsnummer;
	public int wartezeit_schiff;
	public Boolean schiff_wartet = false;
	public int umschlags_zeit;
	public int aufenthalt_zeit = 0;
	
	
	// Daten für die Bewegung
	public int akutelle_reihe_auf_fahrkarte = 0;
	public int akutelle_geschwindigkeit;
	public int geschwindigkeit_in_minuten;
	public Point standort_punkt = null;
	public Coordinate standort_koordinate = new Coordinate();
	public PointMoveTo pointMoveTo = new PointMoveTo();
	public Array_Fahrkarten<Object> fahrweg_fahrkarte;
	public Array_Auftraege_Status array_auftraege_status= new Array_Auftraege_Status(); // Auftragsliste anlegen incl Status
	
	
	/**
	 * Init Schiffs Agent mit allen Allgemeinen Daten
	 */
	public Agent(int id, String schiffs_name, String Heimathafen,String geschwindigkeit_revierfahrt,String geschwindigkeit_marschfahrt)
	{
		geschw_revierfahrt = Integer.parseInt(geschwindigkeit_revierfahrt);
		geschw_marschfahrt = Integer.parseInt(geschwindigkeit_marschfahrt);
		schiff_name = schiffs_name;
		schiffs_id = id;
		heimathafen = Heimathafen;
	}

	/**
	 * Positionen setzten
	 */	
	public void setLocation(Point p) {
		standort_punkt = p;
	}

	public Geometry getGeometry() {
		return standort_punkt;
	}
	

	/**
	 * Auf gehts 
	 */		
	public void step(SimState state) {
		
		vorgang = vorgang + 1;
		logger.debug("Vorgang: " + vorgang);
		// Aktuelle Koordinate setzten init
		if (vorgang == 1) {
			startpunkt =standort_punkt;
			standort_koordinate.x = standort_punkt.getX();
			standort_koordinate.y = standort_punkt.getY();
		}
		
		// Debug nachricht auswählen
		longdebug_nachricht();
		// shortdebug_nachricht();
				
		gucken_ob_schiff_warten_muss();
		

		if (schiff_wartet == false) {
			logger.debug("schiff_wartet: " +schiff_wartet);
			logger.debug("schiff_ist_beschaeftigt: " +schiff_ist_beschaeftigt);
			if (schiff_ist_beschaeftigt == false) {
				fahrtkarte_zuweisung();
			}
			
			
			if (!freie_auftragsnummer.equals(false)	&& !freie_auftragsnummer.equals("Warten")) {
				if (geschwindigkeit_in_minuten == 0) {
					logger.debug("Schiff darf sich bewegen");
					fahrkarte_ablaufen();
				} else {
					logger.debug("Schiff darf sich nicht bewegen erst in " +geschwindigkeit_in_minuten +" minuten");
					geschwindigkeit_in_minuten = geschwindigkeit_in_minuten - 1;
				}
			} else {
				if (schiff_hat_keinen_auftrag_mehr == false	&& freie_auftragsnummer.equals(false))
				{
					logger.debug("Schiff hat keinen Auftrag mehr");
					schiff_hat_keinen_auftrag_mehr = true;
					Reporting_Erzeugen.set_gesamt_zeit(schiffs_id, vorgang,	aufenthalt_zeit); // Gesamtzeit setzten
				}
				
				if (Reporting_Erzeugen.gucke_ob_alle_reports_fertig_sind() == true)
				{
					
					logger.debug("Reporting_Erzeugen");
					
					state.finish();
					
					
					if (Simulation.getGameover()==false ){
					try {
						Reporting_Erzeugen.printreport();
					} catch (IOException e) {
						System.out.println("Fehler ! Report konnte nicht erzeugt werden");
						e.printStackTrace();
					}
					Simulation.setGameover(true);
					}
					
					
					
				}		
			}
		}
		
		
		
		
		
		
		zeiten_brechnen(); // Zeit im Hafen und Zeiten auf See
		bewege_dich();
		
		
		
		
		
	}
	
	public void zeiten_brechnen(){
		
		
		logger.debug("Zeit wir berechnet");
		
		Point emden = Orte_Koordinaten.getPointAt(2);
		Point norddeich = Orte_Koordinaten.getPointAt(0);
		logger.debug("umschlags_zeit: "+umschlags_zeit+" | akutelle_geschwindigkeit: "+ akutelle_geschwindigkeit +" | standort_punkt"+ standort_punkt +" | ");
		
		if (umschlags_zeit!= 0 || akutelle_geschwindigkeit== 0 && (standort_punkt.equals(emden) || standort_punkt.equals(norddeich) || standort_punkt.equals(startpunkt) ))
		{
			Reporting_Erzeugen.set_zeit_im_hafen(schiffs_id, 1);
			logger.debug(schiffs_id + " set_zeit_im_hafen");
			if (umschlags_zeit==0){
				Reporting_Erzeugen.set_wartezeit(schiffs_id, 1);
				logger.debug(schiffs_id + " set_wartezeit");	
			}
			
			
		}else
		{
			Reporting_Erzeugen.set_zeit_auf_see(schiffs_id, 1);
			logger.debug(schiffs_id + " set_zeit_auf_see");	
		}
		
		
		
		
	}
	
	
	
	
	
	
	public void fahrtkarte_zuweisung(){
		
		if (heimathafen.equals("Emd")) {
			logger.debug("Schiff kommt aus Emden");
			freie_auftragsnummer = Array_Auftraege_Status.get_freie_Auftragsnummer_Emden(vorgang);
			logger.debug("Schiff bekommt Auftragsnummer" +freie_auftragsnummer);
			
			if (!freie_auftragsnummer.equals(false) && !freie_auftragsnummer.equals("Warten")) {
				logger.debug("Schiff bekommt neue Fahrkarte");
				fahrweg_fahrkarte = Auftraegs_Fahrkarten_Erzeugen.getfahrkarte((Integer) freie_auftragsnummer,schiffs_id, geschw_revierfahrt,geschw_marschfahrt, heimathafen);
				umschlags_zeit = 30; //Umschlagszeit 
				Auftragsnummer = (Integer) freie_auftragsnummer;
				Array_Auftraege_Status.setAuftraege_emden_erledigt(Auftragsnummer);
				schiff_ist_beschaeftigt = true;	
				akutelle_reihe_auf_fahrkarte = 0;
			}

		} else if (heimathafen.equals("Nor")) {
			logger.debug("Schiff kommt aus Nordeich");
			freie_auftragsnummer = Array_Auftraege_Status.get_freie_Auftragsnummer_Norddeich(vorgang);
			logger.debug("Schiff bekommt Auftragsnummer" +freie_auftragsnummer);
			if (!freie_auftragsnummer.equals(false)  && !freie_auftragsnummer.equals("Warten")) {
				logger.debug("Schiff bekommt neue Fahrkarte");
				fahrweg_fahrkarte = Auftraegs_Fahrkarten_Erzeugen.getfahrkarte((Integer) freie_auftragsnummer,schiffs_id, geschw_revierfahrt,geschw_marschfahrt, heimathafen);
				umschlags_zeit = 30; // Umschlagszeit
				Auftragsnummer = (Integer) freie_auftragsnummer;
				Array_Auftraege_Status.setAuftraege_norddeich_erledigt(Auftragsnummer);
				schiff_ist_beschaeftigt = true;	
				akutelle_reihe_auf_fahrkarte = 0;
			}
			
		}
		
	}
	
	public void fahrkarte_ablaufen(){
	
		laenge_der_fahrkarte = fahrweg_fahrkarte.getNumRows() - 1;
		
	

		if (akutelle_reihe_auf_fahrkarte != laenge_der_fahrkarte) {
			
			standort_koordinate.x = (Double) fahrweg_fahrkarte.get(akutelle_reihe_auf_fahrkarte, 0);
			standort_koordinate.y = (Double) fahrweg_fahrkarte.get(akutelle_reihe_auf_fahrkarte, 1);
			
			// Geschwindigkeit
			akutelle_geschwindigkeit=(Integer) fahrweg_fahrkarte.get(akutelle_reihe_auf_fahrkarte,3);
			geschwindigkeit_dauer_fuer_einen_step(); 
			// TODO in Double umsetzten
			double seemeile = 0.5;
			Reporting_Erzeugen.seemeilen(schiffs_id, seemeile);
			// Wartezeit
			wartezeit_schiff= wartezeit_schiff +(Integer) fahrweg_fahrkarte.get(akutelle_reihe_auf_fahrkarte,4);
			aufenthalt_zeit = aufenthalt_zeit + wartezeit_schiff;
			
			
			akutelle_reihe_auf_fahrkarte = akutelle_reihe_auf_fahrkarte + 1;
			
		} else {
			if (heimathafen.equals("Emd")) {
				akutelle_geschwindigkeit = 0;
				Array_Auftraege_Status.setAuftraege_emden_erledigt((Integer) Auftragsnummer);
			} else if (heimathafen.equals("Nor")) {
				akutelle_geschwindigkeit = 0;
				Array_Auftraege_Status.setAuftraege_norddeich_erledigt((Integer) Auftragsnummer);
			}

			schiff_ist_beschaeftigt = false;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
	
	
	public void gucken_ob_schiff_warten_muss(){
		/**
		 *  Wenn Wartezeit und Umschlagszeit ungleich 0 dann muss nicht gewartete werden sonst wird eine der Variablen heruntergezählt
		 */	
		logger.debug("Gucken ob Schiff noch Warten muss");
		
		schiff_wartet = false;
		
		if (wartezeit_schiff!=0 || umschlags_zeit!=0)
		{
		if (wartezeit_schiff!=0){wartezeit_schiff = wartezeit_schiff -1;}
		if (umschlags_zeit!=0 ){umschlags_zeit = umschlags_zeit -1;}
		schiff_wartet = true;
		}
		
		logger.debug("Schiff muss warten: "+ schiff_wartet);
		
		
		
		
	}
	
	public void longdebug_nachricht(){
		int restminuten =  vorgang % 60;
		int stunden = (vorgang / 60);     
		
		
		
		
		logger.info("Zeit: "+stunden+" Stunden "+restminuten+ " Minuten");
		logger.info("*---------------------------------------------------------*");
		
		logger.info("Vorgang " + vorgang + " wird gestartet mit Schiff: " + schiffs_id);
		logger.info("Schiffs Id: " + schiffs_id + " Schiffs Name: "
				+ schiff_name + " Heimathafen: " + heimathafen
				+ " Geschwindigkeit Revierfahrt: " + geschw_revierfahrt
				+ " Geschwindigkeit Marschfahrt: " + geschw_marschfahrt
				+ " Schiff ist beschaeftigt: " + schiff_ist_beschaeftigt);
		
		logger.info("Schiff muss noch: " +wartezeit_schiff+ " warten");
		logger.info("Schiff muss noch: " +umschlags_zeit + " umschlagen");
		logger.info("Schiff fährt mit: " +akutelle_geschwindigkeit+ " Knoten");
		
		logger.info("Schiff hat Auftrag: "+ freie_auftragsnummer+ " bekommen");
	
		logger.info("Auftrags Koordinate: " + akutelle_reihe_auf_fahrkarte+ " von " + laenge_der_fahrkarte);
		
		logger.info("Akutelle Koordinate: "+standort_koordinate);
		logger.info("Zeit bis zum nächsten Koordinatensprung: " +geschwindigkeit_in_minuten);
		logger.info("Zeit auf See: " + Reporting_Erzeugen.get_zeit_auf_see(schiffs_id) +"min");
		logger.info("Zeit im Hafen: " + Reporting_Erzeugen.get_zeit_im_hafen(schiffs_id)+ "min");
		logger.info("Zurückgelegte Seemeilen: " +Reporting_Erzeugen.get_seemeilen(schiffs_id)+ "sm");
		logger.info("Wartezeit :" + Reporting_Erzeugen.get_wartezeit(schiffs_id));
		
		logger.info("Aufenthaltszeit: "+ aufenthalt_zeit +"min");
		logger.info("*---------------------------------------------------------*");
	}
	
	
	
	public void shortdebug_nachricht(){
		int restminuten =  vorgang % 60;
		int stunden = (vorgang / 60); 

        logger.info("Zeit: "+stunden+" Stunden "+restminuten+ " Minuten");
		logger.info("*---------------------------------------------------------*");
		logger.info("Vorgang " + vorgang + " wird gestartet mit Schiff: " + schiffs_id);
		logger.info("Schiff hat Auftrag: "+ freie_auftragsnummer+ " bekommen");
		logger.info("*---------------------------------------------------------*");
		
	}
	
	
	
	public void bewege_dich(){
		
		
		logger.debug("Schiff bewegt sich");
		pointMoveTo.setCoordinate(standort_koordinate);
		standort_punkt.apply(pointMoveTo);

	}
	
	
		public void geschwindigkeit_dauer_fuer_einen_step(){
		Double zeit = 0.0;
		zeit = 0.926/(akutelle_geschwindigkeit*1.852);
		zeit = zeit * 60;
		geschwindigkeit_in_minuten =(int) Math.round(zeit);
		geschwindigkeit_in_minuten = geschwindigkeit_in_minuten-1;
	}
	
	
	
}
	
	
	
	

	


		

	


