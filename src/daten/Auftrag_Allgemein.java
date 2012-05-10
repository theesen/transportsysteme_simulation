package daten;

/**
 * Speichert die Daten aus der JTable in ein normales Array. Die Klasse dient nur dazu damit die Daten aus den Arrays abgefragt werden können.
 * @author thees
 *
 */
public class Auftrag_Allgemein extends Tabelle_Auftrag_Allgemein {
	
	private Object[][] allgemeine_auftraege = getDatei_array();
	
	public int getAuftragsstartzeit(int auftragsnummer){
		return (Integer) allgemeine_auftraege[auftragsnummer][2];
	}
	
	public String getAuftrags_Starthafen(int auftragsnummer){
		return (String) allgemeine_auftraege[auftragsnummer][3];
	}
	
	public String getAuftrags_Endhafen (int auftragsnummer){
		return (String) allgemeine_auftraege[auftragsnummer][4];
	}
	
	
	
	/**
	 * 
	 * @param row Zeile des gesuchten Objektes
	 * @param col Spalte des gesuchten Objektes
	 * @return gibt den Wert in dem gesuchten Feld wieder. Beim Aufruf der Methode muss angegeben werden, welcher Datentyp gesucht wird.
	 */
	public Object getValueAt_allgemein(int row, int col){
		return allgemeine_auftraege[row][col];
	}
	
	

}
