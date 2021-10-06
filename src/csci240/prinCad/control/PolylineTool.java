package csci240.prinCad.control;

import java.util.List;
import java.util.stream.Stream;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class PolylineTool extends CadTool {
	
	Double xDoubles[];
	Double yDoubles[];
	double[] xPoints;
	double[] yPoints;
	int nPoints = 0;
	
	// ArrayList for Doubles to add new points
	List<Double> arrListX = new ArrayList<Double>(Arrays.asList(xDoubles));
	List<Double> arrListY = new ArrayList<Double>(Arrays.asList(yDoubles));
	
	// Mouse movement properties
	boolean _activeMouse;


	// Only place a marker when the primary mouse button is clicked
	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			_x = e.getX();
			_y = e.getY();
			
			// Add point to Arraylist as a Double
			arrListX.add(_x);
			arrListY.add(_y);
			nPoints++;
			
			// Convert back to  array of Doubles
			xDoubles = arrListX.toArray(xDoubles);
			yDoubles = arrListY.toArray(yDoubles);
			// Convert to array of elemental doubles
			xPoints = Stream.of(xDoubles).mapToDouble(Double::doubleValue).toArray();
			yPoints = Stream.of(yDoubles).mapToDouble(Double::doubleValue).toArray();
			
			_activeMouse = true;
			canvas.getGC().setStroke(Color.ORANGERED);
			canvas.getGC().setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
			canvas.getGC().strokePolyline(xPoints, yPoints, nPoints);
		}
		else if (e.getButton() == MouseButton.SECONDARY) {
			// end polyline
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			nPoints = 0;
			
			canvas.reset();
		}
	}

	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) { 
		if (_activeMouse) {
			_x = Math.min(_xPivot, _xEnd) - 1;
			_y = Math.min(_yPivot, _yEnd) - 1;
			_w = Math.abs(_xEnd - _xPivot) + 2;
			_h = Math.abs(_yEnd - _yPivot) + 2;
			canvas.getGC().fillRect(_x,  _y,  _w,  _h);
			_xEnd = e.getX();
			_yEnd = e.getY();
			canvas.getGC().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}

	// Actually place the marker when the user has released the mouse button
	@Override
	public void onMouseReleased(CanvasToolInterface canvas, MouseEvent e) {

	}

}
