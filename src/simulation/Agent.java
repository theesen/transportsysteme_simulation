/**
 * 
 */
package simulation;

/**
 * @author Patrick
 *
 */

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.geo.PointMoveTo;
import simulation_berechnungen.Auftraegs_Fahrkarten_Erzeugen;
import simulation_berechnungen.Reporting_Erzeugen;
import simulation_berechnungen.Sammlung_Berechnungen;
import simulation_daten.Array_Auftraege_Status;
import simulation_daten.Array_Fahrkarten;
import simulation_daten.Array_Reporting;
import simulation_koordinaten.Orte_Koordinaten;

/**
 * @param args
 */

public class Agent implements Steppable {

	int schiffs_id;
	int direction;
	Point standort_koordinate = null;
	private static final long serialVersionUID = -1113018274619047013L;
	int vorgang = 0;
	int geschw_revierfahrt;
	int geschw_marschfahrt;
	int current_row = 0;
	String heimathafen;
	String schiff_name;
	Boolean schiff_ist_beschaeftigt = false;
	Array_Auftraege_Status array_auftraege_status = new Array_Auftraege_Status();
	Array_Fahrkarten<Object> fahrweg_fahrkarte;
	int Auftragsnummer;
	Object frei_auftragsnr;
	Coordinate coord = new Coordinate();
	double moveRate = 100.0;
	PointMoveTo pointMoveTo = new PointMoveTo();
	int schiff_muss_warten;
	Boolean schiff_wartet = false;
	int akutelle_geschwindigkeit;
	int rowcount;
	int geschwindigkeit_in_minuten;
	Boolean schiff_hat_keinen_auftrag_mehr = false;
	int umschlags_zeit;
	int aufenthalt_zeit = 0;
	
	
	public Agent(int id, String schiffs_name, String Heimathafen,
		String geschwindigkeit_revierfahrt,
		String geschwindigkeit_marschfahrt) {
		geschw_revierfahrt = Integer.parseInt(geschwindigkeit_revierfahrt);
		geschw_marschfahrt = Integer.parseInt(geschwindigkeit_marschfahrt);
		schiff_name = schiffs_name;
		schiffs_id = id;
		heimathafen = Heimathafen;
	}

	public void setLocation(Point p) {
		standort_koordinate = p;
	}

	

	public Geometry getGeometry() {
		return standort_koordinate;
	}
	

	public void step(SimState state) {

		vorgang = vorgang + 1;
		
		

		

		if (vorgang == 1){
			coord.x=standort_koordinate.getX();
			coord.y=standort_koordinate.getY();		
			
		}
		if (vorgang == 837){
			longdebug_nachricht();
		}
		// Debug nachricht auswählen
		longdebug_nachricht();
		//shortdebug_nachricht();
		
		
		gucken_ob_schiff_warten_muss();
		
		
		if (schiff_wartet == false) {
			if (schiff_ist_beschaeftigt == false) {
				fahrtkarte_zuweisung();
			}
			if (!frei_auftragsnr.equals(false)  && !frei_auftragsnr.equals("Warten")) {

				if (geschwindigkeit_in_minuten == 0) {
					fahrkarte_ablaufen();
					
				} else {
					geschwindigkeit_in_minuten = geschwindigkeit_in_minuten - 1;
				}

			} else {

				if (schiff_hat_keinen_auftrag_mehr == false && !frei_auftragsnr.equals("Warten")) {
					schiff_hat_keinen_auftrag_mehr = true;
					
					Reporting_Erzeugen.set_gesamt_zeit(schiffs_id,vorgang); // Gesamtzeit setzten
				}
				
				if (Reporting_Erzeugen.gucke_ob_alle_reports_fertig_sind() == true){
					state.finish();
					
					Reporting_Erzeugen.printreport(aufenthalt_zeit);
				}
				
				
				// 

				//	System.out.println("Schiff hat keine Auftraeg mehr");

			}
		}
		zeiten_brechnen(); // Zeit im Hafen und Zeiten auf See
		bewege_dich();
		
		
		
		
		
	}
	
