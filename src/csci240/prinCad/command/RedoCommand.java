package csci240.prinCad.command;

import csci240.prinCad.model.ModelManager;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class RedoCommand  extends CommandHandler {
	
	// Constructor
	public RedoCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Redo Edit Event");
		ModelManager model = new ModelManager(5);
		model = _canvas.getModel();
		model.redoState();
		_canvas.draw();
	}

}