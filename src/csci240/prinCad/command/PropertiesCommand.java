package csci240.prinCad.command;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PropertiesCommand  extends CommandHandler {
	
	// Constructor
	public PropertiesCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Properties Edit Event");
	}

}