/**
 * 
 */
package simulation_daten;

import java.util.Arrays;

import javax.measure.quantity.Length;

import daten.Auftrag_Allgemein;
import daten.Tabelle_Auftrag_Allgemein;
import daten.Tabelle_Auftrag_Schiffsdaten;

/**
 * @author crossness
 *
 */
public class Array_Auftraege_Status {

	/**
	 * @param args
	 */
	public  static Tabelle_Auftrag_Allgemein array_auftrag_allgemein;
	public  static Object[][] auftraege_emden;
	public  static Object[][] auftraege_norddeich;
	
	public Array_Auftraege_Status() {
		
		
		
		array_auftrag_allgemein = new Tabelle_Auftrag_Allgemein();
		auftraege_emden = array_auftrag_allgemein.getAuftraege_fuer_Heimataufen("Emd");
		auftraege_norddeich = array_auftrag_allgemein.getAuftraege_fuer_Heimataufen("Nor");
		
		
		
		//System.out.println(table_object_Auftrag_Schiffsdaten.getRowCount());
		
		for (Object[] arr : auftraege_emden) {
            System.out.println(Arrays.toString(arr));
		}
		
		for (Object[] arr : auftraege_norddeich) {
            System.out.println(Arrays.toString(arr));
		}
		
		
	}

	/**
	 * @return the auftraege_emden
	 */
	public static Object[][] getAuftraege_emden() {
		return auftraege_emden;
	}

	/**
	 * @param auftraege_emden the auftraege_emden to set
	 */
	public static void setAuftraege_emden(Object[][] auftraege_emden) {
		Array_Auftraege_Status.auftraege_emden = auftraege_emden;
	}

	/**
	 * @return the auftraege_norddeich
	 */
	public static Object[][] getAuftraege_norddeich() {
		return auftraege_norddeich;
	}

	/**
	 * @param auftraege_norddeich the auftraege_norddeich to set
	 */
	public static void setAuftraege_norddeich(Object[][] auftraege_norddeich) {
		Array_Auftraege_Status.auftraege_norddeich = auftraege_norddeich;
	}
	
	public Boolean getAuftragsstatus_Emden(int auftragsnummer){
		return (Boolean) auftraege_emden[auftragsnummer][5];
	}
	
	
	
	
	
	public static Object get_freie_Auftragsnummer_Emden(){
		
		
		int numRows = auftraege_emden.length;
        int numCols = auftraege_emden[0].length;
        Object auftragsnummer = false;  
        
        for (int i=0; i < numRows; i++) {
            
            if(auftraege_emden[i][5].equals(false)){
            	return (Object) auftraege_emden[i][0]; 	
            }
            
        }
       

		return (Object) auftragsnummer;
	}
	
	
public static Object get_freie_Auftragsnummer_Nordeich(){
		
		
		int numRows = auftraege_norddeich.length;
        int numCols = auftraege_norddeich[0].length;
        Object auftragsnummer = false;  
        
        for (int i=0; i < numRows; i++) {
            
            if(auftraege_norddeich[i][5].equals(false)){
            	return (Object) auftraege_norddeich[i][0]; 	
            }
            
        }
       

		return (Object) auftragsnummer;
	}
	
	
	
	
	
	
	public Boolean getAuftragsstatus_Nordeich(int auftragsnummer){
		return (Boolean) auftraege_norddeich[auftragsnummer][5];
	}
	
	public static void setAuftraege_norddeich_erledigt(int auftragsnummer) {
		
		int numRows = auftraege_norddeich.length;
        int numCols = auftraege_norddeich[0].length;
                
        for (int i=0; i < numRows; i++) {
            
            if(auftraege_norddeich[i][0].equals(auftragsnummer)){
            	auftraege_norddeich[i][5]=true; 	
            }
            
        }
		
		
	}
	
	public static void setAuftraege_emden_erledigt(int auftragsnummer) {
		
		int numRows = auftraege_emden.length;
        
                
        for (int i=0; i < numRows; i++) {
            
            if(auftraege_emden[i][0].equals(auftragsnummer)){
            	auftraege_emden[i][5]=true; 	
            }
            
        }
		
		
		
		
		
		
		auftraege_emden[auftragsnummer][5]=true;;
	}
	
	public String getStartzeit_Nordeich(int auftragsnummer){
		return (String) auftraege_norddeich[auftragsnummer][3];
	}
	
	public String getStartzeit_Emden(int auftragsnummer){
		return (String) auftraege_emden[auftragsnummer][3];
	}
	
	public String getEndhafen_Nordeich(int auftragsnummer){
		return (String) auftraege_norddeich[auftragsnummer][4];
	}
	
	public String getEndhafen_Emden(int auftragsnummer){
		return (String) auftraege_norddeich[auftragsnummer][4];
	}
}
