package main.java;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;
import sim.util.geo.*;

import sim.engine.SimState;
import sim.engine.Steppable;

public class Ship implements Steppable{

	private static final long serialVersionUID = 23417612959591011L;

	private String name;
	
	private String heimathafen;
	
	private Point location;
	
	private Point nextTarget;
	
	PointMoveTo pointMoveTo= new PointMoveTo();

	private int geschwindigkeit_Revierfahrt;
	
	private int geschwindigkeit_Marschfahrt;
	
	public Ship (){
		
	}
	
	public void start(){
		
	}
	
	public void chooseNewTask(Windpark_simulation test){ // Angelehnt an die Agent.findNewPath aus dem GeoMason bsp.
		// lese den naechsten Auftrag aus.
		Point currentLocation = location;
		
		if (currentLocation == nextTarget){
			
		}
		
	}
	
	public void setNewRoute(){
		
	}
	
	// Bewegt den Agenten zu der uebergebenen Koordinate "c"!
	public void moveTo (Coordinate c){
		pointMoveTo.setCoordinate(c);
		location.apply(pointMoveTo);
		
	}
	
	public void step(SimState arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
//	public Ship (String shipname, Object[][] shiplist){
//
//		for (int i = 0; i < shiplist.length; i++){
//			if (shiplist[i][0].equals(shipname)){
//				
//				name = (String) shiplist[i][0];
//				heimathafen = (String) shiplist[i][1];
//				geschwindigkeit_Marschfahrt = Integer.parseInt((String)shiplist[i][3]);
//				geschwindigkeit_Revierfahrt = Integer.parseInt((String) shiplist[i][2]);
//			}
//		}
//								
//				standort_coordinate = null;		
//		
//	}
	
	public String DebugInfo(){
		String result = "Das Schiff hat folgende Daten:\n";
		result += "Schiffsname: "+name+"\n"+"Heimathafen: "+heimathafen+"\n"+"Geschwindigkeit Revier: "+geschwindigkeit_Revierfahrt+"\n"+"Geschwindigkeit Marschfahrt: "+geschwindigkeit_Marschfahrt+"\n";
		return result;
	}





}
