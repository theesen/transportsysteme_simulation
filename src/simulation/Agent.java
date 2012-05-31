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

	int schiffs_id;
	int direction;
	Point standort_coordinate = null;
	int moveRate = 5;
	int vorgang = 0;
	Array_Kuerzester_Weg<Double> array_weg;
	
	public Agent(int id) {
		schiffs_id = id;
	}

	public void setLocation(Point p) {
		standort_coordinate = p;
	}

	PointMoveTo pointMoveTo = new PointMoveTo();

	public Geometry getGeometry() {
		return standort_coordinate;
	}

	public void step(SimState state) {

		// Simulation testa = (Simulation) state;
		// GeomVectorField see = testa.see;
		for (int i= 0; i < moveRate; i++){	
		Coordinate coord = (Coordinate) location.getCoordinate().clone();
		System.out.println(schiffs_id);
		System.out.println("location: "+coord);

		Coordinate current_ziel = new Coordinate(426.00924507931506,642.3484625419441);
		
		
		Coordinate coord = (Coordinate) standort_coordinate.getCoordinate().clone();
		System.out.println("standort_coordinate: "+coord);
		Coordinate current_ziel = new Coordinate(426.00924507931506,642.3484625419441);
		System.out.println("Ziel: "+current_ziel);
		
		
 
		array_weg=Finde_Kuerzesten_Weg.gbham(coord.x, coord.y, current_ziel.x, current_ziel.y);
		array_weg.getNumRows();
		System.out.println(array_weg.getNumRows());
		coord.x=array_weg.get(1, 0);
		coord.y=array_weg.get(1, 1);
			
		
//		System.out.println(array_weg.getNumRows());
		//current_ziel= 
			
		System.out.println("_______________________________________");
		
	

		
		pointMoveTo.setCoordinate(coord);
		standort_coordinate.apply(pointMoveTo);
		}
		System.out.println("---------------------------------------------------------");
		System.out.println(array_weg.getNumRows());	
		System.out.println("---------------------------------------------------------");

	}

	

	// Coordinate c = new Coordinate(220.00, 249.806314195345024);
	// if (see.isCovered(coord)) {
	// //cState.county.updateTree(standort_coordinate, translate);
	//
	// }
	// else // try randomly moving in different direction if trying to stray
	//
}
