/**
 * 
 */
package daten;

import javax.swing.table.AbstractTableModel;

/**
 * @author crossness
 *
 */
public class Tabelle_Auftrag_Allgemein extends AbstractTableModel{

	/**
	 * @param args
	 */
	static String[]spalten_namen= null;
	static Object[][]datei_array= null;     
	
	public  String[] getSpalten_namen() {
		return spalten_namen;
	}

	public void setSpalten_namen(String[] spalten_namen) {
		Tabelle_Auftrag_Allgemein.spalten_namen = spalten_namen;
	}

	public static Object[][] getDatei_array() {
		return datei_array;
	}
	
	
	
	
	public int getAuftragsstartzeit(int auftragsnummer){
		return (Integer) datei_array[auftragsnummer][2];
	}
	
	public String getAuftrags_Starthafen(int auftragsnummer){
		return (String) datei_array[auftragsnummer][3];
	}
	
	public String getAuftrags_Endhafen (int auftragsnummer){
		return (String) datei_array[auftragsnummer][4];
	}
	
	
	
	
	
	
	
	
	
	

	public void setDatei_array(Object[][] datei_array) {
		Tabelle_Auftrag_Allgemein.datei_array = datei_array;
	}

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
		   
		        
		        public Object[][] getAuftraege_fuer_Heimataufen(String heimathafen)
				{
		
				int numRows = datei_array.length;
				int numCols = datei_array[0].length;
				int counter = 0;
				Boolean auftrags_ausgefuert = false;
				for (int i=0; i < numRows; i++) {
					if (datei_array[i][3].toString().equals(heimathafen)){
						counter = counter+1;				
					}
						
				}	
				
				Object[][]result_array = new Object[counter][numCols+1];
				counter = 0;
				for (int i=0; i < numRows; i++) {
					if (datei_array[i][3].toString().equals(heimathafen)){
						
					
	                for (int j=0; j < numCols; j++) {             	
	                	result_array[counter][j] = datei_array[i][j];
	                	
	                }
	                result_array[counter][5] = auftrags_ausgefuert ;
	                counter = counter+1;
	                
					}

	            }
	        return result_array;
				}

}
