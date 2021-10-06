package csci240.prinCad.command;

import csci240.prinCad.util.Log;
import csci240.prinCad.control.CrisscrossMarkerTool;
import javafx.event.ActionEvent;

public class CrisscrossMarkerCommand  extends CommandHandler {
	
	// Constructor
	public CrisscrossMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Crisscross Marker CAD Tools Event");
		
		_canvas.setActiveTool(new CrisscrossMarkerTool());
		
	}

}