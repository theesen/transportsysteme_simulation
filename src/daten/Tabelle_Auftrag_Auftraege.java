/**
 * 
 */
package daten;

import javax.swing.table.AbstractTableModel;

/**
 * @author crossness
 *
 */
public class Tabelle_Auftrag_Auftraege extends AbstractTableModel{

	/**
	 * @param args
	 */
	public static  String[]spalten_namen= null;
	
	
	
	
	
	public StringBuffer getAuftrag_id_List(){
		StringBuffer result = new StringBuffer("Die folgenden IDs existieren:\n");
		for (int i = 0; i < datei_array.length; i++){
			result.append((Integer) datei_array[i][0] + "; ");
		}
		return result;
	}
	
	public String getAuftrag_Location(int auftragsnummer){
		return (String) datei_array[auftragsnummer][1];
	}
	
	public String getAuftrag_Aufenthaltszeit(int auftragsnummer){
		return (String) datei_array[auftragsnummer][2];
	}
	
	public Object[][] getAuftragsdaten_fuer_Auftragsnummer(int Autragsnummer)
			{
	
			int numRows = datei_array.length;
			int numCols = datei_array[0].length;
			int counter = 0;
			for (int i=0; i < numRows; i++) {
				if (datei_array[i][0].toString().equals(String.valueOf(Autragsnummer))){
					counter = counter+1;				
				}
					
			}	
			
			Object[][]result_array = new Object[counter][numCols];
			counter = 0;
			for (int i=0; i < numRows; i++) {
				if (datei_array[i][0].toString().equals(String.valueOf(Autragsnummer))){
					
				
                for (int j=0; j < numCols; j++) {             	
                	result_array[counter][j] = datei_array[i][j];
                	
                }
                counter = counter+1;
                
				}

            }
        return result_array;
    
    
}
	
	
	
	public  String[] getSpalten_namen() {
		return spalten_namen;
	}

	public void setSpalten_namen(String[] spalten_namen) {
		Tabelle_Auftrag_Auftraege.spalten_namen = spalten_namen;
	}

	public static Object[][] getDatei_array() {
		return datei_array;
	}

	public void setDatei_array(Object[][] datei_array) {
		Tabelle_Auftrag_Auftraege.datei_array = datei_array;
	}



	public static Object[][]datei_array= null;      
             

				public int getColumnCount() {
		            return spalten_namen.length;
		        }

		        public int getRowCount() {
		            return datei_array.length;
		        }

		        public String getColumnName(int col) {
		            return spalten_namen[col];
		        }

		        public Object getValueAt(int row, int col) {
		            return datei_array[row][col];
		        }


		        public Class getColumnClass(int c) {
		            return getValueAt(0, c).getClass();
		        }



		        private void printDebugData() {
		            int numRows = getRowCount();
		            int numCols = getColumnCount();

		            for (int i=0; i < numRows; i++) {
		                System.out.print("    row " + i + ":");
		                for (int j=0; j < numCols; j++) {
		                    System.out.print("  " + datei_array[i][j]);
		                }
		                System.out.println();
		            }
		            System.out.println("--------------------------");
		        }
		   

	

}