	public void zeiten_brechnen(){
		
		
		//Point vergleichs_position = Sammlung_Berechnungen.Erzeuge_Punkt_aus_Koordinaten(coord.x, coord.y);
		
		Point emden = Orte_Koordinaten.getPointAt(2);
		Point norddeich = Orte_Koordinaten.getPointAt(0);
		
		if (umschlags_zeit!= 0 || akutelle_geschwindigkeit== 0 && (standort_koordinate.equals(emden) || standort_koordinate.equals(norddeich) ))
		{
			Reporting_Erzeugen.set_zeit_im_hafen(schiffs_id, 1);
			
			if (umschlags_zeit==0){
				Reporting_Erzeugen.set_wartezeit(schiffs_id, 1);
			}
			
			
		}else
		{
			Reporting_Erzeugen.set_zeit_auf_see(schiffs_id, 1);
		}
		
		
		
		
	}
	
	
	
	
	
	
	public void fahrtkarte_zuweisung(){
		
		if (heimathafen.equals("Emd")) {

			frei_auftragsnr = Array_Auftraege_Status.get_freie_Auftragsnummer_Emden(vorgang);
		
			
			if (!frei_auftragsnr.equals(false) && !frei_auftragsnr.equals("Warten")) {
				System.out.println(Orte_Koordinaten.getAnzahl_orte());
				fahrweg_fahrkarte = Auftraegs_Fahrkarten_Erzeugen.getfahrkarte((Integer) frei_auftragsnr,schiffs_id, geschw_revierfahrt,geschw_marschfahrt, heimathafen);
				umschlags_zeit = 30; //Umschlagszeit 
				Auftragsnummer = (Integer) frei_auftragsnr;
				Array_Auftraege_Status.setAuftraege_emden_erledigt(Auftragsnummer);
				schiff_ist_beschaeftigt = true;	
				current_row = 0;
			}

		} else if (heimathafen.equals("Nor")) {

			frei_auftragsnr = Array_Auftraege_Status.get_freie_Auftragsnummer_Norddeich(vorgang);

			if (!frei_auftragsnr.equals(false)  && !frei_auftragsnr.equals("Warten")) {
				
				fahrweg_fahrkarte = Auftraegs_Fahrkarten_Erzeugen.getfahrkarte((Integer) frei_auftragsnr,schiffs_id, geschw_revierfahrt,geschw_marschfahrt, heimathafen);
				umschlags_zeit = 30; // Umschlagszeit
				Auftragsnummer = (Integer) frei_auftragsnr;
				Array_Auftraege_Status.setAuftraege_norddeich_erledigt(Auftragsnummer);
				schiff_ist_beschaeftigt = true;	
				current_row = 0;
			}
			
		}
		
	}
	
