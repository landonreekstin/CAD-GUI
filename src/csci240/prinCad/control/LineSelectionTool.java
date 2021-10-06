package csci240.prinCad.control;

import csci240.prinCad.model.CadLine;
import csci240.prinCad.model.ModelManager;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Controls linear selection
 */
public class LineSelectionTool extends CadTool{
	
	// Mouse movement properties
	boolean _activeMouse;
	
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
			if (_activeMouse) {
				canvas.draw();
				_x = Math.min(_xPivot, _xEnd) - 1;
				_y = Math.min(_yPivot, _yEnd) - 1;
				_w = Math.abs(_xEnd - _xPivot) + 2;
				_h = Math.abs(_yEnd - _yPivot) + 2;
				_xEnd = e.getX();
				_yEnd = e.getY();
				canvas.getGC().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
			}
		}
		
		@Override
		// Handle mouse release (button up)
		public void onMouseReleased(CanvasToolInterface canvas, MouseEvent e) {
			if (_activeMouse) {
				_activeMouse = false;
				canvas.setCursor(Cursor.DEFAULT);
				ModelManager model = canvas.getModel();
            	model.lineSelect(new CadLine(_xPivot, _yPivot, _xEnd, _yEnd));
            	canvas.draw();
			}
		}
		
}
