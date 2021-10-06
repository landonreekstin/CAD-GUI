package csci240.prinCad.control;

import csci240.prinCad.model.BoxMarkerItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public abstract class MarkerTool extends CadTool {
	
protected final double MarkerSize = BoxMarkerItem.markerSize;
	
	// Mouse movement properties
	boolean _activeMouse;


	// Only place a marker when the primary mouse button is clicked
	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {

		if (e.getButton() == MouseButton.PRIMARY) {
			canvas.setCursor(Cursor.CROSSHAIR);
			_activeMouse = true;
		}
	}

	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) { }

	// Actually place the marker when the user has released the mouse button
	@Override
	public void onMouseReleased(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.getGC().setStroke(Color.ORANGERED);
			canvas.getGC().setLineWidth(0);
			
			Draw(canvas, e);
			
			canvas.reset();
		}
	}
	
	// Have the derived class do the actual drawing of the marker
	protected abstract void Draw(CanvasToolInterface canvas, MouseEvent e);

}
