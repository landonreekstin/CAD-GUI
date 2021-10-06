/**
 * Controls rectangular selection
 */


// Recieved help from Dillon


package csci240.prinCad.control;

import csci240.prinCad.model.CadBox;
import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.ModelManager;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoxSelectionTool extends CadTool {
			
	// Mouse movement properties
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	
	@Override
	// Handle mouse pressed (button down)
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			_x = e.getX();
			_y = e.getY();
			_xPivot = _x;
			_yPivot = _y;
			_xEnd = _x;
			_yEnd = _y;
			_activeMouse = true;
			canvas.getGC().setStroke(Color.ORANGERED);
			canvas.getGC().setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
		}
	}
	
	@Override
	// Handle mouse drag (only called when mouse button IS depressed)
		public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {
		// _gc.clearRect(_xPivot, _yPivot, _xEnd, _yEnd);
			if (_activeMouse) {
				canvas.draw();
				_xEnd = e.getX();
				_yEnd = e.getY();
				_x = Math.min(_xPivot, _xEnd);
				_y = Math.min(_yPivot, _yEnd);
				_w = Math.abs(_xEnd - _xPivot);
				_h = Math.abs(_yEnd - _yPivot);
				canvas.getGC().strokeRect(_x, _y, _w, _h);
			}
		}
		
		@Override
		// Handle mouse release (button up)
		public void onMouseReleased(CanvasToolInterface canvas, MouseEvent e) {
			if (_activeMouse) {
				_activeMouse = false;
				canvas.setCursor(Cursor.DEFAULT);
				ModelManager model = canvas.getModel();
            	model.boxSelect(new CadBox(_x, _y, _x + _w, _y + _h));
            	model.pointSelect(new CadPoint(_x, _y));
            	canvas.draw();
			}
		}

}
