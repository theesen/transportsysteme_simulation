package daten;

/**
 * Speichert die Aufträge ab, damit sie abgearbeitet werden können.
 * @author thees
 *
 */
public class Auftraege_Auftraege extends Tabelle_Auftrag_Auftraege {
	
	private Object[][] auftraege_daten = getDatei_array();
	
	public String getAuftrag_Location(int auftragsnummer){
		return (String) auftraege_daten[auftragsnummer][1];
	}
	
	public int getAuftrag_Aufenthaltszeit(int auftragsnummer){
		return (Integer) auftraege_daten[auftragsnummer][2];
	}
	
	/**
	 * 
	 * @param row Zeile des gesuchten Objektes
	 * @param col Spalte des gesuchten Objektes
	 * @return gibt den Wert in dem gesuchten Feld wieder. Beim Aufruf der Methode muss angegeben werden, welcher Datentyp gesucht wird.
	 */
	public Object getValueAt_Auftraege(int row, int col){
		return auftraege_daten[row][col];
	}
	

}
