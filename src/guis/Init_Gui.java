/**
 * 
 */
package guis;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import gui_komponenten.Gui_Tabellen;

import javax.swing.*;

import main.java.StudentsWithUI;

import java.awt.*;
//Event brauchen wir f�r das Ereigniss, wenn ein Button geklickt wird
import java.awt.event.*;

/**
 * @author crossness
 * 
 */
public class Init_Gui extends JFrame {

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JPanel panelButton;
	private JLabel oben;
	private static JLabel anzeige;

	public static void addComponentsToPane(Container pane) {
		
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		JPanel pane2 = new JPanel();
		pane2.setLayout(new BoxLayout(pane2, BoxLayout.X_AXIS));
		
		
		addAButton("Starte Simulation", pane2, "action1");
		addAButton("Schiffsdaten laden", pane2, "action2");
		addAButton("Auftragsdaten laden", pane2, "action3");
		addAButton("lala", pane2, "action4");
		addAButton("Beenden", pane2, "action5");
		

		Gui_Tabellen newContentPane = new Gui_Tabellen();
		pane.add(newContentPane);
		pane.add(pane2);
	}

	
	 private static void addButtonListener(JButton b)
	    {
	        b.addActionListener(new ActionListener() 
	        { 
	            public void actionPerformed(ActionEvent ae) 
	            { 
	                StudentsWithUI.main(null);
	            }
	        });
	    }

	      
	
	
	
	
	
	
	
	private static void addAButton(String text, Container container, String action) {
		JButton button = new JButton(text);
		button.setActionCommand(action);
		
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Windpark");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, screenSize.width, screenSize.height);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		  if(e.getActionCommand().equals("action1")) {
		    
			  StudentsWithUI.main(null);
			  
			  
		  }
		  if(e.getActionCommand().equals("action2")) {
		    //TODO: add Stuff for jBtnButton2
		  }
	}
	
	
	
	

	public static void main() {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}