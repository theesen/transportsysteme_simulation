/**
 * 
 */
package jobs;

import java.util.Arrays;

import gui_komponenten.Gui_Tabellen;

/**
 * @author patrick
 *
 */
public class Auftragsdaten_Auslesen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		String aktuelles_verzeichnis = Datei_Auswaehler.main(new String[] {"auftraege.txt", "Auftragsdaten"});	
	
		Object[][] datei_array = Datei_Importierer.main(new String[]{aktuelles_verzeichnis});		
		
		
		
		 // Array aufsplitten, da unentlich viele Spalten bzw. unterschiedliche viele
		 Object [][] datei_array_auftrag_allgemein = new Object[datei_array.length][5] ; 
		
		 
			
		 System.out.println("Anzahl der Tabellenreihen: " + datei_array_auftrag_allgemein.length +"\n");
		 System.out.println("Anzahl der Tabellenspalten: " + datei_array_auftrag_allgemein[1].length+"\n");
	
		 // Reihen nummerieren und die erste 4 spalten in ein eigenes array schreiben
		 int id = 0;
		 int row_auftraege = 0;
		 int row_auftraege_analyse = 0;
		 
		 		 for (int row=0; row < datei_array_auftrag_allgemein.length; row++)
		 { for (int col=4; col < datei_array[row].length-1; col++){row_auftraege_analyse= row_auftraege_analyse+1; col=col+1;} }
		 // Auftrags array 
	 	 Object [][] datei_array_auftrag_auftraege = new Object[row_auftraege_analyse][3] ;
		 	 
		 
		 
		 
		 
		 for (int row=0; row < datei_array_auftrag_allgemein.length; row++)
		 {  
			 	datei_array_auftrag_allgemein[row][0]="id"+id;
		      	  
			 	for (int col=0; col <= 3; col++)
			 	{
			 	datei_array_auftrag_allgemein[row][col+1]= datei_array[row][col];	
			 	}

			 	 	System.out.println("Reihenlaenge "+datei_array[row].length);

			 	 	
			 	for (int col=4; col < datei_array[row].length-1; col++){
			 					 		
			 		
			 		datei_array_auftrag_auftraege[row_auftraege][0]="id"+id; 	
			 		datei_array_auftrag_auftraege[row_auftraege][1]=datei_array[row][col]; 	
			 		datei_array_auftrag_auftraege[row_auftraege][2]=datei_array[row][col+1]; 	
			 		
			 		row_auftraege= row_auftraege+1;
			 		col=col+1;
			 	}
			 	
 	
			 	
				id=id+1;
 		 }
		
		 
		
		 
		
		 
		 
		 
		 
		 
		 // Eingelesendes Array
		 for (Object[] arr : datei_array) {
	            System.out.println(Arrays.toString(arr));
	        }		
		
		 // Nur Allgemeine Auftragsdaten
		 for (Object[] arr : datei_array_auftrag_allgemein) {
	            System.out.println(Arrays.toString(arr));
	        }		
		
		
		// Aufträge für die jeweiligen Auftragsdaten
		 for (Object[] arr :  datei_array_auftrag_auftraege) {
	            System.out.println(Arrays.toString(arr));
	        }	
		 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String[]spalten_namen_allgemein = {"ID","Nr","Uhrzeit","Starthafen","Endhafen"};		
		String[]spalten_namen_auftraege = {"ID","Ziel","Aufenthalt in Min"};	
		Gui_Tabellen.main(datei_array_auftrag_allgemein, spalten_namen_allgemein, datei_array_auftrag_auftraege, spalten_namen_auftraege);
		
		
		
	}

}
