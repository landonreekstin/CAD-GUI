package csci240.prinCad.ui;

import csci240.prinCad.command.BoxMarkerCommand;
import csci240.prinCad.command.CircleCommand;
import csci240.prinCad.command.CrisscrossMarkerCommand;
import csci240.prinCad.command.PlusMarkerCommand;
import csci240.prinCad.command.PolylineCommand;
import csci240.prinCad.command.RectangleCommand;
import csci240.prinCad.command.SingleLineCommand;
import csci240.prinCad.command.EllipseCommand;

import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Creates graphics for CAD Tools tab and buttons
 * @author lando
 *
 */
public class CadToolsManager {
	
	// Cad Tools commands
	private PlusMarkerCommand _plusMarkerCommand;
	private BoxMarkerCommand _boxMarkerCommand;
	private CrisscrossMarkerCommand _crisscrossMarkerCommand;
	private SingleLineCommand _singleLineCommand;
	private CircleCommand _circleCommand;
	private RectangleCommand _rectangleCommand;
	private PolylineCommand _polylineCommand;
	private EllipseCommand _ellipseCommand;
	
	
	// Build menu
	public Menu buildMenu() {
		
        // create menu items 
        MenuItem miPlus = new MenuItem("Plus"); 
        miPlus.setOnAction(e -> _plusMarkerCommand.action(e));
        MenuItem miBox = new MenuItem("Box"); 
        miBox.setOnAction(e -> _boxMarkerCommand.action(e));
        MenuItem miCrisscross = new MenuItem("Crisscross"); 
        miCrisscross.setOnAction(e -> _crisscrossMarkerCommand.action(e));
        // Place inside Markers as cascading tab
        Menu markerMenu = new Menu("Markers");
        ObservableList<MenuItem> markerMenuItems = markerMenu.getItems();
        
        // add individual marker menu items to menu
        markerMenuItems.add(miPlus);
        markerMenuItems.add(miBox);
        markerMenuItems.add(miCrisscross);
        
        // Separate menu items
        MenuItem miSingleLine = new MenuItem("Single Line"); 
        miSingleLine.setOnAction(e -> _singleLineCommand.action(e));
        MenuItem miCircle = new MenuItem("Circle"); 
        miCircle.setOnAction(e -> _circleCommand.action(e));
        MenuItem miRectangle = new MenuItem("Rectangle"); 
        miRectangle.setOnAction(e -> _rectangleCommand.action(e));
        MenuItem miPolyline = new MenuItem("Polyline"); 
        miPolyline.setOnAction(e -> _polylineCommand.action(e));
        MenuItem miEllipse = new MenuItem("Ellipse"); 
        miEllipse.setOnAction(e -> _ellipseCommand.action(e));
		
        // create a menu 
        Menu cadToolMenu = new Menu("CAD Tools"); 
        ObservableList<MenuItem> cadToolMenuItems = cadToolMenu.getItems();
        // add menu items to menu 
        cadToolMenuItems.add(markerMenu); 
        cadToolMenuItems.add(miSingleLine);
        cadToolMenuItems.add(miCircle);
        cadToolMenuItems.add(miRectangle);
        cadToolMenuItems.add(miPolyline);
        cadToolMenuItems.add(miEllipse);
	
        return cadToolMenu;
	}
	
	// Add buttons to bar
	public void addButtonsToBar(ObservableList<Node> nodes) {
		
		// Create buttons
		Button pmb = new Button();
		pmb.setMinWidth(80);
		pmb.setText("Plus");
		pmb.setOnAction(e -> _plusMarkerCommand.action(e));
		
		Button bmb = new Button();
		bmb.setMinWidth(80);
		bmb.setText("Box");
		bmb.setOnAction(e -> _boxMarkerCommand.action(e));
		
		Button cmb = new Button();
		cmb.setMinWidth(80);
		cmb.setText("Crisscross");
		cmb.setOnAction(e -> _crisscrossMarkerCommand.action(e));
		
		Button slb = new Button();
		slb.setMinWidth(80);
		slb.setText("Single Line");
		slb.setOnAction(e -> _singleLineCommand.action(e));
		
		Button cb = new Button();
		cb.setMinWidth(80);
		cb.setText("Circle");
		cb.setOnAction(e -> _circleCommand.action(e));
		
		Button rb = new Button();
		rb.setMinWidth(80);
		rb.setText("Rectangle");
		rb.setOnAction(e -> _rectangleCommand.action(e));
		
		Button plb = new Button();
		plb.setMinWidth(80);
		plb.setText("Polyline");
		plb.setOnAction(e -> _polylineCommand.action(e));
		
		Button eb = new Button();
		eb.setMinWidth(80);
		eb.setText("Ellipse");
		eb.setOnAction(e -> _ellipseCommand.action(e));
	
		nodes.add(pmb);
		nodes.add(bmb);
		nodes.add(cmb);
		nodes.add(slb);
		nodes.add(cb);
		nodes.add(rb);
		nodes.add(plb);
		nodes.add(eb);
	}

	
	public CadToolsManager(PrinCanvas canvas) {
		_plusMarkerCommand = new PlusMarkerCommand(canvas);
		_boxMarkerCommand = new BoxMarkerCommand(canvas);
		_crisscrossMarkerCommand = new CrisscrossMarkerCommand(canvas);
		_singleLineCommand = new SingleLineCommand(canvas);
		_circleCommand = new CircleCommand(canvas);
		_rectangleCommand = new RectangleCommand(canvas);
		_polylineCommand = new PolylineCommand(canvas);
		_ellipseCommand = new EllipseCommand(canvas);
		
	}



}
