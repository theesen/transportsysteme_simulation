package Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.util.AffineTransformation;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.geo.GeomVectorField;
import sim.util.geo.PointMoveTo;

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
    
    double moveRate = 1;
    
    public Agent(int d){
    	direction = d;
    }
    
    public void setLocation (Point p) {location = p;}
    PointMoveTo pointMoveTo = new PointMoveTo();
    public Geometry getGeometry(){ return location;}
    
    public void step (SimState state){
    	
     	
	  	
    	
    	Test testa = (Test) state;
    	GeomVectorField see = testa.see;
    	Coordinate coord = (Coordinate) location.getCoordinate().clone();
    	System.out.println(coord);
    	AffineTransformation translate = null;
    	
//    	switch (direction)
//        {
//        case N : // move up
//            translate = AffineTransformation.translationInstance(0.0, moveRate);
//            coord.y += moveRate;
//            break;
//        case S : // move down
//            translate = AffineTransformation.translationInstance(0.0, -moveRate);
//            coord.y -= moveRate;
//            break;
//        case E : // move right
//            translate = AffineTransformation.translationInstance(moveRate, 0.0);
//            coord.x += moveRate;
//            break;
//        case W : // move left
//            translate = AffineTransformation.translationInstance(-moveRate, 0.0);
//            coord.x -= moveRate;
//            break;
//        case NW : // move upper left
//            translate = AffineTransformation.translationInstance(-moveRate,moveRate);
//            coord.x -= moveRate;
//            coord.y += moveRate; 
//            System.out.println(coord);
//            break;
//        case NE : // move upper right
//            translate = AffineTransformation.translationInstance( moveRate, moveRate );
//            coord.x += moveRate;
//            coord.y += moveRate;
//            break;
//        case SW : // move lower left
//            translate = AffineTransformation.translationInstance(-moveRate, -moveRate);
//            coord.x -= moveRate;
//            coord.y -= moveRate;
//            break;
//        case SE : // move lower right
//            translate = AffineTransformation.translationInstance( moveRate, -moveRate);
//            coord.x += moveRate;
//            coord.y -= moveRate;
//            break;
//        }
//    	
    //	System.out.println(see.isInsideConvexHull(coord));
    	
//    	System.out.println("höhe"+see.getHeight());
//    	System.out.println("width"+see.getWidth());
//    	System.out.println("x" + coord.x);
//    	System.out.println("y"+coord.y);
//    	
    	
//    	System.out.println(coord);
    	 // 	direction = 0; 
    	 
    	// location.apply(translate);
    	  	
    	  	coord.x= coord.x+1;
    	         
    	  		
    	    System.out.println(coord);
			pointMoveTo.setCoordinate(coord);
            location.apply(pointMoveTo);  	
    	  	}
            
            
            
            
            
            
//            Coordinate c = new Coordinate(220.00, 249.806314195345024);
//    	if (see.isCovered(coord))  { 
//        	//cState.county.updateTree(location, translate); 
//        	
//        }
//        else // try randomly moving in different direction if trying to stray
//      
    }
    	
 
    
	


