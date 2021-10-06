package csci240.prinCad.control;

import javafx.scene.input.MouseEvent;

/**
 * Abstract class for selection tool
 */
public abstract class CadTool {
	CanvasToolInterface _canvas;
	double _x, _y, _w, _h;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	
	//static boolean toggle = true; // true = square, false = linear, square by default
	
	//public static boolean getToggle() {return toggle;}
	//public static void setToggle(boolean toggle) {SelectionCommand.toggle = toggle;}
	
	/**
	 * Controls events when mouse is pressed
	 */
	public abstract void onMousePressed(CanvasToolInterface canvas, MouseEvent e);
	/**
	 * Controls events when mouse is dragged
	 */
	public abstract void onMouseDrag(CanvasToolInterface canvas, MouseEvent e);
	/**
	 * Controls events when mouse is released
	 */
	public abstract void onMouseReleased(CanvasToolInterface canvas, MouseEvent e);

}
