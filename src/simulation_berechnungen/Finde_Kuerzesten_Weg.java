/**
 * 
 */
package simulation_berechnungen;

import simulation_daten.Array_Kuerzester_Weg;

import com.vividsolutions.jts.geom.Point;

/**
 * @author Patrick / Wiki
 *
 */



public class Finde_Kuerzesten_Weg {

	
	
	
	
		
	public static Array_Kuerzester_Weg<Double> gbham(double xstart,double ystart,double xend,double yend)
	/*--------------------------------------------------------------
	 * Bresenham-Algorithmus: Linien auf Rastergeräten zeichnen
	 *
	 * Eingabeparameter:
	 *    double xstart, ystart        = Koordinaten des Startpunkts
	 *    double xend, yend            = Koordinaten des Endpunkts
	 *
	 * Ausgabe:
	 *    void SetPixel(double x, double y) setze ein Pixel in der Grafik
	 *         (wird in dieser oder aehnlicher Form vorausgesetzt)
	 *---------------------------------------------------------------
	 */
	
	{
		
		Array_Kuerzester_Weg<Double> array_weg = new Array_Kuerzester_Weg<Double>();
		
		
	    double x, y, t, dx, dy, incx, incy, pdx, pdy, ddx, ddy, es, el, err;
	 
	    
	    // Neues Array erzeugen
	   
	    
	    
	    
	/* Entfernung in beiden Dimensionen berechnen */
	   dx = xend - xstart;
	   dy = yend - ystart;
	 
	/* Vorzeichen des Inkrements bestimmen */
	   incx = sgn(dx);
	   incy = sgn(dy);
	   if(dx<0) dx = -dx;
	   if(dy<0) dy = -dy;
	 
	/* feststellen, welche Entfernung größer ist */
	   if (dx>dy)
	   {
	      /* x ist schnelle Richtung */
	      pdx=incx; pdy=0;    /* pd. ist Parallelschritt */
	      ddx=incx; ddy=incy; /* dd. ist Diagonalschritt */
	      es =dy;   el =dx;   /* Fehlerschritte schnell, langsam */
	   } else
	   {
	      /* y ist schnelle Richtung */
	      pdx=0;    pdy=incy; /* pd. ist Parallelschritt */
	      ddx=incx; ddy=incy; /* dd. ist Diagonalschritt */
	      es =dx;   el =dy;   /* Fehlerschritte schnell, langsam */
	   }
	 
	/* Initialisierungen vor Schleifenbeginn */
	   x = xstart;
	   y = ystart;
	   err = el/2;
	   
	   int row = array_weg.getNumRows();
	   array_weg.Add(x, row);
	   array_weg.Add(y, row);
	    
	    
	   
	   
	   
	   SetPixel(x,y);
	 
	/* Pixel berechnen */
	   for(t=0; t<el; ++t) /* t zaehlt die Pixel, el ist auch Anzahl */
	   {
	      /* Aktualisierung Fehlerterm */
	      err -= es; 
	      if(err<0)
	      {
	          /* Fehlerterm wieder positiv (>=0) machen */
	          err += el;
	          /* Schritt in langsame Richtung, Diagonalschritt */
	          x += ddx;
	          y += ddy;
	      } else
	      {
	          /* Schritt in schnelle Richtung, Parallelschritt */
	          x += pdx;
	          y += pdy;
	      }
	      
	      row = array_weg.getNumRows();
		  array_weg.Add(x, row);
		  array_weg.Add(y, row);
	      
	      SetPixel(x,y);
	      
	      
	      
	   }
	return array_weg;
	} /* gbham() */
		 

	private static void SetPixel(double x, double y) {
		
		
		
		//System.out.println("x: "+x+ " y: "+y);
		
		
	}


	/* signum function */
	static double sgn(double x){
	  return (x > 0) ? 1 : (x < 0) ? -1 : 0;
	}	
		
		
		
		
		public static Array_Kuerzester_Weg<Double> get_kurzen_weg_zwei_punkte (Point start_punkt, Point end_punkt){
			
			Array_Kuerzester_Weg<Double> array_weg = new Array_Kuerzester_Weg<Double>();
			
			
		
			array_weg= gbham(start_punkt.getX(),start_punkt.getY(), end_punkt.getX(), end_punkt.getY());
			
			return array_weg;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

}
