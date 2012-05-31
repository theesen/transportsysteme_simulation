/**
 * 
 */
package simulation;

/**
 * @author Patrick
 *
 */

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.util.AffineTransformation;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.geo.GeomVectorField;
import sim.util.geo.PointMoveTo;

/**
 * @param args
 */

public class Agent implements Steppable {

	final int N = 0;
	final int NW = 1;
	final int W = 2;
	final int SW = 3;
	final int S = 4;
	final int SE = 5;
	final int E = 6;
	final int NE = 7;
	int schiffs_id;
	int direction;
	Point location = null;
	int moveRate = 5;
	Array_Kuerzester_Weg<Double> array_weg;
	
	public Agent(int id) {
		schiffs_id = id;
	}

	public void setLocation(Point p) {
		location = p;
	}

	PointMoveTo pointMoveTo = new PointMoveTo();

	public Geometry getGeometry() {
		return location;
	}

	public void step(SimState state) {

		// Simulation testa = (Simulation) state;
		// GeomVectorField see = testa.see;
		for (int i= 0; i < moveRate; i++){	
		Coordinate coord = (Coordinate) location.getCoordinate().clone();
		System.out.println(schiffs_id);
		System.out.println("location: "+coord);

		Coordinate current_ziel = new Coordinate(426.00924507931506,642.3484625419441);
		
		System.out.println("Ziel: "+current_ziel);
		
		
 
		array_weg=Finde_Kuerzesten_Weg.gbham(coord.x, coord.y, current_ziel.x, current_ziel.y);
		array_weg.getNumRows();
		System.out.println(array_weg.getNumRows());
		
			coord.x=array_weg.get(1, 0);
			coord.y=array_weg.get(1, 1);
		
//		System.out.println(array_weg.getNumRows());
		//current_ziel= 
			
		System.out.println(array_weg.getNumRows());	
		System.out.println("_______________________________________");
	

		
		pointMoveTo.setCoordinate(coord);
		location.apply(pointMoveTo);
		}
		System.out.println("---------------------------------------------------------");
		System.out.println(array_weg.getNumRows());	
		System.out.println("---------------------------------------------------------");

	}

	// Coordinate c = new Coordinate(220.00, 249.806314195345024);
	// if (see.isCovered(coord)) {
	// //cState.county.updateTree(location, translate);
	//
	// }
	// else // try randomly moving in different direction if trying to stray
	//
}
