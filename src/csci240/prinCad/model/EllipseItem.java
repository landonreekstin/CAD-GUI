package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class EllipseItem extends CadItem {
	
	public final double _xCenter;
	public final double _yCenter;
	public final double _xRadius;
	public final double _yRadius;
	
	public EllipseItem(double xCenter, double yCenter, double width, double height) {
		_xCenter = xCenter;
		_yCenter = yCenter;
		_xRadius = width;
		_yRadius = height;
	}
	
	// load circle item from string data
	public static EllipseItem load(String data) {
		
		EllipseItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xc = Double.parseDouble(tokens[0]);
			double yc = Double.parseDouble(tokens[1]);
			double w  = Double.parseDouble(tokens[2]);
			double h  = Double.parseDouble(tokens[3]);
			item = new EllipseItem(xc, yc, w, h);
		}
		catch (Exception ex) {
			Log.error("Invalid EllipseTool data string: " + data, ex);
		}
		return item;
	}

	@Override
   	public CadBox getRectangle() {
     	return new CadBox(_xCenter - _xRadius, _yCenter - _yRadius, _xCenter  + _xRadius, _yCenter + _yRadius);
   	}
	
	@Override 
	public boolean intersects(CadLine line) {
		return line.intersects(this.getRectangle());
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(_xCenter - _xRadius, _yCenter - _yRadius, 2 * _xRadius, 2 * _yRadius);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", _xCenter, _yCenter, _xRadius, _yRadius);
	}

	@Override
	public boolean intersects(CadPoint point) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public EllipseItem deepCopy() {
		EllipseItem newDeepCopy = new EllipseItem(this._xCenter, this._yCenter, this._xRadius, this._yRadius);
		return newDeepCopy;
	}
	
}


