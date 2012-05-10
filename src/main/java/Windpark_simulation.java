package main.java;

import guis.Init_Gui;

import java.util.Arrays;

import daten.Tabelle_Auftrag_Allgemein;
import daten.Tabelle_Auftrag_Auftraege;
import daten.Tabelle_Auftrag_Schiffsdaten;
import jobs.Auftragsdaten_Auslesen;
import jobs.Schiffsdaten_Auslesen;


public class Windpark_simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Auftragsdaten_Auslesen.main();
		Schiffsdaten_Auslesen.main();
		System.out.println("Wir sind in windpark simulation");
		
		Init_Gui.main();
		
		
		
		
		Object[][] test = Tabelle_Auftrag_Allgemein.getDatei_array();
		Object[][] test2 = Tabelle_Auftrag_Auftraege.getDatei_array();
		Object[][] test3 = Tabelle_Auftrag_Schiffsdaten.getDatei_array();
		
		// Aufträge für die jeweiligen Auftragsdaten
				 for (Object[] arr :  test) {
			            System.out.println(Arrays.toString(arr));
		}	
				 
				// Aufträge für die jeweiligen Auftragsdaten
				 for (Object[] arr :  test2) {
			            System.out.println(Arrays.toString(arr));
		}			 
				 
					// Aufträge für die jeweiligen Auftragsdaten
				 for (Object[] arr :  test3) {
			            System.out.println(Arrays.toString(arr));
		}
		
		
				 
	}
	

}
