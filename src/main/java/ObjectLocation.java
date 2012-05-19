package main.java;

import com.vividsolutions.jts.geom.Coordinate;


public class ObjectLocation {
	private static Coordinate positionA = new Coordinate(1,1);
	
	private static Coordinate positionB = new Coordinate(100,100);



public static Coordinate getPositionA (){
	return positionA;
}
public static Coordinate getPositionB(){
	return positionB;
}

}