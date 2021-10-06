package csci240.prinCad.command;

import csci240.prinCad.control.*;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class EllipseCommand  extends CommandHandler {
	
	// Constructor
	public EllipseCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Ellipse CAD Tools Event");
		
		_canvas.setActiveTool(new EllipseTool());
		
	}

}