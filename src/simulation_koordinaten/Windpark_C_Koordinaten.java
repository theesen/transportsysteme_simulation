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
    
    
   // for (int i=0;i < anzahl_windraeder;i++){
        // Object auslesen	
	 //   MasonGeometry zielpunkt = ((MasonGeometry)windpark_C_Geometries.objs[i]);	
	    // Point aus Object auslesen
	   // Point ziel_koordinate_punkt = zielpunkt.geometry.getCentroid();
	    // Point an Get_Right_Koordinate weitergeben
	   // ziel_koordinate_punkt= Get_Right_Koordinaten.main(ziel_koordinate_punkt);
	    // Object Array füllen
	    //windpark_c_koordinaten[i][0]= id;
	    //windpark_c_koordinaten[i][1]= ziel_koordinate_punkt;	
	    //windpark_c_koordinaten[i][2]= "Windpark C";
	    //id = id +1;
   // }
    
    
    GeometryFactory factory = new GeometryFactory();
    
    
    
    
    
    Coordinate coordinate = new Coordinate(6.971596822364287, 54.016654315244836); 
    Point ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
    windpark_c_koordinaten[0][0]= 0; 
    windpark_c_koordinaten[0][1]= ziel_koordinate_punkt;      
    windpark_c_koordinaten[0][2]= "Windpark C"; 
     
    coordinate = new Coordinate(6.971596822364287, 54.016654315244836); 
    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
   windpark_c_koordinaten[1][0]= 1; 
   windpark_c_koordinaten[1][1]= ziel_koordinate_punkt;      
   windpark_c_koordinaten[1][2]= "Windpark C"; 
    
   coordinate = new Coordinate(6.971596822364287, 54.016654315244836); 
   ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
  windpark_c_koordinaten[2][0]= 2; 
  windpark_c_koordinaten[2][1]= ziel_koordinate_punkt;      
  windpark_c_koordinaten[2][2]= "Windpark C"; 
   
  coordinate = new Coordinate(7.01411074709834, 54.01661394114727); 
  ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
 windpark_c_koordinaten[3][0]= 3; 
 windpark_c_koordinaten[3][1]= ziel_koordinate_punkt;      
 windpark_c_koordinaten[3][2]= "Windpark C"; 
  
 coordinate = new Coordinate(7.028322429440588, 54.01661394114727); 
 ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[4][0]= 4; 
windpark_c_koordinaten[4][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[4][2]= "Windpark C"; 
 
coordinate = new Coordinate(7.028322429440588, 54.01661394114727); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[5][0]= 5; 
windpark_c_koordinaten[5][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[5][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985727756511408, 54.0084583734395); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[6][0]= 6; 
windpark_c_koordinaten[6][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[6][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985727756511408, 54.0084583734395); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[7][0]= 7; 
windpark_c_koordinaten[7][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[7][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985727756511408, 54.0084583734395); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[8][0]= 8; 
windpark_c_koordinaten[8][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[8][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985727756511408, 54.0084583734395); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[9][0]= 9; 
windpark_c_koordinaten[9][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[9][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.971556448266723, 54.000060561146356); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[10][0]= 10; 
windpark_c_koordinaten[10][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[10][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985768130608971, 53.99997981295123); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[11][0]= 11; 
windpark_c_koordinaten[11][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[11][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985768130608971, 53.99997981295123); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[12][0]= 12; 
windpark_c_koordinaten[12][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[12][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985768130608971, 53.99997981295123); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[13][0]= 13; 
windpark_c_koordinaten[13][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[13][2]= "Windpark C"; 
 
coordinate = new Coordinate(7.028403177635715, 54.00010093524392); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[14][0]= 14; 
windpark_c_koordinaten[14][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[14][2]= "Windpark C"; 
 
coordinate = new Coordinate(7.028403177635715, 54.00010093524392); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[15][0]= 15; 
windpark_c_koordinaten[15][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[15][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985808504706534, 53.99166274885321); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[16][0]= 16; 
windpark_c_koordinaten[16][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[16][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985808504706534, 53.99166274885321); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[17][0]= 17; 
windpark_c_koordinaten[17][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[17][2]= "Windpark C"; 
 
coordinate = new Coordinate(7.01423186939103, 53.9917838711459); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[18][0]= 18; 
windpark_c_koordinaten[18][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[18][2]= "Windpark C"; 
 
coordinate = new Coordinate(7.01423186939103, 53.9917838711459); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[19][0]= 19; 
windpark_c_koordinaten[19][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[19][2]= "Windpark C"; 
 
coordinate = new Coordinate(7.01423186939103, 53.9917838711459); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[20][0]= 20; 
windpark_c_koordinaten[20][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[20][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985687382413845, 53.98338605885275); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[21][0]= 21; 
windpark_c_koordinaten[21][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[21][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985687382413845, 53.98338605885275); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[22][0]= 22; 
windpark_c_koordinaten[22][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[22][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985687382413845, 53.98338605885275); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[23][0]= 23; 
windpark_c_koordinaten[23][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[23][2]= "Windpark C"; 
 
coordinate = new Coordinate(6.985687382413845, 53.98338605885275); 
ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
 
windpark_c_koordinaten[24][0]= 24; 
windpark_c_koordinaten[24][1]= ziel_koordinate_punkt;      
windpark_c_koordinaten[24][2]= "Windpark C";
    
    
    
    
    
    
    
    
    
    
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
