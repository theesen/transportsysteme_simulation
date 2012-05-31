package simulation;

import java.io.FileNotFoundException;
import java.util.Arrays;

import jobs.Schiffsdaten_Auslesen;
import sim.engine.SimState;
import sim.field.geo.GeomVectorField;
import sim.util.geo.MasonGeometry;

import simulation.GeoToolsImporter;
import simulation.Simulation;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Point;

import daten.Tabelle_Auftrag_Schiffsdaten;

/**
 * @author Patrick
 *
 */
public class Simulation extends SimState {
	
	/**
	 * 
	 */
	public int rowcount;
	
	public int columncount;
	
	public static Object[][]schiffe_daten_agents= new Object[10][5]; 
	
	private Point location;
	
	private static final long serialVersionUID = 2499844612548898662L;
	
	// vergroesserungs_faktor schon mit eingerichtet sonst Width = 12 und Height = 10
	public static final int WIDTH = 1200; 
	public static final int HEIGHT = 1000;
	// Um diesen Wert wurde die Karte vergroeßert
	public static final int vergroesserungs_faktor = 100; 
	
	public static int NUM_AGENTS = 1;
	
	public GeomVectorField see = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField agents = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField orte = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField c_punkte = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField a_punkte = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField b_punkte = new GeomVectorField(WIDTH, HEIGHT);
	
	public int getNumAgents(){ return NUM_AGENTS;}
	
	public void setNumAgents(int a){ if (a>0) NUM_AGENTS = a;}
	
	public Simulation(long seed){
		super (seed);
		
		try {
			GeoToolsImporter importer = new GeoToolsImporter();
			//ShapeFileImporter importer = new ShapeFileImporter();
			
			 // read in the see Shape file 
			importer.ingest("../ne_50m_admin_0_countries.shp", Simulation.class, see,null);
			
			 // read in the orte Shape file 
			System.out.println("reading orte layer");
            importer.ingest("../Orte.shp", Simulation.class, orte, null);
            
            //read in Windpark A Shape file
            System.out.println("reading a punkte layer");
            importer.ingest("../A_Punkte.shp", Simulation.class, a_punkte, null);
            
            //read in Windpark B Shape file
            System.out.println("reading b punkte layer");
            importer.ingest("../B_Punkte.shp", Simulation.class, b_punkte, null);
				
            // read in the Windpark C Shape file 
            System.out.println("reading c punkte layer");
            importer.ingest("../C_Punkte.shp", Simulation.class, c_punkte, null);
            
            // MBR zusammen basteln
            Envelope MBR = see.getMBR();
            MBR.expandToInclude(orte.getMBR());
            MBR.expandToInclude(a_punkte.getMBR());
            MBR.expandToInclude(b_punkte.getMBR());
            MBR.expandToInclude(c_punkte.getMBR());
            see.setMBR(MBR);
            orte.setMBR(MBR);
            a_punkte.setMBR(MBR);
            b_punkte.setMBR(MBR);
            c_punkte.setMBR(MBR);
            
            
            System.out.println("Done reading data");
            
            /* Höhen auslesen kriegen */
            System.out.println("*---------------------------------------------------------*");
			System.out.println("Orginal Höhe der Karte: "+  see.getHeight());
			System.out.println("Neue Höhe der Karte: "+HEIGHT);
			System.out.println("Orgnial Breite der Karte: "+see.getWidth());
			System.out.println("Neue Breite der Karte: " +WIDTH);
			System.out.println("*---------------------------------------------------------*");
            
            // Windpark A-C Array befühlen
			new Windpark_A_Koordinaten(a_punkte.getGeometries());
			new Windpark_B_Koordinaten(b_punkte.getGeometries());
			new Windpark_C_Koordinaten(c_punkte.getGeometries());
			new Orte_Koordinaten(orte.getGeometries());

            
		}
		catch(FileNotFoundException ex){
			System.out.println("Error opening shapefile!"+ex);
			System.exit(-1);
		}
	}
	
	private void addAgents(Point start_point, int schiffs_id){
		
			System.out.println("*---------------------------------------------------------*");
			System.out.println("Neuen Agent erzeugen für Schiff erzeugt");
			System.out.println("*---------------------------------------------------------*");
			

			Agent a = new Agent(schiffs_id);
 		
			// Location des Agents setzen 
			a.setLocation(start_point);
			

			agents.addGeometry(new MasonGeometry(a.getGeometry()));
			schedule.scheduleRepeating(a);
		
	}
	
	public void start(){
		super.start();
		agents.clear();
		
		// TODO Hier for i Schleife über Anzahl der Benötigten Schiffen und deren ID + Standort etc..
		System.out.println("*---------------------------------------------------------*");
		System.out.println("Agenten auf Startpunkt setzten");
		System.out.println("*---------------------------------------------------------*");
		
		Schiffsdaten_Auslesen.main();
		Schiffe_Init();
		
		
		System.out.println("*-------- Wir haben "+ rowcount+" Schiffe------------------------*");
		
		int schiff_nr = 0;
		for (int i = 0;i<rowcount;i++){
		
		addAgents(schiffe_daten_agents_getPointAt(i),schiff_nr);
		
		
		System.out.println("Schiff hinzugefügt mit Nummer "+schiff_nr + " Schiffsname: "+  " Heimathafen: " + schiffe_daten_agents[i][1]  );
		schiff_nr=schiff_nr +1;
		}
		
		
		
		
		
		agents.setMBR(see.getMBR());
		
	}
	
	public static void main (String[] args){
		doLoop(Simulation.class, args);
		System.exit(0);
	}
	
	
	
	public void Schiffe_Init() {
	
		
		
		System.out.println("*---------------------------------------------------------*");
		System.out.println("Start Schiffe_Init mit Koordinaten");
		
		Tabelle_Auftrag_Schiffsdaten table_object_Auftrag_Schiffsdaten = new Tabelle_Auftrag_Schiffsdaten();
		
		
		rowcount = table_object_Auftrag_Schiffsdaten.getRowCount();
		columncount = table_object_Auftrag_Schiffsdaten.getColumnCount();
				
		
		for (int row = 0; row < rowcount; row++) {
			for (int col = 0; col < columncount; col++) {
				schiffe_daten_agents[row][col] = table_object_Auftrag_Schiffsdaten.getValueAt(row, col);		
			}
			String hafen = schiffe_daten_agents[row][1].toString();
			if (hafen.equals("Emd")){
				schiffe_daten_agents[row][4]=Orte_Koordinaten.getPointAt(0);
			}else if (hafen.equals("Nor")){
				schiffe_daten_agents[row][4]=Orte_Koordinaten.getPointAt(2);				
			}			
		}
		
		
//		for (Object[] arr : schiffe_daten_agents) {
//            System.out.println(Arrays.toString(arr));
//		}
		
		System.out.println("*---------------------------------------------------------*");
		
		
		
		
		

	}
	
	
	
	
	 public static Point schiffe_daten_agents_getPointAt(int row){
		 Point angeforderter_punkt = null;
		 angeforderter_punkt = (Point) schiffe_daten_agents[row][4];	 
		 return angeforderter_punkt;
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
