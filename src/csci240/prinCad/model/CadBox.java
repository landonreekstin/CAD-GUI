package csci240.prinCad.model;

public class CadBox {
	
	// Can expose the values since the outside world is not able to modify
   	public final double xMin, yMin, xMax, yMax;
   	
   	// One time assignment of X min & max and Y min & max values
   	public CadBox(double xMin, double yMin, double xMax, double yMax) {
         	this.xMin = xMin;
         	this.yMin = yMin;
         	this.xMax = xMax;
         	this.yMax = yMax;
   	}
 
   	// Check to see if the passed-in rectangle is inside this rectangle
   	public boolean contains(CadBox rect) {
         	return (rect.xMin >= this.xMin &&
                      	rect.xMax <= this.xMax &&
                      	rect.yMin >= this.yMin &&
                      	rect.yMax <= this.yMax);
   	}

}
