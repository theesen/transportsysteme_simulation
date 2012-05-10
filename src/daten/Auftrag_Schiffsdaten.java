package daten;

public class Auftrag_Schiffsdaten extends Tabelle_Auftrag_Schiffsdaten{
	
	private Object[][] ships = getDatei_array();
	
	public String[] getShip_Ship_Names(){
		String[] result = new String[ships.length];
		
		for (int i = 0; i < ships.length; i++){
			result [i] = (String) ships[i][0];
		}
		return result;
	}
	
	

}
