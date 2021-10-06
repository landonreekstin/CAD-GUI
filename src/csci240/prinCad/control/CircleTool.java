package csci240.prinCad.control;

import csci240.prinCad.model.CircleItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class CircleTool extends CadTool {
	
	// Mouse movement properties
	boolean _activeMouse;
	double _radius;


	// Only place a marker when the primary mouse button is clicked
	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {

		if (e.getButton() == MouseButton.PRIMARY) {
			_x = e.getX();
			_y = e.getY();
			_xPivot = _x;
			_yPivot = _y;
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
			_radius = Math.sqrt(Math.pow(_xEnd - _x, 2) + Math.pow(_yEnd - _y, 2));
			_w = 2 * _radius;
			_h = _w;
			canvas.getGC().strokeOval(_x - _radius, _y - _radius, _w, _h);
		}
	}

	// Actually place the marker when the user has released the mouse button
	@Override
	public void onMouseReleased(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.reset(new CircleItem(_xPivot, _yPivot, _radius));
		}
	}

}
