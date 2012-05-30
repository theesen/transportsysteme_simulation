package simulation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import sim.engine.SimState;
import sim.field.geo.GeomVectorField;
import sim.util.Bag;
import sim.util.geo.GeometryUtilities;
import sim.util.geo.MasonGeometry;
import simulation.Agent;
import simulation.GeoToolsImporter;
import simulation.Simulation;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

/**
 * @author Patrick
 *
 */
public class Simulation extends SimState {
	
	/**
	 * 
	 */
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
				
            // read in the Windpark C Shape file 
            System.out.println("reading c punkte layer");
            importer.ingest("../C_Punkte.shp", Simulation.class, c_punkte, null);
            
            // MBR zusammen basteln
            Envelope MBR = see.getMBR();
            MBR.expandToInclude(orte.getMBR());
            MBR.expandToInclude(c_punkte.getMBR());
            see.setMBR(MBR);
            orte.setMBR(MBR);
            c_punkte.setMBR(MBR);
            
            
            System.out.println("Done reading data");
            
            /* Höhen auslesen kriegen */
            System.out.println("*---------------------------------------------------------*");
			System.out.println("Orginal Höhe der Karte: "+  see.getHeight());
			System.out.println("Neue Höhe der Karte: "+HEIGHT);
			System.out.println("Orgnial Breite der Karte: "+see.getWidth());
			System.out.println("Neue Breite der Karte: " +WIDTH);
			System.out.println("*---------------------------------------------------------*");
            
            // Windpark C Array befühlen
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
			System.out.println("Neuen Agent erzeugen");
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
		addAgents(Orte_Koordinaten.getPointAt(0),1);
		addAgents(Orte_Koordinaten.getPointAt(2),2);
		
		
		agents.setMBR(see.getMBR());
		
	}
	
	public static void main (String[] args){
		doLoop(Simulation.class, args);
		System.exit(0);
	}
}
