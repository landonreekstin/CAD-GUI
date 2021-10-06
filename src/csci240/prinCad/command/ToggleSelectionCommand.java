package csci240.prinCad.command;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class ToggleSelectionCommand  extends CommandHandler {
	
	// Constructor
	public ToggleSelectionCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Toggle Selection Edit Event");
		
		this._canvas.toggleSelection();
		
	}

}