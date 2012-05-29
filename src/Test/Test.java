package test;

import java.io.FileNotFoundException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
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
	private static final long serialVersionUID = 2499844612548898662L;
	public static final int WIDTH = 360;
	public static final int HEIGHT = 142;
	
	public static int NUM_AGENTS = 1;
	
	public GeomVectorField see = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField agents = new GeomVectorField(WIDTH, HEIGHT);
	
	public int getNumAgents(){ return NUM_AGENTS;}
	
	public void setNumAgents(int a){ if (a>0) NUM_AGENTS = a;}
	
	public Test (long seed){
		super (seed);
		
		try {
			GeoToolsImporter importer = new GeoToolsImporter();
			//ShapeFileImporter importer = new ShapeFileImporter();
			importer.ingest("/A_Punkte_Shape/ne_50m_admin_0_countries.shp", Test.class, see);
			
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
			
			 MasonGeometry targetPoint = ((MasonGeometry)allgeometries.objs[238]);
			
			System.out.println( targetPoint.geometry.getInteriorPoint()); 
			System.out.println( targetPoint.geometry.getCoordinate());
			System.out.println( targetPoint.geometry.getCentroid());
			System.out.println((Point) targetPoint.geometry.getCentroid());
			Coordinate  test = new Coordinate  (6.6251,53.9908);
			//a.setLocation((Point) targetPoint.geometry.getCentroid());

			//test3.apply(filter);
			MasonGeometry test5 = new MasonGeometry();
			AffineTransformation translate = null;
	
			 a.setLocation(targetPoint.geometry.getCentroid());
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
