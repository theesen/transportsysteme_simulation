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
