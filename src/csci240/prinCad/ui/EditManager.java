package csci240.prinCad.ui;

import csci240.prinCad.command.DeleteCommand;
import csci240.prinCad.command.PropertiesCommand;
import csci240.prinCad.command.RedoCommand;
import csci240.prinCad.command.ToggleSelectionCommand;
import csci240.prinCad.command.UndoCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Creates graphics for Edit menu & buttons
 */
public class EditManager {
	
	// Edit commands
	private UndoCommand _undoCommand;
	private RedoCommand _redoCommand;
	private DeleteCommand _deleteCommand;
	private ToggleSelectionCommand _toggleSelectionCommand;
	private PropertiesCommand _propertiesCommand;
	
	public Menu buildMenu() {
		// Edit Menu Items
	    MenuItem miUndo = new MenuItem("Undo");
	    miUndo.setOnAction(e -> _undoCommand.action(e));
	    
	    MenuItem miRedo = new MenuItem("Redo");
	    miRedo.setOnAction(e -> _redoCommand.action(e));
	    
	    MenuItem miDelete = new MenuItem("Delete");
	    miDelete.setOnAction(e -> _deleteCommand.action(e));
	    
	    MenuItem miToggleSelection = new MenuItem("Toggle Selection");
	    miToggleSelection.setOnAction(e -> _toggleSelectionCommand.action(e));
	    
	    MenuItem miProperties = new MenuItem("Properties");
	    miProperties.setOnAction(e -> _propertiesCommand.action(e));
	    
	    // Edit tab
        Menu editMenu = new Menu("Edit"); 
        ObservableList<MenuItem> editMenuItems = editMenu.getItems();
        
        // edit items
        editMenuItems.add(miUndo);
        editMenuItems.add(miRedo);
        editMenuItems.add(miDelete);
        editMenuItems.add(miToggleSelection);
        editMenuItems.add(miProperties);

        return editMenu;
	}
	
	// Add buttons to bar
	public void addButtonsToBar(ObservableList<Node> nodes) {
		
		// Create buttons
		Button ub = new Button();
		ub.setMinWidth(80);
		ub.setText("Undo");
		ub.setOnAction(e -> _undoCommand.action(e));
		
		Button rb = new Button();
		rb.setMinWidth(80);
		rb.setText("Redo");
		rb.setOnAction(e -> _redoCommand.action(e));
	
		Button db = new Button();
		db.setMinWidth(80);
		db.setText("Delete");
		db.setOnAction(e -> _deleteCommand.action(e));
		
		Button tsb = new Button();
		tsb.setMinWidth(80);
		tsb.setText("^ Selection");
		tsb.setOnAction(e -> _toggleSelectionCommand.action(e));
		
		Button pb = new Button();
		pb.setMinWidth(80);
		pb.setText("Properties");
		pb.setOnAction(e -> _propertiesCommand.action(e));
		
		nodes.add(ub);
		nodes.add(rb);
		nodes.add(db);
		nodes.add(tsb);
		nodes.add(pb);
		
	}
	
	public EditManager(PrinCanvas canvas) {
		_undoCommand = new UndoCommand(canvas);
		_redoCommand = new RedoCommand(canvas);
		_deleteCommand = new DeleteCommand(canvas);
		_toggleSelectionCommand = new ToggleSelectionCommand(canvas);
		_propertiesCommand = new PropertiesCommand(canvas);
	}

}
