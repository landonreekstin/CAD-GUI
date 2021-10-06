package csci240.prinCad.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.control.BoxSelectionTool;
import csci240.prinCad.control.CadTool;
import csci240.prinCad.control.LineSelectionTool;
import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.ModelManager;
import csci240.prinCad.control.CanvasToolInterface;

//Drawing canvas for the Prin CAD tools application
/**
 * Creates canvas and graphics for all drawing on the canvas
 */
public class PrinCanvas extends Canvas implements CanvasToolInterface, CanvasCommandInterface {
	
	// file state
	boolean isModelFile = false;
	String currentFile;
	
	// Current selection tool
	CadTool _selectionTool;
	
	// model manager
	private ModelManager _model;
	
	// Reference to graphics context
	GraphicsContext _gc;
	public GraphicsContext getGC() { return _gc; }
	
	// Active tool - may not be a selection tool
	private CadTool _activeTool;
	
	@Override
	public void setActiveTool(CadTool activeTool) { _activeTool = activeTool; }
	
	// Mouse movement properties
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	
	// Data constructor
	public PrinCanvas(double width, double height) {
		// invoke (call) parent class constructor
		super(width, height);
		
		// Initialize model manager
		_model = new ModelManager(5);
		
		// Get graphics context and fill with background color
		_gc = getGraphicsContext2D();
		// Convert int[] from txt file to RGB argument
		Color canvasHexColor = Color.valueOf(MainForm.settings.getCanvasBackgroundColor());
		_gc.setFill(canvasHexColor);
		_gc.fillRect(0, 0, MainForm.settings.getCanvasWidth(), MainForm.settings.getCanvasHeight());
		
		// Subscribe to mouse events
		setOnMousePressed(e -> onMousePressed(e));
		setOnMouseDragged(e -> onMouseDrag(e));
		setOnMouseReleased(e -> onMouseReleased(e));
		
		// Set default selection
		_selectionTool = new BoxSelectionTool();
		_activeTool = _selectionTool;
		
	}
	@Override
	public boolean getIsModelFile() {return isModelFile;}
	@Override
	public ModelManager getModel() {return _model;}
	@Override
	public String getCurrentFile() {return currentFile;}
	@Override
	public void setIsModelFile(boolean isModelFile) {this.isModelFile = isModelFile;}
	public void setCurrentFile(String currentFile) {this.currentFile = currentFile;}
	
	/**
	 * Allows selection tool toggle to be translated to current canvas
	 */
	@Override
	public void toggleSelection() {
		
		if (_selectionTool instanceof BoxSelectionTool)
			_selectionTool = new LineSelectionTool();
		else
			_selectionTool = new BoxSelectionTool();
		
		_activeTool = _selectionTool;
	}
	
	// Set back to selection mode
	@Override
	public void reset() {
		_activeTool = _selectionTool;
	}
	
	// Save created CAD item and set back to selection mode
	@Override
	public void reset(CadItem cadItem) {
		_model.add(cadItem);
		_activeTool = _selectionTool;
	}
	
	@Override
	public void emptyState() {
		_model.firstState();
	}
	
	// Draw all graphic objects
	@Override
	public void draw() {
		_gc.fillRect(0, 0, getWidth(), getHeight());
		_gc.setStroke(Color.ORANGERED);
		_gc.setLineWidth(0);
		_model.draw(_gc);
	}
	
	// Clear screen
	@Override
	public void clear() {
		_gc.clearRect(0, 0, MainForm.settings.getCanvasWidth(), MainForm.settings.getCanvasHeight());
		// Redraw Canvas
		Color canvasHexColor = Color.valueOf(MainForm.settings.getCanvasBackgroundColor());
		_gc.setFill(canvasHexColor);
		_gc.fillRect(0, 0, MainForm.settings.getCanvasWidth(), MainForm.settings.getCanvasHeight());
		_model.clear();
	}
	
	// Delete selected items
	@Override
	public void delete() {
		_model.remove();
	}
	
	@Override
	public void undo() {
		_model.undoState();
	}

	// Persist data
	@Override
	public void saveToFile(PrintWriter out) {
		_model.save(out);
	}
	
	@Override
	public void loadFromFile(BufferedReader reader) {
		_model.clear();
		_model.load(reader);
		draw();
	}
	
	@Override
	public void passFileName(File file) {
		this.currentFile = file.getPath();
	}
	
	// Handle mouse pressed (button down)
	private void onMousePressed(MouseEvent e) {
		
		_activeTool.onMousePressed(this, e);
	}
	
	// Handle mouse drag (only called when mouse button IS depressed)
	private void onMouseDrag(MouseEvent e) {
		
		_activeTool.onMouseDrag(this, e);
	}
	
	// Handle mouse release (button up)
	private void onMouseReleased(MouseEvent e) {
		
		_activeTool.onMouseReleased(this, e);
	}

}


