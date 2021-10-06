package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class CircleItem extends CadItem {
	
	public final double _xCenter;
	public final double _yCenter;
	public final double _radius;
	public final double _diameter;
	
	public CircleItem(double xCenter, double yCenter, double radius) {
		_xCenter = xCenter;
		_yCenter = yCenter;
		_radius = radius;
		_diameter = radius + radius;
	}
	
	// load circle item from string data
	public static CircleItem load(String data) {
		
		CircleItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xc = Double.parseDouble(tokens[0]);
			double yc = Double.parseDouble(tokens[1]);
			double r  = Double.parseDouble(tokens[2]);
			item = new CircleItem(xc, yc, r);
		}
		catch (Exception ex) {
			Log.error("Invalid CircleTool data string: " + data, ex);
		}
		return item;
	}

	@Override
   	public CadBox getRectangle() {
     	return new CadBox(_xCenter - _radius, _yCenter - _radius, _xCenter + _radius, _xCenter + _radius);
   	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(_xCenter - _radius, _yCenter - _radius, _diameter, _diameter);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f", _xCenter, _yCenter, _radius);
	}
	
	@Override
	public boolean intersects(CadLine line) {
		double dx0 = line.x0 - _xCenter;
		double dy0 = line.y0 - _yCenter;
		double dx1 = line.x1 - _xCenter;
		double dy1 = line.y1 - _yCenter;
		double lenSquare0 = dx0 * dx0 + dy0 * dy0;
		double lenSquare1 = dx1 * dx1 + dy1 * dy1;
		double radiusSquare = _radius * _radius;
		return ((lenSquare0 <= radiusSquare && lenSquare1 >= radiusSquare) ||
				(lenSquare0 >= radiusSquare && lenSquare1 <= radiusSquare));
	}
	
	// Does the line intersect this item?
	@Override
	public boolean intersects(CadPoint point) {
		double dx = point.x - _xCenter;
		double dy = point.y - _yCenter;
		double lenSquare = dx * dx + dy * dy;
		double radiusSquare = _radius * _radius;
		double sizeSquare = BoxMarkerItem.markerSize * BoxMarkerItem.markerSize;
		return Math.abs(radiusSquare - lenSquare) <= sizeSquare;
	}
	
	@Override
	public CircleItem deepCopy() {
		CircleItem newDeepCopy = new CircleItem(this._xCenter, this._yCenter, this._radius);
		return newDeepCopy;
	}
	
}

