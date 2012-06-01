package simulation;

import javax.swing.JLabel;

import sim.display.Console;
import sim.display.GUIState;

public class Console_Simulation extends Console {

	public Console_Simulation(GUIState arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		int val = 1000;
        long speed = (long)( 512000.0 / (Math.pow(4,5)-1) * ( Math.pow(4,val/1000.0) - 1 ) );
		setPlaySleep(speed);
		JLabel sliderText = null;
		sliderText = new JLabel("0.0");
		sliderText.setText("" + ((double) (speed)) / 1000);
		
		
	}

}
