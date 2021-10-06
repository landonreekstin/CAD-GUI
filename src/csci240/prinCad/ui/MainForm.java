/* Landon Reekstin
 * CSCI 240
 * PrinCAD Spring 2021
 * 
 *  References: W3Schools.com, StackOverflow.com*/


package csci240.prinCad.ui;

import java.io.File;
import csci240.prinCad.util.Log;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.net.URL;

public class MainForm extends Application {
	
	// Manager of file commands
	private FileManager _fileManager;
	// Manager of edit commands
	private EditManager _editManager;
	// Manager of Cad Tools commands
	private CadToolsManager _cadToolsManager;
	
	// Initialize deafault settings
	static UserSettings settings = new UserSettings();
	
	public static void main(String[] args) throws Exception {
        
        // Load and set settings from file or default class settings
        settings.setSettings();
        
        Log.setLoggingLevel(settings.getLoggingLevel());
     		
        Log.info("PrinCad begin execution"); // after restoring app settings
	
        try {
        	// Launch the javaFX application
			launch(args);
				
		// Write to file with current variables		
		settings.saveSettings();
		
		// Exception testing
		//File file = new File("nonexistingfile.txt");
		//Scanner scanner = new Scanner(file);
		
        }
		catch (Exception ex) {
			Log.error("PrinCad crash with exception ", ex);
			throw ex;
		}
		
		Log.info("PrinCad end execution");
		
	}
	
	// Override the start
	@Override public void start(Stage primaryStage)  throws Exception{
		
		// Create drawing canvas 
		PrinCanvas canvas = new PrinCanvas(settings.getCanvasWidth(), settings.getCanvasHeight());
		
		// Create file manager
		_fileManager = new FileManager(canvas);
		// Create edit manager
		_editManager = new EditManager(canvas);
		// Create CAD Tools manager
		_cadToolsManager = new CadToolsManager(canvas);
		
		// Convert hex value from txt file to RGB argument
		Color sceneHexColor = Color.valueOf(settings.getSceneBackgroundColor());
		
		// Create the typical monolithic border layout
		// Attach canvas to center of layout
		BorderPane pane = new BorderPane(canvas);
		Color sceneBackgroundColor = sceneHexColor;
		pane.setStyle(FormatStyleColor(sceneBackgroundColor));
		
		// Create menu bar
		MenuBar mb = new MenuBar();
		pane.setTop(mb);

		ObservableList<Menu> menus = mb.getMenus();
		        
		// add file menu to menu bar
		Menu fileMenu = _fileManager.buildMenu();
		menus.add(fileMenu);
		// add file menu to menu bar
		Menu editMenu = _editManager.buildMenu();
		menus.add(editMenu);
		// add CAD Tools menu to menu bar
		Menu cadToolsMenu = _cadToolsManager.buildMenu();
		menus.add(cadToolsMenu);
		
		// Create right VBox to hold the buttons
		VBox rvbox = new VBox(5);
		rvbox.setPadding(new Insets(10));
		rvbox.setAlignment(Pos.TOP_CENTER);
		pane.setRight(rvbox);
		// Create left VBox to hold the buttons
		VBox lvbox = new VBox(5);
		lvbox.setPadding(new Insets(10));
		lvbox.setAlignment(Pos.TOP_CENTER);
		pane.setLeft(lvbox);

		// Add buttons to bar
		ObservableList<Node> rnodes = rvbox.getChildren();
		_fileManager.addButtonsToBar(rnodes);
		_editManager.addButtonsToBar(rnodes);
		ObservableList<Node> lnodes = lvbox.getChildren();
		_cadToolsManager.addButtonsToBar(lnodes);
		
		// Create a scene, attach a layout pane to scene,
		// set the initial size and background color
		Scene scene = new Scene(pane, settings.getSceneWidth(), settings.getSceneHeight(), sceneBackgroundColor);
		
		// Apply application styles
		File file = new File("AppStyles.css");
		if (!file.exists()) {
			Log.info(file.toString() + " does not exist");
		}
		else {
			URL url = file.toURI().toURL();
			scene.getStylesheets().add(url.toExternalForm());
		}
		
		// Attach scene to stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("CSCI 240 PrinCad Project");
        primaryStage.show();
	}
	
	// Format color for use with setStyle
	private String FormatStyleColor(Color color) {
		String rx = String.format("%02X", Math.round(color.getRed() * 255.0));
		String gx = String.format("%02X", Math.round(color.getGreen() * 255.0));
		String bx = String.format("%02X", Math.round(color.getBlue() * 255.0));
		String fx = "-fx-background-color: #" + rx + gx + bx + ";";
		return fx;
	}

}
