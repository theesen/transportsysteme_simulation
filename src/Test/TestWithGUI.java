package test;

import java.awt.Color;
import java.awt.Paint;

import javax.swing.JFrame;

import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;

import sim.portrayal.SimplePortrayal2D;
import sim.portrayal.geo.GeomVectorFieldPortrayal;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.gui.SimpleColorMap;

public class TestWithGUI extends GUIState{
	
	Display2D display;
	JFrame displayFrame;
	
	
	GeomVectorFieldPortrayal landPortrayal = new GeomVectorFieldPortrayal();
	GeomVectorFieldPortrayal agentPortrayal = new GeomVectorFieldPortrayal();
	
	public TestWithGUI(SimState state){
		super(state);
	}
	
	public TestWithGUI(){
		super(new Test(System.currentTimeMillis()));
	}
	
	public static String getName(){ return "GeoMason: Test";}
	public Object getSimulationInspectedObject(){ return state;}
	
	public void init (Controller controller){
		super.init(controller);
		
		display = new Display2D(Test.WIDTH, Test.HEIGHT, this);
		display.attach(landPortrayal, "Landschaft");
		display.attach(agentPortrayal, "Agenten");
	
		
		displayFrame = display.createFrame();
		controller.registerFrame(displayFrame);
		displayFrame.setVisible(true);
		
	}
	
	public void quit(){
		super.quit();
		
		if (displayFrame != null) displayFrame.dispose();
		displayFrame = null;
		display = null;
	}
	
	public void start(){
		super.start();
		setupPortrayals();
		
	}
	
	private void setupPortrayals(){
		Test welt = (Test)state;
		
		agentPortrayal.setField(welt.agents);
		agentPortrayal.setPortrayalForAll(new OvalPortrayal2D(Color.RED, 6.0));
		
		landPortrayal.setField(welt.see);
		landPortrayal.setPortrayalForAll(new SimplePortrayal2D());
		
		display.reset();
		display.setBackground(Color.WHITE);
		display.repaint();
	}
	
	public static void main (String[] args){
		TestWithGUI testGUI = new TestWithGUI();
		Console console = new Console(testGUI);
		console.setVisible(true);
		
	}
	
	

}
