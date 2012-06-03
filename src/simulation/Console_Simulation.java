package simulation;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
		
		JTabbedPane tabPane = getTabPane();
		JPanel outerPane = new JPanel();
        outerPane.setLayout(new BorderLayout());
        
        
        
        
        Box defaults = new Box(BoxLayout.X_AXIS);
        defaults.add(new JLabel(" Save as Defaults for "));
        JButton appPreferences = new JButton("Simulation");
        JButton systemPreferences = new JButton("MASON");
        defaults.add(appPreferences);
        defaults.add(systemPreferences);
        defaults.add(Box.createGlue());
        
        
        
        
        
        outerPane.add(defaults, BorderLayout.SOUTH);
		tabPane.addTab("Windpark reports", outerPane);//Windparkreport adden
		
		
		
	}

}
