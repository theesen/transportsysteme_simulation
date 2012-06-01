/**
 * 
 */
package simulation_berechnungen;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

/**
 * @author crossness
 *
 */
public class Sammlung_Berechnungen {

	
	
	
	public static Point Erzeuge_Punkt_aus_Koordinaten(double x, double y){
		
		GeometryFactory fact = new GeometryFactory();
		Double new_x = x;
		Double new_y = y;
		Point new_point = fact.createPoint(new Coordinate(new_x,new_y));
		
		return (new_point);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
