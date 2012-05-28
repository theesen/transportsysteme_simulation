	package main.java;
	
import sim.display.*;
import sim.portrayal.simple.*;
import sim.portrayal.geo.GeomVectorFieldPortrayal;
import sim.engine.*;
import java.awt.Color;
import javax.swing.*;
import sim.util.gui.SimpleColorMap;


/**
 *  The display GUI for the ColorWorld GeoMASON example.  Much of this file is similar to other 
 *  MASON GUI code.  The only exception is that we use our custom TestWorldPortrayal for the 
 *  voting districts to handle the shading.    
 *
 */

public class NordseeWithGUI extends GUIState {

    Display2D display;
    JFrame displayFrame;

    // our data is vector format, not raster.
    GeomVectorFieldPortrayal countyPortrayal = new GeomVectorFieldPortrayal();
    GeomVectorFieldPortrayal agentPortrayal = new GeomVectorFieldPortrayal();

    public NordseeWithGUI(SimState state)
    {
        super(state);
    }

    public NordseeWithGUI()
    {
        super(new Nordsee(System.currentTimeMillis()));
    }

    public static String getName() { return "GeoMASON: Nordsee"; }
    public Object getSimulationInspectedObject() { return state; }

    public void init(Controller controller)
    {
        super.init(controller);

        display = new Display2D(Nordsee.WIDTH, Nordsee.HEIGHT, this);

        display.attach(countyPortrayal, "Windanlagen");
        display.attach(agentPortrayal, "Agents");

        displayFrame = display.createFrame();
        controller.registerFrame(displayFrame);
        displayFrame.setVisible(true);
    }

    public void quit()
    {
        super.quit();

        if (displayFrame!=null) displayFrame.dispose();
        displayFrame = null;
        display = null;
    }

    public void start()
    {
        super.start();
        setupPortrayals();
    }

    private void setupPortrayals()
    {
        Nordsee world = (Nordsee)state;

        agentPortrayal.setField(world.agents);
        agentPortrayal.setPortrayalForAll(new OvalPortrayal2D(Color.RED,6.0));

        // the county portrayal (ie, the voting districts) to use our custom portrayal 
        countyPortrayal.setField(world.see);
        countyPortrayal.setPortrayalForAll(new NordseePortrayal(
        		new SimpleColorMap(0.0, Nordsee.NUM_AGENTS, Color.WHITE, Color.BLUE))); 

        display.reset();
        display.setBackdrop(Color.WHITE); 
        display.repaint();
    }

    public static void main(String[] args)
    {
    	NordseeWithGUI worldGUI = new NordseeWithGUI();
        Console console = new Console(worldGUI);
        console.setVisible(true);
    }

}
