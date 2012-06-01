package simulation_berechnungen;

import jobs.Auftragsdaten_Auslesen;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import simulation_daten.Array_Fahrkarten;
import simulation_daten.Array_Kuerzester_Weg;
import simulation_koordinaten.Orte_Koordinaten;
import simulation_koordinaten.Windpark_A_Koordinaten;
import simulation_koordinaten.Windpark_B_Koordinaten;
import simulation_koordinaten.Windpark_C_Koordinaten;
import daten.Tabelle_Auftrag_Allgemein;
import daten.Tabelle_Auftrag_Auftraege;
import daten.Tabelle_Auftrag_Schiffsdaten;

/**
 * @author Patrick
 * 
 */
public class Auftraegs_Fahrkarten_Erzeugen {

	public static int auftragsnummer;
	public static String starthafen;
	public static String endhafen;
	public static Point temp_point;
	public static int schiffid;
	public static int geschwindkeit_revier;
	public static int geschwindkeit_marschfahrt;
	public static int geschwindkeit;
	public static int wartezeit;
	public static Point ziel_point ;
	public static Point current_point ;
	public static double current_x;
	public static double current_y;
	public static Tabelle_Auftrag_Allgemein array_auftrag_allgemein;
	public static Tabelle_Auftrag_Auftraege array_auftrag_auftraege;
	
	public static Array_Fahrkarten<Object> array_fahrkarten;
	public static Array_Fahrkarten<Object> getArray_fahrkarten() {
		return array_fahrkarten;
	}



	public static void setArray_fahrkarten(Array_Fahrkarten<Object> array_fahrkarten) {
		Auftraegs_Fahrkarten_Erzeugen.array_fahrkarten = array_fahrkarten;
	}

	public static int row_lenght;
	public static Array_Kuerzester_Weg<Double> array_weg_berechnung_temporär;
	
	
	public Auftraegs_Fahrkarten_Erzeugen() {
		/**
		 * Aufbau array_fahrkarten array
		 *	 X Koordinate
		 *	 Y Kooridnate
		 *   Schiffsid
		 *	 Geschwindigkeit
		 *   Wartezeit
		 */
		
		
	}
	
	
	
		public static void getfahrkarte(){
		
		Auftragsdaten_Auslesen.main();
		
		// Alle Daten besorgen
		array_auftrag_allgemein = new Tabelle_Auftrag_Allgemein();
		array_auftrag_auftraege = new Tabelle_Auftrag_Auftraege();
		array_fahrkarten = new Array_Fahrkarten<Object>();	
		//array_orte_Koordinaten = new Orte_Koordinaten();
		// Diese Daten sollen übergeben werden
		// TODO Daten übergeben
			
		auftragsnummer = 2;
		geschwindkeit_revier = 3;
		geschwindkeit_marschfahrt=12;
		schiffid = 1;
		wartezeit = 0;
		
		geschwindkeit = geschwindkeit_revier;
		
		System.out.println("*---------------------------------------------------------*");
		System.out.println("*-----Fahrkarte erzeugen für Auftragsnummer" + auftragsnummer+"----*");
		

		
		
		System.out.println("*---------Starthafen für Auftrag ermitteln----------------*");
				
		starthafen = array_auftrag_allgemein.getAuftrags_Starthafen(auftragsnummer);
		endhafen = array_auftrag_allgemein.getAuftrags_Endhafen(auftragsnummer);
		
		Orte_Koordinaten.printData();
		
		if (starthafen.equals("Emd")) {
			ziel_point = Orte_Koordinaten.getPointAt(0);
			
			// 435,1223322 ,  583,7745277
			
			array_fahrkarten.Add( ziel_point.getX(),0);
			array_fahrkarten.Add( ziel_point.getY(),0);
			array_fahrkarten.Add( schiffid,0);
			array_fahrkarten.Add( geschwindkeit,0);
			array_fahrkarten.Add( wartezeit,0);
			
			// TODO 1 seemeile weg brechnen
			
			
		} else if (starthafen.equals("Nor")) {
			ziel_point = Orte_Koordinaten.getPointAt(2);
			array_fahrkarten.Add( ziel_point.getX(),0);
			array_fahrkarten.Add( ziel_point.getY(),0);
			array_fahrkarten.Add( schiffid,0);
			array_fahrkarten.Add( geschwindkeit,0);
			array_fahrkarten.Add( wartezeit,0);
			
			// Weg für Revierfahrt berechnen Norddeich
			array_weg_berechnung_temporär = Finde_Kuerzesten_Weg.get_kurzen_weg_zwei_punkte(Orte_Koordinaten.getPointAt(2),Orte_Koordinaten.getPointAt(1));
			
			// Wartezeit wieder auf Null setzten
			wartezeit = 0;
			
			row_lenght = array_weg_berechnung_temporär.getNumRows();
			
			fuelle_array_fahrkarten();
			
		}
		
		
		// Akutelle Position setzen
		set_aktuelle_position();
		geschwindkeit=geschwindkeit_marschfahrt;
		
		/**
		 * Array nur mit den Auftragsdaten für die jeweilige Autragsnummer erzeugen
		 */
		
		
		Object[][] temp_auftrag_array = array_auftrag_auftraege.getAuftragsdaten_fuer_Auftragsnummer(auftragsnummer);
		
		row_lenght=temp_auftrag_array.length;
		
		String ziel_string = null;
		String[] ziel_array = null;
		Integer ziel_wartezeit = null;
		
		for (int i= 0; i < row_lenght; i++){
			
			// Ziel splitten A.01 in zwei einheiten zerlegen
			ziel_string= temp_auftrag_array[i][1].toString();
			ziel_array = ziel_string.split("\\.");
			
			
			// Herrausfinden in welchen Windpark wir müssen 
			if (ziel_array[0].equals("A")){
				// Koordinate für Windrad rausfinden
				ziel_point=Windpark_A_Koordinaten.getPointAt(Integer.parseInt(ziel_array[1]));
			}
			else if (ziel_array[0].equals("B")){
				ziel_point=Windpark_B_Koordinaten.getPointAt(Integer.parseInt(ziel_array[1]));
				}
			else if (ziel_array[0].equals("C")){
				ziel_point=Windpark_C_Koordinaten.getPointAt(Integer.parseInt(ziel_array[1]));
				}
			
			// Weg ausrechnen
			array_weg_berechnung_temporär = Finde_Kuerzesten_Weg.get_kurzen_weg_zwei_punkte(current_point,ziel_point);
			
			// Weg setzen 
			fuelle_array_fahrkarten();
			
			
			// Setze warte Zeit auf die letzte Position
			ziel_wartezeit=Integer.parseInt((String) temp_auftrag_array[i][2]);
			array_fahrkarten.setlastrow(4, ziel_wartezeit);
			
			// Akutell Poistion setzten 
			set_aktuelle_position();
		}

		
		// Nach hause fahren
		gohome();
		
		array_fahrkarten.printDebugData();
		
		
		
		System.out.println("*---------------------------------------------------------*");
	}

	
	public static void set_aktuelle_position(){
		
		GeometryFactory fact = new GeometryFactory();
		current_x = (Double) array_fahrkarten.getlastrow(0);
		current_y = (Double) array_fahrkarten.getlastrow(1);
		current_point = fact.createPoint(new Coordinate(current_x,current_y));
		
		
		
	}
	
