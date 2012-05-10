package main.java;

import java.util.ArrayList;

import daten.Auftrag_Schiffsdaten;
import daten.Tabelle_Auftrag_Schiffsdaten;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Double2D;
import sim.util.MutableDouble2D;

public class Ship implements Steppable{
	
	private String name;
	
	private String heimathafen;
	
	private MutableDouble2D location;

	private int geschwindigkeit_Revierfahrt = 0;
	
	private int geschwindigkeit_Marschfahrt= 0;
	
	
	
	
	public Ship (String shipname){
				
				name = null;
				heimathafen = null;
				location = null;
				geschwindigkeit_Revierfahrt = null;
				geschwindigkeit_Marschfahrt = null;
				
		
	}
	
	

	public void step(SimState arg0) {
		// TODO Auto-generated method stub
		
	}

}
