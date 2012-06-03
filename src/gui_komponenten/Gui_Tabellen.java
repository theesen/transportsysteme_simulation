
package gui_komponenten;

/*
 * Gui_Tabellen.java requires no other files.
 */

import guis.Init_Gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import daten.Tabelle_Auftrag_Allgemein;
import daten.Tabelle_Auftrag_Auftraege;
import daten.Tabelle_Auftrag_Schiffsdaten;

/** 
 * Gui_Tabellen is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */




public class Gui_Tabellen extends JPanel {
	
	

    private boolean DEBUG = false;

    public Gui_Tabellen() {
        super(new GridLayout(1,1));        
        
        JPanel pane_tabellen = new JPanel();
		pane_tabellen.setLayout(new BoxLayout(pane_tabellen, BoxLayout.Y_AXIS));
        
        
        
        JTable table_auftrag_allgemein = new JTable(new Tabelle_Auftrag_Allgemein());
        table_auftrag_allgemein.setPreferredScrollableViewportSize(new Dimension(800, 800));
        table_auftrag_allgemein.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table_auftrag_allgemein);
        JLabel label = new JLabel("Auftragsdaten Allgemein", JLabel.CENTER);
        
        //Add the scroll pane to this panel.
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane_tabellen.add(label);
        pane_tabellen.add(scrollPane);
        
    	JTable table_auftrag_auftraege = new JTable(new Tabelle_Auftrag_Auftraege());
        table_auftrag_auftraege.setPreferredScrollableViewportSize(new Dimension(800, 800));
        table_auftrag_auftraege.setFillsViewportHeight(true);
        JScrollPane scrollPane2 = new JScrollPane(table_auftrag_auftraege);
        JLabel label2 = new JLabel("Auftragsdaten Auftraege", JLabel.CENTER);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane_tabellen.add(label2);
        pane_tabellen.add(scrollPane2);
        
        
        JTable table_auftrag_schiffsdaten = new JTable(new Tabelle_Auftrag_Schiffsdaten());
        table_auftrag_schiffsdaten.setPreferredScrollableViewportSize(new Dimension(800, 800));
        table_auftrag_schiffsdaten.setFillsViewportHeight(true);
        JScrollPane scrollPane3 = new JScrollPane(table_auftrag_schiffsdaten);
        JLabel label3 = new JLabel("Schiffsdaten", JLabel.CENTER);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane_tabellen.add(label3);
        
        pane_tabellen.add(scrollPane3);
        
        add(pane_tabellen);
        
        
        
        }
       
    



    public static void main() {
  
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

        	
        	
        	public void run() {
            	Init_Gui.main();
            }
        });
    }
}
