package csci240.prinCad.command;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class DeleteCommand  extends CommandHandler {
	
	// Constructor
	public DeleteCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Delete Edit Event");
		
		_canvas.delete();
		_canvas.draw();
	}

}