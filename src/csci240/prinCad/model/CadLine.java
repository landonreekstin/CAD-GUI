package csci240.prinCad.model;

public class CadLine {

	// Can expose the values since the outside world is not able to modify
	public final double x0, y0, x1, y1;
	
	public final double dx, dy, len, cos, sin;

	// One time assignment of X and Y values
	public CadLine(double x0, double y0, double x1, double y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		
		this.dx = this.x1 - this.x0;
		this.dy = this.y1 - this.y0;
		this.len = Math.sqrt(dx * dx + dy * dy);
		this.cos = this.dx / this.len;
		this.sin = this.dy / this.len;
	}

	public boolean intersects(CadPoint pa, CadPoint pb) {
		return intersects(pa.x, pa.y, pb.x, pb.y);
	}
	
	public boolean intersects(double xa, double ya, double xb, double yb) {
	
		double dxa = xa - x0;
		double dya = ya - y0;
		double dxb = xb - x0;
		double dyb = yb - y0;
		
		// rotate to horizontal
		// then check to validate the yx of the two points are on opposite sides of the line
		double ypa = dya * cos - dxa * sin;
		double ypb = dyb * cos - dxb * sin;
		if ((ypa < 0.0 && ypb < 0.0) || (ypa > 0.0 && ypb > 0.0))
			return false;
		
		// then check to validate the x at y=0 is position and less than length
		double xpa = dxa * cos + dya * sin;
		double xpb = dxb * cos + dyb * sin;
		double xat0y = xpa - ypa * (xpb - xpa) / (ypb - ypa);
		
		return (xat0y >= 0.0 && xat0y < len);
	}
	
	public boolean intersects(CadBox box) {
		
		// is the line close to the box?
		if ((x0 < box.xMin && x1 < box.xMin) ||
			(x0 > box.xMax && x1 > box.xMax) ||
			(y0 < box.yMin && y1 < box.yMin) || 
			(y0 > box.yMax && y1 > box.yMax))
			return false;
		
		// does line cross the box?
		return (intersects(box.xMin, box.yMax, box.xMax, box.yMax) || // top side of box
				intersects(box.xMax, box.yMax, box.xMax, box.yMin) || // right side of box
				intersects(box.xMax, box.yMin, box.xMin, box.yMin) || // bottom side of box
				intersects(box.xMin, box.yMin, box.xMin, box.yMax) ); // left side of box
	}

}
