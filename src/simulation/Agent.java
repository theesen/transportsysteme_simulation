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
import com.vividsolutions.jts.geom.Point;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.geo.PointMoveTo;
import simulation_berechnungen.Auftraegs_Fahrkarten_Erzeugen;
import simulation_berechnungen.Finde_Kuerzesten_Weg;
import simulation_daten.Array_Fahrkarten;
import simulation_daten.Array_Kuerzester_Weg;

/**
 * @param args
 */

public class Agent implements Steppable {

	int schiffs_id;
	int direction;
	Point standort_coordinate = null;
	double moveRate = 1;
	int vorgang = 0;
    int geschw_revierfahrt;
    int geschw_marschfahrt;
    int current_row = 0; 
    String schiff_name;
    
	Array_Kuerzester_Weg<Double> array_weg;
	
	public Agent(int id, String geschwindigkeit_revierfahrt, String geschwindigkeit_marschfahrt, String schiffs_name) {
		geschw_revierfahrt= Integer.parseInt (geschwindigkeit_revierfahrt);
		geschw_marschfahrt=Integer.parseInt (geschwindigkeit_marschfahrt);
		schiff_name=schiffs_name;
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

		vorgang=vorgang+1;
		
		System.out.println("Vorgang: "+vorgang);		
		System.out.println("Schiffs Id: "+schiffs_id);
		System.out.println("Schiffs Name: "+schiff_name);
		System.out.println("Geschwindigkeit Revierfahrt: "+geschw_revierfahrt);
		System.out.println("Geschwindigkeit Marschfahrt: "+geschw_marschfahrt);
		
		
		Array_Fahrkarten<Object> test = Auftraegs_Fahrkarten_Erzeugen.getArray_fahrkarten();
		
		
		int rowcount = test.getNumRows()-1;
		System.out.println("Auftrag hat: "+rowcount);	
		
		Coordinate coord = new Coordinate(435.24239312076264,  574.7197300421526);
		
		if (current_row != rowcount){
		coord.x=(Double) test.get(current_row, 0);
		coord.y =(Double) test.get(current_row, 1);
		System.out.println("Auftrags koordinate: "+ current_row + " von " +rowcount);	
		
		current_row= current_row+1;
		
		}
		
		
	//	Coordinate coord = (Coordinate) standort_coordinate.getCoordinate().clone();
//		System.out.println("standort_coordinate: "+coord);
//		Coordinate current_ziel = new Coordinate(426.00924507931506,642.3484625419441);
//		System.out.println("Ziel: "+current_ziel);
//		
// 
//		array_weg=Finde_Kuerzesten_Weg.gbham(coord.x, coord.y, current_ziel.x, current_ziel.y);
//		array_weg.getNumRows();
//		
//		
//		System.out.println(array_weg.getNumRows());
//		
//		coord.x=array_weg.get(1, 0);
//		coord.y=array_weg.get(1, 1);
			
		
			
		
		
	
		
		
		
		//AffineTransformation translate = null;

		// switch (direction)
		// {
		// case N : // move up
		// translate = AffineTransformation.translationInstance(0.0, moveRate);
		// coord.y += moveRate;
		// break;
		// case S : // move down
		// translate = AffineTransformation.translationInstance(0.0, -moveRate);
		// coord.y -= moveRate;
		// break;
		// case E : // move right
		// translate = AffineTransformation.translationInstance(moveRate, 0.0);
		// coord.x += moveRate;
		// break;
		// case W : // move left
		// translate = AffineTransformation.translationInstance(-moveRate, 0.0);
		// coord.x -= moveRate;
		// break;
		// case NW : // move upper left
		// translate =
		// AffineTransformation.translationInstance(-moveRate,moveRate);
		// coord.x -= moveRate;
		// coord.y += moveRate;
		// System.out.println(coord);
		// break;
		// case NE : // move upper right
		// translate = AffineTransformation.translationInstance( moveRate,
		// moveRate );
		// coord.x += moveRate;
		// coord.y += moveRate;
		// break;
		// case SW : // move lower left
		// translate = AffineTransformation.translationInstance(-moveRate,
		// -moveRate);
		// coord.x -= moveRate;
		// coord.y -= moveRate;
		// break;
		// case SE : // move lower right
		// translate = AffineTransformation.translationInstance( moveRate,
		// -moveRate);
		// coord.x += moveRate;
		// coord.y -= moveRate;
		// break;
		// }

		// System.out.println(coord);
		// direction = 0;

		// standort_coordinate.apply(translate);

		// coord.x= coord.x+1;

		// System.out.println(coord);
		
		pointMoveTo.setCoordinate(coord);
		standort_coordinate.apply(pointMoveTo);

	}

	

	// Coordinate c = new Coordinate(220.00, 249.806314195345024);
	// if (see.isCovered(coord)) {
	// //cState.county.updateTree(standort_coordinate, translate);
	//
	// }
	// else // try randomly moving in different direction if trying to stray
	//
}
