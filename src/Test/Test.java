package Test;

import java.io.FileNotFoundException;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

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
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	
	public static int NUM_AGENTS = 1;
	
	public GeomVectorField see = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField agents = new GeomVectorField(WIDTH, HEIGHT);
	
	public int getNumAgents(){ return NUM_AGENTS;}
	
	public void setNumAgents(int a){ if (a>0) NUM_AGENTS = a;}
	
	public Test (long seed){
		super (seed);
		
		try {
			ShapeFileImporter importer = new ShapeFileImporter();
			importer.ingest("/shapefila_data/A_Punkte_Shape/A_Punkte", Test.class, see);
			
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
			
			 MasonGeometry targetPoint = ((MasonGeometry)allgeometries.objs[random.nextInt(allgeometries.numObjs)]);
			
			a.setLocation((Point) targetPoint.geometry);
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
