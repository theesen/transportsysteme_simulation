/**
 * 
 */
package guis;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import gui_komponenten.Gui_Tabellen;

import javax.swing.*;
import java.awt.*;
//Event brauchen wir für das Ereigniss, wenn ein Button geklickt wird
import java.awt.event.*;
/**
 * @author crossness
 *
 */
public class Starte_Gui extends JFrame {


private JButton button1;
private JButton button2;
private JButton button3;
private JPanel panelButton;
private JLabel oben;
private JLabel anzeige;

		       
		        
		       
		        
		        
		        public static void addComponentsToPane(Container pane) {
		            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		     		            
		            JPanel pane2 = new JPanel();
		            pane2.setLayout(new BoxLayout(pane2, BoxLayout.X_AXIS));
		            
		            addAButton("Button 1", pane2);
		            addAButton("Button 2", pane2);
		            addAButton("Button 3", pane2);
		            addAButton("Long-Named Button 4", pane2);
		            addAButton("5", pane2);
		            
		            Gui_Tabellen newContentPane = new Gui_Tabellen();
		          
		              
		            pane.add(newContentPane);
		            pane.add(pane2);
		        }
		     
		        private static void addAButton(String text, Container container) {
		            JButton button = new JButton(text);
		            button.setAlignmentX(Component.CENTER_ALIGNMENT);
		            container.add(button);
		        }
		     
		        /**
		         * Create the GUI and show it.  For thread safety,
		         * this method should be invoked from the
		         * event-dispatching thread.
		         */
		        private static void createAndShowGUI() {
		            //Create and set up the window.
		            JFrame frame = new JFrame("Windpark");
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		     
		           // frame.setLayout(new BorderLayout(2,1));
				     
		           
		          
		            
		            
		            
		            //Set up the content pane.
		            addComponentsToPane(frame.getContentPane());
		            
		            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		            frame.setBounds(0,0,screenSize.width, screenSize.height);
		            //Display the window.
		            frame.pack();
		            frame.setVisible(true);
		        }
		     
		        public static void main() {
		            //Schedule a job for the event-dispatching thread:
		            //creating and showing this application's GUI.
		            javax.swing.SwingUtilities.invokeLater(new Runnable() {
		                public void run() {
		                    createAndShowGUI();
		                }
		            });
		        }		        
		        
		        
		        
		     

	}


