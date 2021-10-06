package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class BoxMarkerItem extends CadItem {
	
	public final double _x;
	public final double _y;
	// All other markers use this size
	public final static double markerSize = 4;
	
	public BoxMarkerItem(double x, double y) {
		_x = x;
		_y = y;
	}
	
	// load rectangle item from string data
	public static BoxMarkerItem load(String data) {
		
		BoxMarkerItem item = null;
		try {
			String[] tokens = data.split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			item = new BoxMarkerItem(x, y);
		}
		catch (Exception ex) {
			Log.error("Invalid BoxMarkerTool data string: " + data, ex);
		}
		return item;
	}
	
	@Override 
	public boolean intersects(CadLine line) {
		return line.intersects(this.getRectangle());
	}
	
	@Override
   	public CadBox getRectangle() {
     	return new CadBox(_x - markerSize/2, _y - markerSize/2, _x + markerSize, _y + markerSize);
   	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeRect(_x-markerSize, _y-markerSize,2*markerSize, 2*markerSize);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f", _x, _y);
	}

	@Override
	public boolean intersects(CadPoint point) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public BoxMarkerItem deepCopy() {
		BoxMarkerItem newDeepCopy = new BoxMarkerItem(this._x, this._y);
		return newDeepCopy;
	}
	
}