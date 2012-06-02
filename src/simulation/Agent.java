/**
 * 
 */
package simulation;

/**
 * @author Patrick
 *
 */

import java.util.Random;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.util.AffineTransformation;


import sim.display.Console;
import sim.display.Prefs;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.geo.GeomVectorField;
import sim.util.geo.PointMoveTo;
import simulation_berechnungen.Auftraegs_Fahrkarten_Erzeugen;
import simulation_berechnungen.Finde_Kuerzesten_Weg;
import simulation_daten.Array_Auftraege_Status;
import simulation_daten.Array_Fahrkarten;
import simulation_daten.Array_Kuerzester_Weg;

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

		System.out.println("*---------------------------------------------------------*");
		System.out.println("Vorgang: " + vorgang + " wird gestartet mit Schiff: " + schiffs_id);
//		System.out.println("Schiffs Id: " + schiffs_id + " Schiffs Name: "
//				+ schiff_name + " Heimathafen: " + heimathafen
//				+ " Geschwindigkeit Revierfahrt: " + geschw_revierfahrt
//				+ " Geschwindigkeit Marschfahrt: " + geschw_marschfahrt+
//				" Schiff ist beschaeftigt: " +schiff_ist_beschaeftigt);
//		System.out.println("*---------------------------------------------------------*");

		if (schiff_ist_beschaeftigt == false) {

			if (heimathafen.equals("Emd")) {

				frei_auftragsnr = Array_Auftraege_Status.get_freie_Auftragsnummer_Emden();
				
				if (!frei_auftragsnr.equals(false)) {
					System.out.println("Schiff hat Auftrag "+ frei_auftragsnr+ " bekommen");
					fahrweg_fahrkarte = Auftraegs_Fahrkarten_Erzeugen.getfahrkarte((Integer) frei_auftragsnr,schiffs_id, geschw_revierfahrt,geschw_marschfahrt, heimathafen);

					Auftragsnummer = (Integer) frei_auftragsnr;
					Array_Auftraege_Status.setAuftraege_emden_erledigt(Auftragsnummer);
					schiff_ist_beschaeftigt = true;	
				}

			} else if (heimathafen.equals("Nor")) {

				frei_auftragsnr = Array_Auftraege_Status.get_freie_Auftragsnummer_Nordeich();

				if (!frei_auftragsnr.equals(false)) {
					System.out.println("Schiff hat Auftrag "+ frei_auftragsnr+ " bekommen");
					fahrweg_fahrkarte = Auftraegs_Fahrkarten_Erzeugen.getfahrkarte((Integer) frei_auftragsnr,schiffs_id, geschw_revierfahrt,geschw_marschfahrt, heimathafen);

					Auftragsnummer = (Integer) frei_auftragsnr;
					Array_Auftraege_Status.setAuftraege_norddeich_erledigt(Auftragsnummer);
					schiff_ist_beschaeftigt = true;	
				}
				
			}
			
		}	

			if (!frei_auftragsnr.equals(false)) {

				int rowcount = fahrweg_fahrkarte.getNumRows() - 1;
				//System.out.println("Auftrag hat: " + rowcount + " Schritte");

				

				if (current_row != rowcount) {
					coord.x = (Double) fahrweg_fahrkarte.get(current_row, 0);
					coord.y = (Double) fahrweg_fahrkarte.get(current_row, 1);
					//System.out.println("Auftrags koordinate: " + current_row+ " von " + rowcount);
					current_row = current_row + 1;
					
				} else {

					if (heimathafen.equals("Emd")) {
						Array_Auftraege_Status
								.setAuftraege_emden_erledigt((Integer) Auftragsnummer);
					} else if (heimathafen.equals("Nor")) {
						Array_Auftraege_Status
								.setAuftraege_norddeich_erledigt((Integer) Auftragsnummer);
					}

					schiff_ist_beschaeftigt = false;
				}
			} else {
				
				System.out.println("Schiff hat keine Auftraeg mehr");
			}

		
		System.out.println(coord);	
		pointMoveTo.setCoordinate(coord);
		standort_koordinate.apply(pointMoveTo);
		
	
	
	
	}
}
	
	
	
	

	


		

	


