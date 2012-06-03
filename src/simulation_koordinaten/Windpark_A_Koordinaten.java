package simulation_koordinaten;

import java.util.Arrays;

import sim.util.Bag;
import sim.util.geo.MasonGeometry;
import simulation_berechnungen.Get_Right_Koordinaten;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
/**
 * @author Patrick
 * Hier werden alle Koordinaten des Windparks A in ein array geschrieben zusätzlich werden diese noch mithilfe von Get_Right_Koordinate an unsere Karte angepasst bzw. neu berechnet
 *
 */
public class Windpark_A_Koordinaten {
	/**
	 * @param args
	 */
		
	static Object[][]windpark_a_koordinaten= null;     
	static int anzahl_windraeder ;
	static int id =0;
	
	// Konstruktur der Klasse zum füllen des Object Arrays
	public Windpark_A_Koordinaten(Bag windpark_A_Geometries){
		
		
	if (windpark_A_Geometries.isEmpty()){
			throw new RuntimeException("Keine Geometrien gefunden!");
	}	
	
	setAnzahl_windraeder( windpark_A_Geometries.objs.length);
        
    // Passendes Object Arrays erzeugen
    windpark_a_koordinaten=new Object[anzahl_windraeder][3];
    
    
		//  for (int i=0;i < anzahl_windraeder;i++){
        // Object auslesen	
	//    MasonGeometry zielpunkt = ((MasonGeometry)windpark_A_Geometries.objs[i]);	
	    // Point aus Object auslesen
	  //  Point ziel_koordinate_punkt = zielpunkt.geometry.getCentroid();
	    // Point an Get_Right_Koordinate weitergeben
	  //  ziel_koordinate_punkt= Get_Right_Koordinaten.main(ziel_koordinate_punkt);
	    // Object Array füllen
	  //  id = id +1;
   // }
    
    GeometryFactory factory = new GeometryFactory();
    
    int i=0;
  
    
    Coordinate coordinate = new Coordinate(6.595030046150043, 54.01673460359173); 
    Point ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
    windpark_a_koordinaten[0][0]= 0; 
    windpark_a_koordinaten[0][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[0][2]= "Windpark A";
    

    coordinate = new Coordinate(6.595030046150043, 54.01673460359173); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate));  
    windpark_a_koordinaten[1][0]= 1; 
    windpark_a_koordinaten[1][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[1][2]= "Windpark A";
    

    coordinate = new Coordinate(6.595030046150043, 54.01673460359173); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate));  
    windpark_a_koordinaten[2][0]= 2; 
    windpark_a_koordinaten[2][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[2][2]= "Windpark A";
    

    coordinate = new Coordinate(6.637624719079224, 54.01673460359173); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[3][0]= 3; 
    windpark_a_koordinaten[3][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[3][2]= "Windpark A";
    

    coordinate = new Coordinate(6.637624719079224, 54.01673460359173); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[4][0]= 4; 
    windpark_a_koordinaten[4][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[4][2]= "Windpark A";
    

    coordinate = new Coordinate(6.637624719079224, 54.01673460359173); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[5][0]= 5; 
    windpark_a_koordinaten[5][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[5][2]= "Windpark A";
    

    coordinate = new Coordinate(6.609201354394728, 54.0085386617864); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[6][0]= 6; 
    windpark_a_koordinaten[6][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[6][2]= "Windpark A";
    

    coordinate = new Coordinate(6.609201354394728, 54.0085386617864); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[7][0]= 7; 
    windpark_a_koordinaten[7][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[7][2]= "Windpark A";
    

    coordinate = new Coordinate(6.609201354394728, 54.0085386617864); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[8][0]= 8; 
    windpark_a_koordinaten[8][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[8][2]= "Windpark A";
    
    
    coordinate = new Coordinate(6.609201354394728, 54.0085386617864); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[9][0]= 9; 
    windpark_a_koordinaten[9][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[9][2]= "Windpark A";
    
    coordinate = new Coordinate(6.609201354394728, 54.0085386617864); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[10][0]= 10; 
    windpark_a_koordinaten[10][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[10][2]= "Windpark A";

    coordinate = new Coordinate(6.609201354394728, 54.0085386617864); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[11][0]= 11; 
    windpark_a_koordinaten[11][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[11][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623372662639413, 54.000140849493256); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[12][0]= 12; 
    windpark_a_koordinaten[12][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[12][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623372662639413, 54.000140849493256); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[13][0]= 13; 
    windpark_a_koordinaten[13][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[13][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623372662639413, 54.000140849493256); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[14][0]= 14; 
    windpark_a_koordinaten[14][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[14][2]= "Windpark A";
    

    coordinate = new Coordinate(6.59511079434517, 53.991783411297675); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[15][0]= 15; 
    windpark_a_koordinaten[15][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[15][2]= "Windpark A";
    

    coordinate = new Coordinate(6.59511079434517, 53.991783411297675); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[16][0]= 16; 
    windpark_a_koordinaten[16][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[16][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623493784932102, 53.99182378539523); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[17][0]= 17; 
    windpark_a_koordinaten[17][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[17][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623493784932102, 53.99182378539523); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[18][0]= 18; 
    windpark_a_koordinaten[18][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[18][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623493784932102, 53.99182378539523); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[19][0]= 19; 
    windpark_a_koordinaten[19][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[19][2]= "Windpark A";
    
    

    coordinate = new Coordinate(6.623493784932102, 53.99182378539523); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[20][0]= 20; 
    windpark_a_koordinaten[20][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[20][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623493784932102, 53.99182378539523); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[21][0]= 21; 
    windpark_a_koordinaten[21][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[21][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623493784932102, 53.99182378539523); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[22][0]= 22; 
    windpark_a_koordinaten[22][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[22][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623493784932102, 53.99182378539523); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[23][0]= 23; 
    windpark_a_koordinaten[23][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[23][2]= "Windpark A";
    

    coordinate = new Coordinate(6.623493784932102, 53.99182378539523); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
    windpark_a_koordinaten[24][0]= 24; 
    windpark_a_koordinaten[24][1]= ziel_koordinate_punkt;      
    windpark_a_koordinaten[24][2]= "Windpark A";
    
    
    
    
    
    
    
    
    System.out.println("Windpark A Daten eingelesen");
    // Daten ausgeben in Konsole
    System.out.println("*---------------------------------------------------------*");
    printData();	
    System.out.println("*---------------------------------------------------------*");	

	}
	
	
	




	public static int getAnzahl_windraeder() {
		return anzahl_windraeder;
	}

	public static void setAnzahl_windraeder(int anzahl_windraeder) {
		Windpark_A_Koordinaten.anzahl_windraeder = anzahl_windraeder;
		System.out.println("Anzahl Windräder in Windpark A: " + anzahl_windraeder);
	}
	
	
	
	public static Object[][] getwindpark_a_koordinaten() {
		return windpark_a_koordinaten;
	}
	
	public int getColumnCount() {
        return windpark_a_koordinaten.length;
    }
	
	
	public int getRowCount() {
        return windpark_a_koordinaten.length;
    }
	
	
	 public Object getValueAt(int row, int col) {
         return windpark_a_koordinaten[row][col];
     }
	
		
	 public void printData() {
		 
		 
		 for (Object[] arr :  windpark_a_koordinaten) {
	            System.out.println(Arrays.toString(arr));
		 }

     }
	 public static Point getPointAt(int windrad_nr){
		 Point angeforderter_punkt = null;
		 
		 angeforderter_punkt = (Point) windpark_a_koordinaten[windrad_nr][1];
		 
		 
		 return angeforderter_punkt;
	 }
	
	 
}
