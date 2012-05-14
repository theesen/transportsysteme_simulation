package daten;

/**
 * Speichert die Aufträge ab, damit sie abgearbeitet werden können.
 * @author thees
 *
 */
public class Auftrag_Auftraege extends Tabelle_Auftrag_Auftraege {
	
	private Object[][] auftraege_daten = getDatei_array();
	
	public Object [][] getAuftraege_array(){
		return auftraege_daten;
	}
	
	public StringBuffer getAuftrag_id_List(){
		StringBuffer result = new StringBuffer("Die folgenden IDs existieren:\n");
		for (int i = 0; i < auftraege_daten.length; i++){
			result.append((Integer) auftraege_daten[i][0] + "; ");
		}
		return result;
	}
	
	public String getAuftrag_Location(int auftragsnummer){
		return (String) auftraege_daten[auftragsnummer][1];
	}
	
	public String getAuftrag_Aufenthaltszeit(int auftragsnummer){
		return (String) auftraege_daten[auftragsnummer][2];
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
