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
import simulation_daten.Array_Auftraege_Status;
import simulation_daten.Array_Fahrkarten;

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
	int wartezeit;
	Boolean schiff_wartet = false;
	int akutelle_geschwindigkeit;
	int rowcount;
	int geschwindigkeit_in_minuten;
	Boolean schiff_hat_keinen_auftrag_mehr = false;
	
	
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

		
		
		// Debug nachricht auswählen
		longdebug_nachricht();
		//shortdebug_nachricht();
		
		
		gucken_ob_schiff_warten_muss();
		
		
		if (schiff_wartet == false) {
			if (schiff_ist_beschaeftigt == false) {
				fahrtkarte_zuweisung();
			}
			if (!frei_auftragsnr.equals(false)) {

				if (geschwindigkeit_in_minuten == 0) {
					fahrkarte_ablaufen();
				} else {
					geschwindigkeit_in_minuten = geschwindigkeit_in_minuten - 1;
				}

			} else {

				if (schiff_hat_keinen_auftrag_mehr = false) {
					schiff_hat_keinen_auftrag_mehr = true;
				}

				// state.finish();

				System.out.println("Schiff hat keine Auftraeg mehr");

			}
		}

		bewege_dich();
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public void fahrtkarte_zuweisung(){
		
		if (heimathafen.equals("Emd")) {

			frei_auftragsnr = Array_Auftraege_Status.get_freie_Auftragsnummer_Emden();
			
			if (!frei_auftragsnr.equals(false)) {
				
				fahrweg_fahrkarte = Auftraegs_Fahrkarten_Erzeugen.getfahrkarte((Integer) frei_auftragsnr,schiffs_id, geschw_revierfahrt,geschw_marschfahrt, heimathafen);

				Auftragsnummer = (Integer) frei_auftragsnr;
				Array_Auftraege_Status.setAuftraege_emden_erledigt(Auftragsnummer);
				schiff_ist_beschaeftigt = true;	
			}

		} else if (heimathafen.equals("Nor")) {

			frei_auftragsnr = Array_Auftraege_Status.get_freie_Auftragsnummer_Nordeich();

			if (!frei_auftragsnr.equals(false)) {
				
				fahrweg_fahrkarte = Auftraegs_Fahrkarten_Erzeugen.getfahrkarte((Integer) frei_auftragsnr,schiffs_id, geschw_revierfahrt,geschw_marschfahrt, heimathafen);

				Auftragsnummer = (Integer) frei_auftragsnr;
				Array_Auftraege_Status.setAuftraege_norddeich_erledigt(Auftragsnummer);
				schiff_ist_beschaeftigt = true;	
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
			// Wartezeit
			wartezeit= (Integer) fahrweg_fahrkarte.get(current_row,4);
			
			
			
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
		if (wartezeit!=0){
		wartezeit = wartezeit -1;
		
		schiff_wartet = true;
		
		}
		
		
		
		
		
	}
	
	public void longdebug_nachricht(){
		
		System.out.println("*---------------------------------------------------------*");
		System.out.println("Vorgang: " + vorgang + " wird gestartet mit Schiff: " + schiffs_id);
		System.out.println("Schiffs Id: " + schiffs_id + " Schiffs Name: "
				+ schiff_name + " Heimathafen: " + heimathafen
				+ " Geschwindigkeit Revierfahrt: " + geschw_revierfahrt
				+ " Geschwindigkeit Marschfahrt: " + geschw_marschfahrt
				+ " Schiff ist beschaeftigt: " + schiff_ist_beschaeftigt);
		
		System.out.println("Schiff muss noch: " +wartezeit+ " Minuten warten");
		System.out.println("Schiff fährt mit: " +akutelle_geschwindigkeit+ " Seemeilen");
		
		System.out.println("Schiff hat Auftrag: "+ frei_auftragsnr+ " bekommen");
	
		System.out.println("Auftrags Koordinate: " + current_row+ " von " + rowcount);
		
		System.out.println("Akutelle Koordinate: "+coord);
		System.out.println("Zeit bis zum nächsten Koordinatensprung: " +geschwindigkeit_in_minuten);
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
	
	
	
	

	


		

	


