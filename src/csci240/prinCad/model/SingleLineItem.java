package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class SingleLineItem extends CadItem {
	
	public final double _xS;
	public final double _yS;
	public final double _xE;
	public final double _yE;
	
	public SingleLineItem(double xS, double yS, double xE, double yE) {
		_xS = xS;
		_yS = yS;
		_xE = xE;
		_yE = yE;
	}
	
	// load rectangle item from string data
	public static SingleLineItem load(String data) {
		
		SingleLineItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xS = Double.parseDouble(tokens[0]);
			double yS = Double.parseDouble(tokens[1]);
			double xE  = Double.parseDouble(tokens[2]);
			double yE  = Double.parseDouble(tokens[3]);
			item = new SingleLineItem(xS, yS, xE, yE);
		}
		catch (Exception ex) {
			Log.error("Invalid SingleLineTool data string: " + data, ex);
		}
		return item;
	}

	@Override
   	public CadBox getRectangle() {
     	return new CadBox(Math.min(_xS, _xE), Math.min(_yS, _yE), 
     			Math.min(_xS, _xE) + Math.abs(_xE - _xS), Math.min(_yS, _yE) + Math.abs(_yE - _yS));
   	}
	
	@Override
	public boolean intersects(CadLine line) {
		return line.intersects(_xS, _yS, _xE, _yE);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_xS, _yS, _xE, _yE);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", _xS, _yS, _xE, _yE);
	}

	@Override
	public boolean intersects(CadPoint point) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public SingleLineItem deepCopy() {
		SingleLineItem newDeepCopy = new SingleLineItem(this._xS, this._yS, this._xE, this._yE);
		return newDeepCopy;
	}
	
}


