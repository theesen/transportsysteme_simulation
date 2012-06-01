package simulation_koordinaten;

import java.util.Arrays;
import com.vividsolutions.jts.geom.Point;

import sim.util.Bag;
import sim.util.geo.MasonGeometry;
import simulation_berechnungen.Get_Right_Koordinaten;
/**
 * @author Patrick
 * Hier werden alle Koordinaten des Windparks C in ein array geschrieben zusätzlich werden diese noch mithilfe von Get_Right_Koordinate an unsere Karte angepasst bzw. neu berechnet
 *
 */
public class Orte_Koordinaten {
	/**
	 * @param args
	 */
	public static Object[][]orte_koordinaten= null;     
	public static int anzahl_orte ;
	public static int id =0;
	
	// Konstruktur der Klasse zum füllen des Object Arrays
	public Orte_Koordinaten(){
		
		
	}	
	public static void Fuelle_Orte_Koordinaten(Bag orte_Geometries)	{
		
	if (orte_Geometries.isEmpty()){
			throw new RuntimeException("Keine Geometrien gefunden!");
	}	
	
	setAnzahl_orte( orte_Geometries.objs.length);
        
    // Passendes Object Arrays erzeugen
    orte_koordinaten=new Object[anzahl_orte][3];
    
    
    for (int i=0;i < anzahl_orte;i++){
    	
        // Object auslesen	
	    MasonGeometry zielpunkt = ((MasonGeometry)orte_Geometries.objs[i]);	
	    
	    //TODO Ortsnamen einlesen
		
	    
	    // Point aus Object auslesen
	    Point ziel_koordinate_punkt = zielpunkt.geometry.getCentroid();
	    
	    // Point an Get_Right_Koordinate weitergeben
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(ziel_koordinate_punkt);
	    
	    // Object Array füllen
	    orte_koordinaten[i][0]= id;
	    orte_koordinaten[i][1]= ziel_koordinate_punkt;	
	    orte_koordinaten[i][2]= "Orte";
	    id = id +1;
    }
    
    System.out.println("Orte Daten eingelesen");
    // Daten ausgeben in Konsole
    System.out.println("*---------------------------------------------------------*");
    printData();	
    System.out.println("*---------------------------------------------------------*");	

	}


	public static int getAnzahl_orte() {
		return anzahl_orte;
	}

	public static void setAnzahl_orte(int anzahl_orte) {
		Orte_Koordinaten.anzahl_orte = anzahl_orte;
		System.out.println("Anzahl Orte: " + anzahl_orte);
	}
	
	
	
	public static Object[][] getorte_koordinaten() {
		return orte_koordinaten;
	}
	
	public int getColumnCount() {
        return orte_koordinaten.length;
    }
	
	
	public int getRowCount() {
        return orte_koordinaten.length;
    }
	
	
	 public Object getValueAt(int row, int col) {
         return orte_koordinaten[row][col];
     }
	
	 
	 // TODO Hier Namen und nicht spalte anfragen
	 public static Point getPointAt(int ort_nr){
		 Point angeforderter_punkt = null;
		 
		 angeforderter_punkt = (Point)orte_koordinaten[ort_nr][1];
		 
		 
		 return angeforderter_punkt;
	 }
	 
		
	 public static void printData() {
		 
		 
		 for (Object[] arr :  orte_koordinaten) {
	            System.out.println(Arrays.toString(arr));
		 }
	 
		 
     }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
