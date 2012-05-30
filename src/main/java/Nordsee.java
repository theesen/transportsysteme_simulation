package main.java;

import java.io.FileNotFoundException;
import java.util.WeakHashMap;
import com.vividsolutions.jts.geom.Geometry;

import sim.app.geo.colorworld.CountingGeomWrapper;
import sim.engine.*;
import sim.field.geo.*;
import sim.io.geo.ShapeFileImporter;
import sim.util.Bag;
import sim.util.geo.*;


public class Nordsee extends SimState
{
    private static final long serialVersionUID = -2568637684893865458L;


	public static final int WIDTH = 300; 
	public static final int HEIGHT = 300; 

	// number of agents in the Simulation
    public static int NUM_AGENTS = 1;

    // where all the county geometry lives
    public GeomVectorField see = new GeomVectorField(WIDTH, HEIGHT);

    // where all the agents live.  We use a GeomVectorField since we want to determine how 
    // many agents are inside each district.  The most efficient way to do this is via 
    // the GeomVectorField's spatial indexing.  
    public static GeomVectorField agents = new GeomVectorField(WIDTH, HEIGHT);

    // getters and setters for inspectors
    public int getNumAgents() { return NUM_AGENTS; }
    public void setNumAgents(int a) { if (a > 0) NUM_AGENTS = a; }

    
    //public WeakHashMap<Geometry, Agent> regionCnt = new WeakHashMap<Geometry, Agent>(); 
    public WeakHashMap regionCnt = new WeakHashMap(); 
    
    public Nordsee(long seed)
    {
        super(seed);

        try
            {
                // Open simple Shape file of county.
                ShapeFileImporter importer = new ShapeFileImporter();

                // this line allows us to replace the standard MasonGeometry with our
                // own subclass of MasonGeometry; see CountingGeomWrapper.java for more info.
                // Note: this line MUST occur prior to ingesting the data
                importer.masonGeometryClass = CountingGeomWrapper.class;

                importer.ingest( "/pol", Nordsee.class, see, null);
            }
        catch (FileNotFoundException ex)
            {
                System.out.println("Error opening shapefile!" + ex);
                System.exit(-1);
            }

        // we use either the ConvexHull or Union to determine if the agents are within
        // Fairfax county or not
        see.computeConvexHull();
        see.computeUnion();

    }

    private void addAgents()
    {
        //Agent a = null;

        for (int i = 0; i < NUM_AGENTS; i++)
            {
                // pick a random political region to plop the agent in
                Bag allRegions = see.getGeometries();

                if (allRegions.isEmpty())
                    {
                        // Something went wrong.  We *should* have regions.
                        throw new RuntimeException("No regions found.");
                    }
                MasonGeometry region = ((MasonGeometry)allRegions.objs[random.nextInt(allRegions.numObjs)]);
           
                // give each agent a random direction to initially move in
                Agent a = new Agent(random.nextInt(8));

                // set each agent in the center of corresponding region
                a.setLocation(region.getGeometry().getCentroid());

                // place the agents in the GeomVectorField
                agents.addGeometry(new MasonGeometry(a.getGeometry()));

                // add the new agent the schedule
                schedule.scheduleRepeating(a);
            }        
    }



    public void start()
    {
        super.start();
 
        agents.clear(); // remove any agents from previous runs

        // add agents to the Simulation
        addAgents();

        // ensure both GeomFields Color same area
        agents.setMBR(see.getMBR());
    }

    public static void main(String[] args)
    {
        doLoop(Nordsee.class, args);
        System.exit(0);
    }
}

