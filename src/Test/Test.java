package Test;

import java.io.FileNotFoundException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.util.AffineTransformation;

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
	   public static int WIDTH = 400; 
	    public static int HEIGHT = 480;
	
	public static int NUM_AGENTS = 1;
	
	public GeomVectorField see = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField agents = new GeomVectorField(WIDTH, HEIGHT);
	
	public int getNumAgents(){ return NUM_AGENTS;}
	
	public void setNumAgents(int a){ if (a>0) NUM_AGENTS = a;}
	
	public Test (long seed){
		super (seed);
		
		try {
			//GeoToolsImporter importer = new GeoToolsImporter();
			ShapeFileImporter importer = new ShapeFileImporter();
			importer.ingest("../ne_50m_admin_0_countries", Test.class, see);
			
//			HEIGHT=(int) see.getHeight();
//			HEIGHT=HEIGHT*20;
//			System.out.println(HEIGHT);
//			WIDTH=(int) see.getWidth();
//			WIDTH=WIDTH*20;
//			System.out.println(WIDTH);
		}
		catch(FileNotFoundException ex){
			System.out.println("Error opening shapefile!"+ex);
			System.exit(-1);
		}
	}
	
	private void addAgents(){
		for (int i = 0; i < NUM_AGENTS; i++){
			Agent a = new Agent(1);
			Bag allgeometries = see.getGeometries();
			
			if (allgeometries.isEmpty()){
				throw new RuntimeException("Keine Geometrien gefunden!");
			}
			
			 MasonGeometry targetPoint = ((MasonGeometry)allgeometries.objs[4]);
			
//			System.out.println( targetPoint.geometry.getInteriorPoint()); 
//			System.out.println( targetPoint.geometry.getCoordinate());
			System.out.println( "Punkt setzen auf" +targetPoint.geometry.getInteriorPoint());
		//	System.out.println((Point) targetPoint.geometry.getCentroid());
		
	//		a.setLocation((Point) targetPoint.geometry.getInteriorPoint());

					
	
			GeometryFactory fact = new GeometryFactory();
			location = fact.createPoint(new Coordinate(121,50));
		
//			MasonGeometry mg = new MasonGeometry(location); 
//            mg.isMovable = true; 
//            agents.addGeometry(mg);
            
			
			
			a.setLocation(location);
			System.out.println(a.getGeometry());
			 
			 
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
