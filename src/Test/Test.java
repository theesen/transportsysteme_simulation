package Test;

import java.io.FileNotFoundException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.util.AffineTransformation;

import sim.app.geo.campusworld.CampusWorld;
import sim.engine.SimState;
import sim.field.geo.GeomVectorField;
import sim.io.geo.ShapeFileImporter;
import sim.util.Bag;
import sim.util.geo.MasonGeometry;

public class Test extends SimState {
	
	/**
	 * 
	 */
	 private Point location;
	
	private static final long serialVersionUID = 2499844612548898662L;
	
	// vergroesserungs_faktor schon mit eingerichtet sonst Width = 12 und Height = 10
	public static int WIDTH = 1200; 
	public static int HEIGHT = 1000;
	
	public static int vergroesserungs_faktor = 100; 
	
	public static int NUM_AGENTS = 1;
	
	public GeomVectorField see = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField agents = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField orte = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField c_punkte = new GeomVectorField(WIDTH, HEIGHT);
	
	
	
	
	
	public int getNumAgents(){ return NUM_AGENTS;}
	
	public void setNumAgents(int a){ if (a>0) NUM_AGENTS = a;}
	
	public Test (long seed){
		super (seed);
		
		try {
			GeoToolsImporter importer = new GeoToolsImporter();
			//ShapeFileImporter importer = new ShapeFileImporter();
			
			 // read in the see GIS file 
			importer.ingest("../ne_50m_admin_0_countries.shp", Test.class, see,null);
			
			 // read in the orte GIS file 
			System.out.println("reading orte layer");
            importer.ingest("../Orte.shp", Test.class, orte, null);
				
            
            System.out.println("reading c punkte layer");
            importer.ingest("../C_Punkte.shp", Test.class, c_punkte, null);
            
            
            Envelope MBR = see.getMBR();
            
			
            MBR.expandToInclude(orte.getMBR());
            MBR.expandToInclude(c_punkte.getMBR());
			
            System.out.println("Done reading data");
            
            /* Höhe raus kriegen */
			//HEIGHT=(int) see.getHeight();
			System.out.println("Orginal Höhe "+  see.getHeight());
			//System.out.println("Orginal Höhe "+  HEIGHT);
		//	HEIGHT=HEIGHT;
			System.out.println("Neue Höhe "+HEIGHT);
			//WIDTH=(int) see.getWidth();
			System.out.println("Orgnial Breite "+see.getWidth());
		//	System.out.println("Orgnial Breite "+WIDTH);
		//	WIDTH=WIDTH;
			System.out.println("Neue Breite " +WIDTH);
          //see.computeConvexHull();
          //  
            
            see.setMBR(MBR);
            orte.setMBR(MBR);
            c_punkte.setMBR(MBR);
            
            
            // Info über Windpark C
            
            Bag windpark_C_koordinaten = c_punkte.getGeometries();
            
            int anzahl = windpark_C_koordinaten.objs.length;
            System.out.println("Anzahl Windräder " + anzahl);
            MasonGeometry targetPoint = ((MasonGeometry)windpark_C_koordinaten.objs[4]);
            System.out.println( "Windrad 4 von Windpark C hat Koordinate: " +targetPoint.geometry.getInteriorPoint());
            
            
            
            
            
          
            
            
            
            
            
            
            
		}
		catch(FileNotFoundException ex){
			System.out.println("Error opening shapefile!"+ex);
			System.exit(-1);
		}
	}
	
	private void addAgents(){
		for (int i = 0; i < NUM_AGENTS; i++){
			Agent a = new Agent(1);
			
			
			
			
			
			
			
			Bag allgeometries = c_punkte.getGeometries();
			
			if (allgeometries.isEmpty()){
				throw new RuntimeException("Keine Geometrien gefunden!");
			}
			
			
			// MasonGeometry targetPoint = ((MasonGeometry)allgeometries.objs[4]);
			MasonGeometry targetPoint = ((MasonGeometry)allgeometries.objs[24]);
//			System.out.println( targetPoint.geometry.getInteriorPoint()); 
//			System.out.println( targetPoint.geometry.getCoordinate());
		//	System.out.println( "Punkt setzen auf " +targetPoint.geometry.getInteriorPoint());
		//	System.out.println((Point) targetPoint.geometry.getCentroid());
			Point ziel_koordinate = targetPoint.geometry.getCentroid();
		 		double ziel_x = ziel_koordinate.getX();
		 		double ziel_y = ziel_koordinate.getY();
		//	a.setLocation((Point) targetPoint.geometry.getInteriorPoint());

		 		
		 		
		 		double ziel_neu_x = ((ziel_x-2.524941847350391)-(0.458069661500247/2))*vergroesserungs_faktor;
				double ziel_neu_y = ((ziel_y-47.278819891949524)-(0.612150065154651/2))*vergroesserungs_faktor;
		 		
		 		
					System.out.println("Neue Korrdinaten: x="+ziel_neu_x+" y="+ziel_neu_y);
 //5.6				
			GeometryFactory fact = new GeometryFactory();
			location = fact.createPoint(new Coordinate(ziel_neu_x,ziel_neu_y));
			System.out.println("Location: "+location);
//			MasonGeometry mg = new MasonGeometry(standort_koordinate); 
//            mg.isMovable = true; 
//            agents.addGeometry(mg);
            
			
			
			a.setLocation(location);
		//	System.out.println(a.getGeometry());
			 
			 
			agents.addGeometry(new MasonGeometry(a.getGeometry()));
			schedule.scheduleRepeating(a);
		}
	}
	
	public void start(){
		super.start();
		agents.clear();
		addAgents();
		agents.setMBR(see.getMBR());
		
	}
	
	public static void main (String[] args){
		doLoop(Test.class, args);
		System.exit(0);
	}
}
