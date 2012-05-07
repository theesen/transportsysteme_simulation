/**
 * 
 */
package jobs;

import gui_komponenten.Gui_Tabellen;

/**
 * @author patrick
 *
 */
public class Schiffsdaten_Auslesen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	String aktuelles_verzeichnis = Datei_Auswaehler.main(new String[] {"schiffe.txt", "Schiffsdaten"});		
		
	Object[][] datei_array = Datei_Importierer.main(new String[]{aktuelles_verzeichnis});		
	
	
	String[]spalten_namen = {"Heimathafen","Geschwindigkeit Revier"," Geschwindigkeit", "Marschfahrt"};
	

	Gui_Tabellen.main(datei_array, spalten_namen, null, null);
	
		
		
		
		
	}

}
