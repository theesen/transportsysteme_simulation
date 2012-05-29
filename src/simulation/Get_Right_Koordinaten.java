/**
 * 
 */
package simulation;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

/**
 * @author crossness
 *
 */
public class Get_Right_Koordinaten {

	/**
	 * @param args
	 * @return 
	 */
	public static  Point main(Point umwandel_koordinate) {
		// TODO Auto-generated method stub

	double ziel_x = umwandel_koordinate.getX();
 	double ziel_y = umwandel_koordinate.getY();	
		
 	double ziel_neu_x = ((ziel_x-2.524941847350391)-(0.458069661500247/2))*Simulation.vergroesserungs_faktor;
	double ziel_neu_y = ((ziel_y-47.278819891949524)-(0.612150065154651/2))*Simulation.vergroesserungs_faktor;	
 	
	GeometryFactory fact = new GeometryFactory();
	umwandel_koordinate = fact.createPoint(new Coordinate(ziel_neu_x,ziel_neu_y));
 	
	return 	umwandel_koordinate;
	}

}
