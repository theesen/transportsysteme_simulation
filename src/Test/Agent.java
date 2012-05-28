package test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.util.AffineTransformation;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.geo.GeomVectorField;

public class Agent implements Steppable{
	
    final int N  = 0; 
    final int NW = 1; 
    final int W  = 2;
    final int SW = 3;
    final int S  = 4;
    final int SE = 5; 
    final int E  = 6; 
    final int NE = 7;
	
    int direction;
    Point location = null;
    
    double moveRate = 10;
    
    public Agent(int d){
    	direction = d;
    }
    
    public void setLocation (Point p) {location = p;}
    
    public Geometry getGeometry(){ return location;}
    
    public void step (SimState state){
    	
    	Test testa = (Test) state;
    	GeomVectorField see = testa.see;
    	Coordinate coord = (Coordinate) location.getCoordinate().clone();
    	AffineTransformation translate = null;
    	
    	switch (direction)
        {
        case N : // move up
            translate = AffineTransformation.translationInstance(0.0, moveRate);
            coord.y += moveRate;
            break;
        case S : // move down
            translate = AffineTransformation.translationInstance(0.0, -moveRate);
            coord.y -= moveRate;
            break;
        case E : // move right
            translate = AffineTransformation.translationInstance(moveRate, 0.0);
            coord.x += moveRate;
            break;
        case W : // move left
            translate = AffineTransformation.translationInstance(-moveRate, 0.0);
            coord.x -= moveRate;
            break;
        case NW : // move upper left
            translate = AffineTransformation.translationInstance(-moveRate,moveRate);
            coord.x -= moveRate;
            coord.y += moveRate; 
            break;
        case NE : // move upper right
            translate = AffineTransformation.translationInstance( moveRate, moveRate );
            coord.x += moveRate;
            coord.y += moveRate;
            break;
        case SW : // move lower left
            translate = AffineTransformation.translationInstance(-moveRate, -moveRate);
            coord.x -= moveRate;
            coord.y -= moveRate;
            break;
        case SE : // move lower right
            translate = AffineTransformation.translationInstance( moveRate, -moveRate);
            coord.x += moveRate;
            coord.y -= moveRate;
            break;
        }
    	
    	location.apply(translate);
    	
    }
    
	

}
