package csci240.prinCad.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import csci240.prinCad.util.Log;

/**
 * Creates default settings or uploads from file
 */
public class UserSettings {
	
	private int SceneWidth = 720;
	private int SceneHeight = 720;
	private String SceneBackgroundColor = "0x00000000";
	private int CanvasWidth = 450;
	private int CanvasHeight = 450;
	private String CanvasBackgroundColor = "0x00123456";
	private Log.LoggingLevel _loggingLevel = Log.LoggingLevel.None;
	
	// Constructor
	public UserSettings() {
		
	}
	
	// Getters and Setters
	public int getSceneWidth() {return SceneWidth;}
	public int getSceneHeight() {return SceneHeight;}
	public String getSceneBackgroundColor() {return SceneBackgroundColor;}
	public int getCanvasWidth() {return CanvasWidth;}
	public int getCanvasHeight() {return CanvasHeight;}
	public String getCanvasBackgroundColor() {return CanvasBackgroundColor;}
	public Log.LoggingLevel getLoggingLevel() {return _loggingLevel;}
	
	public void setSceneWidth(int SceneWidth) {this.SceneWidth = SceneWidth;}
	public void setSceneHeight(int SceneHeight) {this.SceneHeight = SceneHeight;}
	public void setSceneBackgrounColor(String SceneBackgroundColor) {this.SceneBackgroundColor = SceneBackgroundColor;}
	public void setCanvasWidth(int CanvasWidth) {this.CanvasWidth = CanvasWidth;}
	public void setCanvasHeight(int CanvasHeight) {this.CanvasHeight = CanvasHeight;}
	public void setCanvasBackgrounColor(String CanvasBackgroundColor) {this.CanvasBackgroundColor = CanvasBackgroundColor;}
	
	
	public void loadSettings(String newLine) { // Loads the integer settings from the text file and saves them as a 'settings' object
		
		String[] arrOfStr = newLine.split("=", 2);
		
		switch (arrOfStr[0]) {
		case "SceneWidth":
			this.SceneWidth = Integer.parseInt(arrOfStr[1]);
			System.out.println("Scene width scanned");
			break;
		case "SceneHeight":
			this.SceneHeight = Integer.parseInt(arrOfStr[1]);
			System.out.println("Scene height scanned");
			break;
		case "SceneBackgroundColor":
			this.SceneBackgroundColor = arrOfStr[1];
			System.out.println("Scene color scanned");
			break;
		case "CanvasWidth":
			this.CanvasWidth = Integer.parseInt(arrOfStr[1]);
			System.out.println("Canvas width scanned");
			break;
		case "CanvasHeight":
			this.CanvasHeight = Integer.parseInt(arrOfStr[1]);
			System.out.println("Canvas height scanned");
			break;
		case "CanvasBackgroundColor":
			this.CanvasBackgroundColor = arrOfStr[1];
			System.out.println("Canvas color scanned");
			break;
		case "LoggingLevel":
			this._loggingLevel = Log.LoggingLevel.valueOf(arrOfStr[1]);
			System.out.println("Logging level scanned");
			break;
		}
		
	}
	
	public void setSettings() { // Sets the settings loaded from the txt file in loadSettings if the file exists
								// If the file dne, creates it
		
		File file = new File("C:\\Users\\lando\\Documents\\Obj Oriented Prog\\eclipse-workspace\\PrinCADReekstin\\UserSettings.txt");
		
		// Check if the UserSettings file exists
		// If true, writes current file settings
		// If false, creates file first
		 if (file.exists()) {
			 Scanner scanner;
			try {
				scanner = new Scanner(file);
			 
				while(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					this.loadSettings(line);
				}
				scanner.close();
			} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 
		 else { // if the UserSettings file does not exist, creates it
	        	
        	 try {
        	      
        	      if (file.createNewFile()) {
        	        System.out.println("File created: " + file.getName());
        	      } else {
        	        System.out.println("File already exists.");
        	      }
        	    } catch (IOException error) {
        	      System.out.println("An error occurred.");
        	      error.printStackTrace();
        	    }
    
        }
		 
	}
	
	public void saveSettings() { // Saves the current settings to the UserSettings file
		
		File file = new File("C:\\Users\\lando\\Documents\\Obj Oriented Prog\\eclipse-workspace\\PrinCADReekstin\\UserSettings.txt");
		
		try {
		      FileWriter fileWriter = new FileWriter(file);
		      fileWriter.write("SceneWidth=" + this.SceneWidth + "\r\n"
		      		+ "SceneHeight=" + this.SceneHeight + "\r\n"
		      		+ "SceneBackgroundColor=" + this.SceneBackgroundColor + "\r\n"
		      		+ "CanvasWidth=" + this.CanvasWidth + "\r\n"
		      		+ "CanvasHeight=" + this.CanvasHeight + "\r\n"
		      		+ "CanvasBackgroundColor=" + this.CanvasBackgroundColor + "\r\n"
		      		+ "LoggingLevel=" + this._loggingLevel);
		      fileWriter.close();
		      System.out.println("File written successfully.");
		    } catch (IOException error2) {
		      System.out.println("An error occurred.");
		      error2.printStackTrace();
		    }
		
	}

}
