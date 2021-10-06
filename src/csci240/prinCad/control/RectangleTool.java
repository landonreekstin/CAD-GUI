package csci240.prinCad.control;

import csci240.prinCad.model.RectangleItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class RectangleTool extends CadTool {
	
	// Mouse movement properties
	boolean _activeMouse;

	// Only place a marker when the primary mouse button is clicked
	@Override
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
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) { 
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

	// Actually place the marker when the user has released the mouse button
	@Override
	public void onMouseReleased(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.reset(new RectangleItem(_x, _y, _w, _h));
		}
	}

}