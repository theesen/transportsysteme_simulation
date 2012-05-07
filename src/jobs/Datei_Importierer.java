package jobs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
*
* @author Patrick
*/



public class Datei_Importierer {
	
    public static Object[][] main(String[] args) {
    
    
    
    	
    	
    	
    try {
    	
    	// Einlesen des Files und spliten
        FileReader myFile = null;
        BufferedReader buff = null;
       
     
        System.out.println("*********************");
        System.out.println("***  Datenzugriff ***");
        System.out.println("********"+args[0]+"********");        
        
        final List<String> lines = new ArrayList<String>();	
               
        try {
            myFile = new FileReader(args[0]);
            buff = new BufferedReader(myFile);
            String line;
            while ((line = buff.readLine()) != null) {
                System.out.println(line); // kontrolle was eingelesen
     
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error2 :" + e);
        } finally {
            try {
                buff.close();
                myFile.close();
            } catch (IOException e) {
                System.err.println("Error2 :" + e);
            }
        }
     
        final Object[][] datei_array = new Object[lines.size()][];	
        int cnt = 0;
        for (final String line : lines) {
            datei_array[cnt++] = line.split(";");
        }
     
        // Ausgabe des Array
        for (Object[] arr : datei_array) {
            System.out.println(Arrays.toString(arr));
        }
    	
        
        return datei_array;
    	
    	
 
    }
 
        catch(Exception ex)
         {
  System.out.println("Irgendwas ist schief gelaufen !!!");
 
  System.out.println(ex.getMessage() );
 
       }
	return null;
 
      
    }
 
}	
	
	
	
	
	
	
	

