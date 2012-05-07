
package gui_komponenten;

/*
 * Gui_Tabellen.java requires no other files.
 */

import guis.Starte_Gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import daten.Tabelle_Auftrag_Allgemein;
import daten.Tabelle_Auftrag_Auftraege;

import java.awt.Dimension;
import java.awt.GridLayout;

/** 
 * Gui_Tabellen is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */




public class Gui_Tabellen extends JPanel {
	
	
	
	
public static 	Object[][] datei_array_auftrag_allgemein_temp; 
public	static String[] spalten_namen_allgemein_temp;  
public static	Object[][] datei_array_auftrag_auftraege_temp;  
public static	String[] spalten_namen_auftraege_temp; 
	
	
	
	
	
	
	
	
	
	
	
	

    private boolean DEBUG = false;

    public Gui_Tabellen() {
        super(new GridLayout(1,2));

        Tabelle_Auftrag_Allgemein tablemodel = new Tabelle_Auftrag_Allgemein();
        tablemodel.setSpalten_namen(spalten_namen_allgemein_temp);
        tablemodel.setDatei_array(datei_array_auftrag_allgemein_temp);
        
        
        JTable table = new JTable(tablemodel);
        table.setPreferredScrollableViewportSize(new Dimension(800, 800));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        
        if (spalten_namen_auftraege_temp!=null){
        	Tabelle_Auftrag_Auftraege tablemodel2= null;
            tablemodel2 = new Tabelle_Auftrag_Auftraege();
            tablemodel2.setSpalten_namen(spalten_namen_auftraege_temp);
            tablemodel2.setDatei_array(datei_array_auftrag_auftraege_temp);
            
            JTable table2 = new JTable(tablemodel2);
            table2.setPreferredScrollableViewportSize(new Dimension(800, 800));
            table2.setFillsViewportHeight(true);
            JScrollPane scrollPane2 = new JScrollPane(table2);
            add(scrollPane2);
        }
        
        
        
        
        
   
        
        

        
    }

   

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    public static void main(Object[][] datei_array_auftrag_allgemein, String[] spalten_namen_allgemein, Object[][] datei_array_auftrag_auftraege, String[] spalten_namen_auftraege) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	
    	
    
    	datei_array_auftrag_allgemein_temp =datei_array_auftrag_allgemein;
    	spalten_namen_allgemein_temp = spalten_namen_allgemein;
    	
    	
    	datei_array_auftrag_auftraege_temp =  datei_array_auftrag_auftraege;
    	spalten_namen_auftraege_temp = spalten_namen_auftraege; 
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

        	
        	
        	public void run() {
            	Starte_Gui.main();
            }
        });
    }
}
