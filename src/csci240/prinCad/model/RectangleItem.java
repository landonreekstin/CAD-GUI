package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class RectangleItem extends CadItem {
	
	public final double _x;
	public final double _y;
	public final double _w;
	public final double _h;
	
	public RectangleItem(double x, double y, double w, double h) {
		_x = x;
		_y = y;
		_w = w;
		_h = h;
	}
	
	// load rectangle item from string data
	public static RectangleItem load(String data) {
		
		RectangleItem item = null;
		try {
			String[] tokens = data.split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			double w  = Double.parseDouble(tokens[2]);
			double h  = Double.parseDouble(tokens[3]);
			item = new RectangleItem(x, y, w, h);
		}
		catch (Exception ex) {
			Log.error("Invalid RectangleTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override
   	public CadBox getRectangle() {
     	return new CadBox(_x, _y, _x + _w, _y + _h);
   	}
	
	@Override 
	public boolean intersects(CadLine line) {
		return line.intersects(this.getRectangle());
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeRect(_x, _y, _w, _h);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", _x, _y, _w, _h);
	}

	@Override
	public boolean intersects(CadPoint point) {
		return false;
	}
	
	@Override
	public RectangleItem deepCopy() {
		RectangleItem newDeepCopy = new RectangleItem(this._x, this._y, this._w, this._h);
		return newDeepCopy;
	}
	
}

