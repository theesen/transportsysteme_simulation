package simulation;

import guis.Init_Gui;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import jobs.Auftragsdaten_Auslesen;
import jobs.Schiffsdaten_Auslesen;
import sim.engine.SimState;
import sim.field.geo.GeomVectorField;
import sim.util.geo.MasonGeometry;
import simulation_berechnungen.Reporting_Erzeugen;
import simulation_berechnungen.Sammlung_Berechnungen;
import simulation_koordinaten.Orte_Koordinaten;
import simulation_koordinaten.Windpark_A_Koordinaten;
import simulation_koordinaten.Windpark_B_Koordinaten;
import simulation_koordinaten.Windpark_C_Koordinaten;
import simulation_mason_klassen.ShapeFileImporter2;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Point;

import daten.Tabelle_Auftrag_Schiffsdaten;

/**
 * @author Patrick
 *
 */
public class Simulation extends SimState {
	
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger( Simulation.class );
	public static Boolean gameover = false;
	/**
	 * @return the gameover
	 */
	public static Boolean getGameover() {
		return gameover;
	}
	/**
	 * @param gameover the gameover to set
	 */
	public static void setGameover(Boolean Gameover) {
		gameover = Gameover;
	}

	public int schiffs_rowcount;
	
	public int columncount;
	
	public static Object[][]schiffe_daten_agents= new Object[100][5]; 
	
	//private Point location;
	
	private static final long serialVersionUID = -1113018274619047013L;
	
	// vergroesserungs_faktor schon mit eingerichtet sonst Width = 12 und Height = 10
	public static final int WIDTH = 1200; 
	public static final int HEIGHT = 1000;
	// Um diesen Wert wurde die Karte vergroeßert
	public static final int vergroesserungs_faktor = 100; 
	
	 // getters and setters for inspectors
    public int getNumAgents() { return NUM_AGENTS; }
    public void setNumAgents(int a) { if (a > 0) NUM_AGENTS = a; }
	
	public static int NUM_AGENTS = 4;
	
	public GeomVectorField see = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField agents = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField orte = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField c_punkte = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField a_punkte = new GeomVectorField(WIDTH, HEIGHT);
	
	public GeomVectorField b_punkte = new GeomVectorField(WIDTH, HEIGHT);
	
	
		
	
	
	public Simulation(long seed){
		super (seed);
		
		try {
			//GeoToolsImporter importer = new GeoToolsImporter();
			ShapeFileImporter2 importer = new ShapeFileImporter2();
			
			
			
			
			String aktuelles_verzeichnis = System.getProperty("user.dir"); 
			
//			File f1 = new File("../ne_50m_admin_0_countries.shp"); 
//			System.out.println(f1.exists()); 
			
			
			System.out.println(aktuelles_verzeichnis); 
			 // read in the see Shape file 
			importer.ingest(aktuelles_verzeichnis+"\\Maps\\ne_50m_admin_0_countries.shp", Simulation.class, see,null);
			
			
				
			 // read in the orte Shape file 
			System.out.println("reading orte layer");
            importer.ingest(aktuelles_verzeichnis+"\\Maps\\Orte.shp", Simulation.class, orte, null);
            
            //read in Windpark A Shape file
            System.out.println("reading a punkte layer");
            importer.ingest(aktuelles_verzeichnis+"\\Maps\\A_Punkte.shp", Simulation.class, a_punkte, null);
            
            //read in Windpark B Shape file
            System.out.println("reading b punkte layer");
            importer.ingest(aktuelles_verzeichnis+"\\Maps\\B_Punkte.shp", Simulation.class, b_punkte, null);
				
            // read in the Windpark C Shape file 
            System.out.println("reading c punkte layer");
            importer.ingest(aktuelles_verzeichnis+"\\Maps\\C_Punkte.shp", Simulation.class, c_punkte, null);
            
            // MBR zusammen basteln
            Envelope MBR = see.getMBR();
            MBR.expandToInclude(orte.getMBR());
            MBR.expandToInclude(a_punkte.getMBR());
            MBR.expandToInclude(b_punkte.getMBR());
            MBR.expandToInclude(c_punkte.getMBR());
            see.setMBR(MBR);
            orte.setMBR(MBR);
            a_punkte.setMBR(MBR);
            b_punkte.setMBR(MBR);
            c_punkte.setMBR(MBR);
            
            
            System.out.println("Done reading data");
            
            /* Höhen auslesen kriegen */
            System.out.println("*---------------------------------------------------------*");
			System.out.println("Orginal Höhe der Karte: "+  see.getHeight());
			System.out.println("Neue Höhe der Karte: "+HEIGHT);
			System.out.println("Orgnial Breite der Karte: "+see.getWidth());
			System.out.println("Neue Breite der Karte: " +WIDTH);
			System.out.println("*---------------------------------------------------------*");
            
            // Windpark A-C Array befühlen
			new Windpark_A_Koordinaten(a_punkte.getGeometries());
			new Windpark_B_Koordinaten(b_punkte.getGeometries());
			new Windpark_C_Koordinaten(c_punkte.getGeometries());
			Orte_Koordinaten.Fuelle_Orte_Koordinaten(orte.getGeometries());
		}
		catch(FileNotFoundException ex){
			System.out.println("Error opening shapefile!"+ex);
			System.exit(-1);
		}
	}
	