	public void fahrkarte_ablaufen(){
	
		rowcount = fahrweg_fahrkarte.getNumRows() - 1;
		

		

		if (current_row != rowcount) {
			coord.x = (Double) fahrweg_fahrkarte.get(current_row, 0);
			coord.y = (Double) fahrweg_fahrkarte.get(current_row, 1);
			
			// Geschwindigkeit
			akutelle_geschwindigkeit=(Integer) fahrweg_fahrkarte.get(current_row,3);
			geschwindigkeit_dauer_fuer_einen_step(); 
			
			int seemeile = 1852/926;
			Reporting_Erzeugen.seemeilen(schiffs_id, seemeile);
			// Wartezeit
			schiff_muss_warten= schiff_muss_warten +(Integer) fahrweg_fahrkarte.get(current_row,4);
			aufenthalt_zeit = aufenthalt_zeit + schiff_muss_warten;
			
			//Reporting_Erzeugen.set_wartezeit(schiffs_id, schiff_muss_warten);
			
			
			current_row = current_row + 1;
			
		} else {
			if (heimathafen.equals("Emd")) {
				Array_Auftraege_Status.setAuftraege_emden_erledigt((Integer) Auftragsnummer);
			} else if (heimathafen.equals("Nor")) {
				Array_Auftraege_Status.setAuftraege_norddeich_erledigt((Integer) Auftragsnummer);
			}

			schiff_ist_beschaeftigt = false;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
	
	
	public void gucken_ob_schiff_warten_muss(){
		
		schiff_wartet = false;
		if (schiff_muss_warten!=0 || umschlags_zeit!=0){
			
		if (schiff_muss_warten!=0){schiff_muss_warten = schiff_muss_warten -1;}
		if (umschlags_zeit!=0 ){umschlags_zeit = umschlags_zeit -1;}
		
		schiff_wartet = true;
		
		}
		
		
		
		
		
	}
	
	public void longdebug_nachricht(){
		int restminuten =  vorgang % 60;
		int stunden = (vorgang / 60);     
        System.out.println("Zeit: Stunden: "+stunden+" Minuten: "+restminuten);
		System.out.println("*---------------------------------------------------------*");
		
		System.out.println("Vorgang " + vorgang + " wird gestartet mit Schiff: " + schiffs_id);
		System.out.println("Schiffs Id: " + schiffs_id + " Schiffs Name: "
				+ schiff_name + " Heimathafen: " + heimathafen
				+ " Geschwindigkeit Revierfahrt: " + geschw_revierfahrt
				+ " Geschwindigkeit Marschfahrt: " + geschw_marschfahrt
				+ " Schiff ist beschaeftigt: " + schiff_ist_beschaeftigt);
		
		System.out.println("Schiff muss noch: " +schiff_muss_warten+ " warten");
		System.out.println("Schiff muss noch: " +umschlags_zeit + " umschlagen");
		System.out.println("Schiff fährt mit: " +akutelle_geschwindigkeit+ " Seemeilen");
		
		System.out.println("Schiff hat Auftrag: "+ frei_auftragsnr+ " bekommen");
	
		System.out.println("Auftrags Koordinate: " + current_row+ " von " + rowcount);
		
		System.out.println("Akutelle Koordinate: "+coord);
		System.out.println("Zeit bis zum nächsten Koordinatensprung: " +geschwindigkeit_in_minuten);
		System.out.println("Zeit auf See: " + Reporting_Erzeugen.get_zeit_auf_see(schiffs_id) +"min");
		System.out.println("Zeit im Hafen: " + Reporting_Erzeugen.get_zeit_im_hafen(schiffs_id)+ "min");
		System.out.println("Zurückgelegte Seemeilen: " +Reporting_Erzeugen.get_seemeilen(schiffs_id)+ "sm");
		System.out.println("Wartezeit :" + Reporting_Erzeugen.get_wartezeit(schiffs_id));
		System.out.println("Aufenthaltszeit: "+ aufenthalt_zeit +"min");
		System.out.println("*---------------------------------------------------------*");
	}
	
	
	
	public void shortdebug_nachricht(){
		
		System.out.println("Vorgang: " + vorgang + " wird gestartet mit Schiff: " + schiffs_id);
		System.out.println("Auftrags koordinate: " + current_row+ " von " + rowcount);
		
		
	}
	
	
	
	public void bewege_dich(){
		
		
		
		pointMoveTo.setCoordinate(coord);
		standort_koordinate.apply(pointMoveTo);
		
		
	}
	
	
	public void geschwindigkeit_dauer_fuer_einen_step(){
		
		Double zeit = 0.0;
		zeit = 0.926/(akutelle_geschwindigkeit*1.852);
		zeit = zeit * 60;
//		zeit = ;
//		System.out.println(zeit);
		geschwindigkeit_in_minuten =(int) Math.round(zeit);
		geschwindigkeit_in_minuten = geschwindigkeit_in_minuten +1;
	}
	
	
	
}
	
	
	
	

	


		

	


