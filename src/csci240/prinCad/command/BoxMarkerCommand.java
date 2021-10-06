package csci240.prinCad.command;

import csci240.prinCad.util.Log;
import csci240.prinCad.control.BoxMarkerTool;
import javafx.event.ActionEvent;

public class BoxMarkerCommand  extends CommandHandler {
	
	// Constructor
	public BoxMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Box Marker CAD Tools Event");
		
		_canvas.setActiveTool(new BoxMarkerTool());
		
	}

}