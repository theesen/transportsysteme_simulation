package main.java;



import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Double2D;
import sim.util.MutableDouble2D;

public class Ship implements Steppable{
	
	private String name;
	
	private String heimathafen;
	
	private MutableDouble2D location;

	private int geschwindigkeit_Revierfahrt = 0;
	
	private int geschwindigkeit_Marschfahrt;
	
	
	public Ship (String shipname, Object[][] shiplist){

		for (int i = 0; i < shiplist.length; i++){
			if (shiplist[i][0].equals(shipname)){
				
				name = (String) shiplist[i][0];
				heimathafen = (String) shiplist[i][1];
				geschwindigkeit_Marschfahrt = Integer.parseInt((String)shiplist[i][3]);
				geschwindigkeit_Revierfahrt = Integer.parseInt((String) shiplist[i][2]);
			}
		}
								
				location = null;		
		
	}
	
	public String DebugInfo(){
		String result = "Das Schiff hat folgende Daten:\n";
		result += "Schiffsname: "+name+"\n"+"Heimathafen: "+heimathafen+"\n"+"Geschwindigkeit Revier: "+geschwindigkeit_Revierfahrt+"\n"+"Geschwindigkeit Marschfahrt: "+geschwindigkeit_Marschfahrt+"\n";
		return result;
	}



	public void step(SimState arg0) {
		// TODO Auto-generated method stub
		
	}

}
