package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class OpenFileCommand extends CommandHandler {
	
	// Constructor
	public OpenFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Open File Event");
		
		try {
			Window stage = _canvas.getScene().getWindow();
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load Model");
			File file = fileChooser.showOpenDialog(stage);
			
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				
				_canvas.loadFromFile(reader);
				_canvas.setIsModelFile(true);
				_canvas.passFileName(file);
				
				reader.close();
				fr.close();
			}
		}
		catch (Exception ex) {
			Log.error("", ex);
		}

	}

}
