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
	
	//setAnzahl_orte( orte_Geometries.objs.length);
    
    // Passendes Object Arrays erzeugen
    orte_koordinaten=new Object[7][3];
    
    
   // for (int i=0;i < anzahl_orte;i++){
    	
        // Object auslesen	
	 //   MasonGeometry zielpunkt = ((MasonGeometry)orte_Geometries.objs[i]);	
	    
	    //TODO Ortsnamen einlesen
		
	    
	    // Point aus Object auslesen
	   // Point ziel_koordinate_punkt = zielpunkt.geometry.getCentroid();
	    
	    // Point an Get_Right_Koordinate weitergeben
	   // ziel_koordinate_punkt= Get_Right_Koordinaten.main(ziel_koordinate_punkt);
	    
	    // Object Array füllen
	   // orte_koordinaten[i][0]= id;
	    //orte_koordinaten[i][1]= ziel_koordinate_punkt;	
	 //   orte_koordinaten[i][2]= "Orte";
	    //id = id +1;
    //}
	    
	    
	    GeometryFactory factory = new GeometryFactory();
	    
	    int i=0;
	  
	    
	    Coordinate coordinate = new Coordinate(7.156595836560699, 53.32789855528718);  
	    Point ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
	 	    
	    // Object Array füllen emd
	    orte_koordinaten[0][0]= 0;
	    orte_koordinaten[0][1]= ziel_koordinate_punkt;	
	    orte_koordinaten[0][2]= "Orte";
	    
	    
	    
	     coordinate = new Coordinate(7.104142162276447, 53.71942776690892); 
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
	 	    
	    // Object Array füllen revier
	    orte_koordinaten[1][0]= 1;
	    orte_koordinaten[1][1]= ziel_koordinate_punkt;	
	    orte_koordinaten[1][2]= "Orte";
	    
	    
	    coordinate = new Coordinate(7.1645575549788445, 53.619672118493334);
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
	 	    
	    // Object Array füllen nord
	    orte_koordinaten[2][0]= 2;
	    orte_koordinaten[2][1]= ziel_koordinate_punkt;	
	    orte_koordinaten[2][2]= "Orte";
	    
	    
	    coordinate = new Coordinate(7.092902089215536, 53.3330502554401);
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
	 	    
	    // Object Array füllen marschroute 1
	    orte_koordinaten[3][0]= 3;
	    orte_koordinaten[3][1]= ziel_koordinate_punkt;	
	    orte_koordinaten[3][2]= "Orte";
	    
	    
	    coordinate = new Coordinate(7.010474886768854, 53.37473219304098);
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
	 	    
	    // Object Array füllen marschroute 2
	    orte_koordinaten[4][0]= 4;
	    orte_koordinaten[4][1]= ziel_koordinate_punkt;	
	    orte_koordinaten[4][2]= "Orte";
	    
	    
	    coordinate = new Coordinate(6.977691340341196, 53.51757478819005);
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
	 	    
	    // Object Array füllen marschroute 3
	    orte_koordinaten[5][0]= 5;
	    orte_koordinaten[5][1]= ziel_koordinate_punkt;	
	    orte_koordinaten[5][2]= "Orte";
	    
	    
	    
	    coordinate = new Coordinate(6.947717812178766, 53.6969476207871);
	    ziel_koordinate_punkt= Get_Right_Koordinaten.main(factory.createPoint(coordinate)); 
	 	    
	    // Object Array füllen marschroute 4
	    orte_koordinaten[6][0]= 6;
	    orte_koordinaten[6][1]= ziel_koordinate_punkt;	
	    orte_koordinaten[6][2]= "Orte";
	    
	    
	    
	    
	    
	    
	    
    
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
