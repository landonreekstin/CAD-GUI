package csci240.prinCad.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class SaveFileCommand extends CommandHandler {
	
	// Constructor
	public SaveFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle Save File Event");
		
		try {
			Window stage = getCanvas().getScene().getWindow();
			
			if (_canvas.getIsModelFile()) {
				File fileNew = new File(_canvas.getCurrentFile());
				File filePreviousState = new File(_canvas.getCurrentFile());
				
				// Creates backup file of previous save state
				File fileRename = new File(filePreviousState + ".bak");
				filePreviousState.renameTo(fileRename);
				FileWriter fw = new FileWriter(filePreviousState);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw, true);
				
				// Overwrites open file
				FileWriter fwNew = new FileWriter(fileNew);
				BufferedWriter bwNew = new BufferedWriter(fwNew);
				PrintWriter outNew = new PrintWriter(bwNew, true);
				
				_canvas.saveToFile(outNew);
				
				out.flush();
				out.close();
			}
			
			else {
			
				// saves as new file
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save Model");
				File file = fileChooser.showSaveDialog(stage);
				
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw, true);
				
				_canvas.saveToFile(out);
				_canvas.setIsModelFile(true);
				_canvas.passFileName(file);
			
				out.flush();
				out.close();
				
			}
		}
		catch (Exception ex) {	
			Log.error("", ex);
		}

	}

}
