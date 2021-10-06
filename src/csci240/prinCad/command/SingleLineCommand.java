package csci240.prinCad.command;

import csci240.prinCad.control.SingleLineTool;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class SingleLineCommand  extends CommandHandler {
	
	// Constructor
	public SingleLineCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Single Line CAD Tools Event");
		
		_canvas.setActiveTool(new SingleLineTool());
		
	}

}