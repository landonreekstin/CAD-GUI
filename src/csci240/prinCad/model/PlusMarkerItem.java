package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class PlusMarkerItem extends CadItem {
	
	public final double _x;
	public final double _y;
	public final double markerSize = BoxMarkerItem.markerSize;
	
	public PlusMarkerItem(double x, double y) {
		_x = x;
		_y = y;
	}
	
	// load rectangle item from string data
	public static PlusMarkerItem load(String data) {
		
		PlusMarkerItem item = null;
		try {
			String[] tokens = data.split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			item = new PlusMarkerItem(x, y);
		}
		catch (Exception ex) {
			Log.error("Invalid PlusMarkerTool data string: " + data, ex);
		}
		return item;
	}

	@Override
   	public CadBox getRectangle() {
     	return new CadBox(_x - markerSize/2, _y - markerSize/2, _x + markerSize, _y + markerSize);
   	}
	
	@Override 
	public boolean intersects(CadLine line) {
		return line.intersects(this.getRectangle());
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_x - markerSize, _y, _x + markerSize, _y);
		gc.strokeLine(_x, _y - markerSize, _x, _y + markerSize);
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
	public PlusMarkerItem deepCopy() {
		PlusMarkerItem newDeepCopy = new PlusMarkerItem(this._x, this._y);
		return newDeepCopy;
	}
	
}
