/**
 * 
 */
package simulation_berechnungen;

import java.util.Arrays;

import daten.Auftrag_Allgemein;
import daten.Tabelle_Auftrag_Schiffsdaten;

/**
 * @author crossness
 *
 */
public class Auftraege_Verteilen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 Object[][]datei_array= new Object[1000][3];     
		
		 
		 
		 
		 datei_array = Auftrag_Allgemein.getDatei_array();
		 
		//System.out.println(table_object_Auftrag_Schiffsdaten.getRowCount());
		
		for (Object[] arr : datei_array) {
            System.out.println(Arrays.toString(arr));
	 }
		
	}

}
