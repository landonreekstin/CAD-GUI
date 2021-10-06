package csci240.prinCad.command;

import csci240.prinCad.control.*;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class RectangleCommand  extends CommandHandler {
	
	// Constructor
	public RectangleCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Rectangle CAD Tools Event");
		
		_canvas.setActiveTool(new RectangleTool());
		
	}

}