package csci240.prinCad.model;

public class CadPoint {

	// Can expose the values since the outside world is not able to modify
	public final double x, y;
	
	// One time assignment of X and Y values
	public CadPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
}
