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
public class Windpark_C_Koordinaten {
	/**
	 * @param args
	 */
		
	static Object[][]windpark_c_koordinaten= null;     
	static int anzahl_windraeder ;
	static int id =0;
	
	// Konstruktur der Klasse zum füllen des Object Arrays
	public Windpark_C_Koordinaten(Bag windpark_C_Geometries){
		
		
	if (windpark_C_Geometries.isEmpty()){
			throw new RuntimeException("Keine Geometrien gefunden!");
	}	
	
	setAnzahl_windraeder( windpark_C_Geometries.objs.length);
        
    // Passendes Object Arrays erzeugen
    windpark_c_koordinaten=new Object[anzahl_windraeder][3];
    
    
    for (int i=0;i < anzahl_windraeder;i++){
        // Object auslesen	
	    MasonGeometry zielpunkt = ((MasonGeometry)windpark_C_Geometries.objs[i]);	
	    // Point aus Object auslesen
	    Point ziel_koordinate_punkt = zielpunkt.geometry.getCentroid();
	    // Point an Get_Right_Koordinate weitergeben
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(ziel_koordinate_punkt);
	    // Object Array füllen
	    windpark_c_koordinaten[i][0]= id;
	    windpark_c_koordinaten[i][1]= ziel_koordinate_punkt;	
	    windpark_c_koordinaten[i][2]= "Windpark C";
	    id = id +1;
    }
    
    System.out.println("Windpark C Daten eingelesen");
    // Daten ausgeben in Konsole
    System.out.println("*---------------------------------------------------------*");
    printData();	
    System.out.println("*---------------------------------------------------------*");	

	}
	
	
	




	public static int getAnzahl_windraeder() {
		return anzahl_windraeder;
	}

	public static void setAnzahl_windraeder(int anzahl_windraeder) {
		Windpark_C_Koordinaten.anzahl_windraeder = anzahl_windraeder;
		System.out.println("Anzahl Windräder in Windpark C: " + anzahl_windraeder);
	}
	
	
	
	public static Object[][] getwindpark_c_koordinaten() {
		return windpark_c_koordinaten;
	}
	
	public int getColumnCount() {
        return windpark_c_koordinaten.length;
    }
	
	
	public int getRowCount() {
        return windpark_c_koordinaten.length;
    }
	
	
	 public Object getValueAt(int row, int col) {
         return windpark_c_koordinaten[row][col];
     }
	
		
	 public void printData() {
		 
		 
		 for (Object[] arr :  windpark_c_koordinaten) {
	            System.out.println(Arrays.toString(arr));
		 }
		 
		 
		 
		 
		 
     }
	
	
	
	
	 public static Point getPointAt(int windrad_nr){
		 Point angeforderter_punkt = null;
		 
		 angeforderter_punkt = (Point) windpark_c_koordinaten[windrad_nr][1];
		 
		 
		 return angeforderter_punkt;
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
