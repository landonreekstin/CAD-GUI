package csci240.prinCad.command;

import csci240.prinCad.control.*;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class CircleCommand  extends CommandHandler {
	
	// Constructor
	public CircleCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Circle CAD Tools Event");
		
		_canvas.setActiveTool(new CircleTool());
		
	}

}