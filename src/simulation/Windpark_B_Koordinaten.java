package simulation;

import java.util.Arrays;

import sim.util.Bag;
import sim.util.geo.MasonGeometry;

import com.vividsolutions.jts.geom.Point;
/**
 * @author Patrick
 * Hier werden alle Koordinaten des Windparks B in ein array geschrieben zusätzlich werden diese noch mithilfe von Get_Right_Koordinate an unsere Karte angepasst bzw. neu berechnet
 *
 */
public class Windpark_B_Koordinaten {
	/**
	 * @param args
	 */
		
	static Object[][]windpark_b_koordinaten= null;     
	static int anzahl_windraeder ;
	static int id =0;
	
	// Konstruktur der Klasse zum füllen des Object Arrays
	public Windpark_B_Koordinaten(Bag windpark_B_Geometries){
		
		
	if (windpark_B_Geometries.isEmpty()){
			throw new RuntimeException("Keine Geometrien gefunden!");
	}	
	
	setAnzahl_windraeder( windpark_B_Geometries.objs.length);
        
    // Passendes Object Arrays erzeugen
    windpark_b_koordinaten=new Object[anzahl_windraeder][3];
    
    
    for (int i=0;i < anzahl_windraeder;i++){
        // Object auslesen	
	    MasonGeometry zielpunkt = ((MasonGeometry)windpark_B_Geometries.objs[i]);	
	    // Point aus Object auslesen
	    Point ziel_koordinate_punkt = zielpunkt.geometry.getCentroid();
	    // Point an Get_Right_Koordinate weitergeben
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(ziel_koordinate_punkt);
	    // Object Array füllen
	    windpark_b_koordinaten[i][0]= id;
	    windpark_b_koordinaten[i][1]= ziel_koordinate_punkt;	
	    windpark_b_koordinaten[i][2]= "Windpark B";
	    id = id +1;
    }
    
    System.out.println("Windpark B Daten eingelesen");
    // Daten ausgeben in Konsole
    System.out.println("*---------------------------------------------------------*");
    printData();	
    System.out.println("*---------------------------------------------------------*");	

	}
	
	
	




	public static int getAnzahl_windraeder() {
		return anzahl_windraeder;
	}

	public static void setAnzahl_windraeder(int anzahl_windraeder) {
		Windpark_B_Koordinaten.anzahl_windraeder = anzahl_windraeder;
		System.out.println("Anzahl Windräder in Windpark B: " + anzahl_windraeder);
	}
	
	
	
	public static Object[][] getwindpark_b_koordinaten() {
		return windpark_b_koordinaten;
	}
	
	public int getColumnCount() {
        return windpark_b_koordinaten.length;
    }
	
	
	public int getRowCount() {
        return windpark_b_koordinaten.length;
    }
	
	
	 public Object getValueAt(int row, int col) {
         return windpark_b_koordinaten[row][col];
     }
	
		
	 public void printData() {
		 
		 
		 for (Object[] arr :  windpark_b_koordinaten) {
	            System.out.println(Arrays.toString(arr));
		 }
		 
	
		 
     }
	
	
	
}
