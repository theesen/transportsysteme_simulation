package jobs;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
/**
*
* @author Patrick
*/
public class Datei_Auswaehler {

static String aktuelles_verzeichnis = null;
static String ausgewaehlte_datei = null;

	
	public static String main( final String[] args )
	{
		  
		aktuelles_verzeichnis = System.getProperty("user.dir");  
		
		  
	    JFileChooser file_chooser = new JFileChooser(aktuelles_verzeichnis );
	    file_chooser.setFileFilter( new FileFilter()
	    
	    
	    {
	      @Override public boolean accept( File datei )
	      {
	        return datei.isDirectory() ||
	          datei.getName().toLowerCase().equals(args[0]);
	      }
	      @Override public String getDescription()
	      {
	        return args[1];
	      }
	    } );
	    
	    
	    int state = file_chooser.showOpenDialog( null );
	    if ( state == JFileChooser.APPROVE_OPTION )
	    	
	    {
	      File file = file_chooser.getSelectedFile();
	      System.out.println( file.getAbsolutePath());
	      
	      ausgewaehlte_datei= file.getAbsolutePath();
	      
	      return ausgewaehlte_datei;
	      
	    }
	    
	    else
	      System.out.println( "Auswahl abgebrochen" );
	    System.exit( 0 );
		return aktuelles_verzeichnis;
	}
	
	
	
	
	

	
	






	
	
	
	
	
	
	
	
	
	
	
	
	
}