	private void addAgents(){
		
		double abweichung = 0.00000000000001;

			
			// Schiffe wie in Schiffe.txt angegeben platzieren und Agenten erzeugen 
			for (int i = 0;i<schiffs_rowcount;i++)
			{
				
				
			Agent a = null;
			String schiff_name = 	schiffe_daten_agents[i][0].toString();	
			String heimathafen = schiffe_daten_agents[i][1].toString();
			String geschw_revier = 	schiffe_daten_agents[i][2].toString();
			String geschw_marsch = 	schiffe_daten_agents[i][3].toString();
	
			System.out.println("Schiff hinzugefügt mit Nummer "+i + " Schiffsname: "+ schiff_name+ " Heimathafen: " + heimathafen );
		
			a = new Agent(i,schiff_name,heimathafen,geschw_revier,geschw_marsch);
			//a = new Agent(random.nextInt(8));
			// Location des Agents setzen 
			
			
			
			 
            abweichung = abweichung + abweichung;
            Point test = schiffe_daten_agents_getPointAt(i) ;
            double x = test.getX();
            double y = test.getY();
            
            x = x + abweichung;
            
            test = Sammlung_Berechnungen.Erzeuge_Punkt_aus_Koordinaten(x, y);

            logger.debug( test);
			a.setLocation(test);
			
			 MasonGeometry mg = new MasonGeometry(a.getGeometry()); 
             mg.isMovable = true; 
             agents.addGeometry(mg);
        //     schedule.scheduleRepeating(a);
           
            Reporting_Erzeugen.add_schiffe(i);
			
//			agents.addGeometry(new MasonGeometry(a.getGeometry()));
			schedule.scheduleRepeating(1.0,0,a,1.0);
			
			}
			
			
			
			
			
			
			
			
			
		
			
			
			System.out.println("*---------------------------------------------------------*");
			System.out.println("Neuen Agent erzeugen für Schiff erzeugt");
			System.out.println("*---------------------------------------------------------*");
			
	}
	
	public void start(){
		
		
		super.start();
		agents.clear();
		
		System.out.println("*---------------------------------------------------------*");
		System.out.println("Agenten auf Startpunkt setzten");
		System.out.println("*---------------------------------------------------------*");
		
		Schiffsdaten_Auslesen.main();
		
		Schiffe_Init();
				
		Auftragsdaten_Auslesen.main();
		
		Reporting_Erzeugen.report_anlegen();
		
		Init_Gui.main();
		
				
		
		System.out.println("*-------- Wir haben "+ schiffs_rowcount+" Schiffe------------------------*");
		
		addAgents();
		
		agents.setMBR(see.getMBR());
		
	}
	
	public static void main (String[] args){
		doLoop(Simulation.class, args);
		System.exit(0);
	}
	
	
	
	public void Schiffe_Init() {
		
		//TODO Überarbeiten
		
		System.out.println("*---------------------------------------------------------*");
		System.out.println("Start Schiffe_Init mit Koordinaten");
		
		Tabelle_Auftrag_Schiffsdaten table_object_Auftrag_Schiffsdaten = new Tabelle_Auftrag_Schiffsdaten();
		
		
		schiffs_rowcount = table_object_Auftrag_Schiffsdaten.getRowCount();
		columncount = table_object_Auftrag_Schiffsdaten.getColumnCount();
		NUM_AGENTS =schiffs_rowcount;		
		
		for (int row = 0; row < schiffs_rowcount; row++) {
			for (int col = 0; col < columncount; col++) {
				schiffe_daten_agents[row][col] = table_object_Auftrag_Schiffsdaten.getValueAt(row, col);		
			}
			String hafen = schiffe_daten_agents[row][1].toString();
			if (hafen.equals("Emd")){
				schiffe_daten_agents[row][4]=Orte_Koordinaten.getPointAt(0);
			}else if (hafen.equals("Nor")){
				schiffe_daten_agents[row][4]=Orte_Koordinaten.getPointAt(2);				
			}			
		}
		
		
//		for (Object[] arr : schiffe_daten_agents) {
//            System.out.println(Arrays.toString(arr));
//		}
		
		System.out.println("*---------------------------------------------------------*");
	}
	

	 public static Point schiffe_daten_agents_getPointAt(int row){
		 Point angeforderter_punkt = null;
		 angeforderter_punkt = (Point) schiffe_daten_agents[row][4];	 
		 return angeforderter_punkt;
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