	public static void fuelle_array_fahrkarten(){
		
		int reihen = array_fahrkarten.getNumRows();
		System.out.println("array_fahrkarten row count: "+array_fahrkarten.getNumRows()+" array_weg_berechnung_temporär row count: "+array_weg_berechnung_temporär.getNumRows());
		for (int row = 0; row < array_weg_berechnung_temporär.getNumRows(); row++) {
				
				array_fahrkarten.Add( array_weg_berechnung_temporär.get(row, 0),reihen);
				array_fahrkarten.Add( array_weg_berechnung_temporär.get(row, 1),reihen);
				array_fahrkarten.Add( schiffid,reihen);
				array_fahrkarten.Add( geschwindkeit,reihen);
				array_fahrkarten.Add( wartezeit,reihen);
				
				reihen= reihen +1;
		
		}
		
		System.out.println("array_fahrkarten row count: "+array_fahrkarten.getNumRows()+" array_weg_berechnung_temporär row count: "+array_weg_berechnung_temporär.getNumRows());
	}
	
	public static void gohome(){
		
		
		// Akutelle Position setzen
		set_aktuelle_position();
		
		
		if (endhafen.equals("Emd")) {
			ziel_point= Orte_Koordinaten.getPointAt(0);
			// Weg ausrechnen
			array_weg_berechnung_temporär = Finde_Kuerzesten_Weg.get_kurzen_weg_zwei_punkte(current_point,ziel_point);		
			fuelle_array_fahrkarten();
			set_aktuelle_position();
			
			
			
		} else if (endhafen.equals("Nor")) {
			
			// Revierfahrt ansteuern
			ziel_point = Orte_Koordinaten.getPointAt(1);
			array_weg_berechnung_temporär = Finde_Kuerzesten_Weg.get_kurzen_weg_zwei_punkte(current_point,ziel_point);
			fuelle_array_fahrkarten();
			set_aktuelle_position();
			
			
			geschwindkeit=geschwindkeit_revier;
			// Nach Norddeich fahren
			ziel_point = Orte_Koordinaten.getPointAt(2);
			array_weg_berechnung_temporär = Finde_Kuerzesten_Weg.get_kurzen_weg_zwei_punkte(current_point,ziel_point);
			fuelle_array_fahrkarten();
			set_aktuelle_position();
			
			// WarteZeit setzten
			array_fahrkarten.setlastrow(4, 30);
			
			
		}
		
		
	}
	
	public static void main(){
		
		
		
		
	}
	
}
