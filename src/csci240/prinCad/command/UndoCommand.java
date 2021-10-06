package csci240.prinCad.command;

import csci240.prinCad.model.ModelManager;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class UndoCommand  extends CommandHandler {
	
	// Constructor
	public UndoCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Undo Edit Event");
		ModelManager model = new ModelManager(5);
		model = _canvas.getModel();
		model.undoState();
		_canvas.draw();
	}

}
