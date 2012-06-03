/**
 * 
 */
package jobs;

import daten.Tabelle_Auftrag_Schiffsdaten;

/**
 * @author patrick
 *
 */
public class Schiffsdaten_Auslesen {

	/**
	 */
	public static void main() {
		

		
	String aktuelles_verzeichnis = Datei_Auswaehler.main(new String[] {"schiffe.txt", "Schiffsdaten"});		
		
	Object[][] datei_array = Datei_Importierer.main(new String[]{aktuelles_verzeichnis});		
	
	
	String[]spalten_namen = {"Schiffsname","Heimathafen"," Geschwindigkeit_Revier", "Marschfahrt"};
	

	Tabelle_Auftrag_Schiffsdaten table_object_Auftrag_Schiffsdaten = new Tabelle_Auftrag_Schiffsdaten();
	table_object_Auftrag_Schiffsdaten.setSpalten_namen(spalten_namen);
	table_object_Auftrag_Schiffsdaten.setDatei_array(datei_array);
	
		
		
		
		
	}

}
