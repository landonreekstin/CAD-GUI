package csci240.prinCad.command;
import javafx.event.ActionEvent;

/**
 * Abstract class to handle Edit and File Commands
 */
public abstract class CommandHandler {
	// Owning canvas
	CanvasCommandInterface _canvas;
	public final CanvasCommandInterface getCanvas() { return _canvas; }
	
	// Constructor
	public CommandHandler(CanvasCommandInterface canvas) {
		_canvas = canvas;
	}
		
	// Handle action event
	/**
	 * Controls commands to be executed when File and Edit buttons are pressed
	 */
	public abstract void action(ActionEvent e);

}
