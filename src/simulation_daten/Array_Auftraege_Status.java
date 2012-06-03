/**
 * 
 */
package simulation_daten;



import java.util.Arrays;

import daten.Tabelle_Auftrag_Allgemein;


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
		
//		for (Object[] arr : auftraege_emden) {
//            System.out.println(Arrays.toString(arr));
//		}
//		
//		for (Object[] arr : auftraege_norddeich) {
//            System.out.println(Arrays.toString(arr));
//		}
		
		
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
	
	
	
	
	
	public static Object get_freie_Auftragsnummer_Emden(int aktuelle_uhrzeit){
		
		int startzeit_in_minuten;
		int numRows = auftraege_emden.length;
		int newRows = 0;
		
		
		int zaehler = 0;
		
		for (int i = 0; i < numRows; i++) {
		if (auftraege_emden[i][5].equals(false)) {
			newRows = newRows+1;
		}
		}
		
		
		
		String[][] warteliste = new String[newRows][2];
		
		
		for (int i = 0; i < numRows; i++) {

		if (auftraege_emden[i][5].equals(false)) {	
				
				String startzeit = (String) auftraege_emden[i][2];
				String[] split = startzeit.split("\\:");
				startzeit_in_minuten = 60 * Integer.valueOf(split[0])+ Integer.valueOf(split[1]);
				warteliste[zaehler][0] = String.valueOf(startzeit_in_minuten);
				warteliste[zaehler][1] = String.valueOf(auftraege_emden[i][0]);
				zaehler = zaehler +1;
				}
		}
		
		
		
		if (warteliste.length != 0){
		int ASC = 1; 
		warteliste=simulation_daten.Array_Sortier_Manager.sort(warteliste, new int[]{0,1,2}, new int[]{ASC,ASC,ASC});  
		System.out.println("Warteliste Emden");
		for (Object[] arr :  warteliste) {
			
            System.out.println(Arrays.toString(arr));
		}
		
		
		if(Integer.valueOf(warteliste[0][0])<= aktuelle_uhrzeit){
			
			return Integer.valueOf(warteliste[0][1]);
		}else{
			return (Object) "Warten";
		}
			
		}else
		{
	
		return (Object) false;
		}
	}
	
	
	public static Object get_freie_Auftragsnummer_Norddeich(int aktuelle_uhrzeit){
		
		int startzeit_in_minuten;
		int numRows = auftraege_norddeich.length;
		int newRows = 0;
		Object auftragsnummer = false;
		
		int zaehler = 0;
		
		for (int i = 0; i < numRows; i++) {
		if (auftraege_norddeich[i][5].equals(false)) {
			newRows = newRows+1;
		}
		}
		
	
		String[][] warteliste = new String[newRows][2];
		
		for (int i = 0; i < numRows; i++) {

		if (auftraege_norddeich[i][5].equals(false)) {	
				
				String startzeit = (String) auftraege_norddeich[i][2];
				String[] split = startzeit.split("\\:");
				startzeit_in_minuten = 60 * Integer.valueOf(split[0])+ Integer.valueOf(split[1]);
				warteliste[zaehler][0] = String.valueOf(startzeit_in_minuten);
				warteliste[zaehler][1] = String.valueOf(auftraege_norddeich[i][0]);
				zaehler = zaehler +1;
				}
		}
		
		if (warteliste.length != 0){
			int ASC = 1; 
			warteliste=simulation_daten.Array_Sortier_Manager.sort(warteliste, new int[]{0,1,2}, new int[]{ASC,ASC,ASC});  
			System.out.println("Warteliste Norddeich");
			for (Object[] arr :  warteliste) {
				
	            System.out.println(Arrays.toString(arr));
			}
			
			
			if(Integer.valueOf(warteliste[0][0])<= aktuelle_uhrzeit){
				return Integer.valueOf(warteliste[0][1]);
			}else{
				return (Object) "Warten";
			}
				
			}else
			{
		
			return (Object) false;
			}
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
	
	public static int getNumRows()
	{
		int size = 0;
		size = auftraege_emden.length + auftraege_norddeich.length;
		
		return size;
	}
 
	public static int getNumCols()
	{
		return auftraege_emden[0].length;
	}
	
}
