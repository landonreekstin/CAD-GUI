package csci240.prinCad.model;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ModelManager {
	
	// size of undo buffer
	final private int _undoSize; 
	
	private Deque<ModelData> _undoStack;
	private Deque<ModelData> _redoStack;

	ArrayList<CadItem> _items;
	
	// Constructor
	public ModelManager(int undoSize) {
		_undoSize = undoSize;
		_items = new ArrayList<CadItem>();
		_undoStack = new ArrayDeque<ModelData>();
		_redoStack = new ArrayDeque<ModelData>();
	}
	
	// Pushes current state to undo stack and clears redo stack
	private void saveState() {
		saveModelData();
		_redoStack.clear();
	}
	
	private void saveModelData() {
		ModelData modelData = new ModelData(_items);
		if (_undoStack.size() == _undoSize) {
			_undoStack.removeLast();
		}
		_undoStack.push(modelData);
	}
	
	public void undoState() {
		ModelData modelData = new ModelData(_items);
		if (_redoStack.size() == _undoSize && _undoStack.size() > 0) {
			_redoStack.removeLast();
		}
		if (_undoStack.size() > 0) {
			_redoStack.push(modelData); // current state to redo stack
			modelData = _undoStack.pop(); // first undo state becomes current
			_items = modelData.getItems();
		}
	}
	
	public void redoState() {
		ModelData modelData = new ModelData(_items);
		if (_undoStack.size() == _undoSize && _redoStack.size() > 0) {
			_undoStack.removeLast();
		}
		if (_redoStack.size() > 0) {
			_undoStack.push(modelData); // current state to undo stack
			modelData = _redoStack.pop(); // first redo state becomes current
			_items = modelData.getItems();
		}
	}
	
	public void firstState() {
		_items.add(null);
	}

	public void add(CadItem item) {
		saveState();
		_items.add(item);
	}
	
	// Removes selected items
	public void remove() {
		saveState();
		Iterator<CadItem> itr = _items.iterator();
		while (itr.hasNext()) {
			CadItem currentItem = itr.next();
        	if (currentItem.getIsSelected()) {
				itr.remove();
        	}
		}
	}
	
	public void clear() {
		_items.clear();
	}
	
	// select items
   	public void boxSelect(CadBox boxSelect) {
     	for (CadItem item : _items) {
            	item.boxSelect(boxSelect);
     	}
   	}
   	
   	// select items
   	public void lineSelect(CadLine lineSelect) {
     	for (CadItem item : _items) {
            	item.lineSelect(lineSelect);
     	}
   	}
   	
   	// select items
   	public void pointSelect(CadPoint pointSelect) {
     	for (CadItem item : _items) {
            	item.pointSelect(pointSelect);
     	}
   	}
	
	public void draw(GraphicsContext gc) {
		
		gc.setStroke(Color.ORANGERED);
     	gc.setLineWidth(0);
		
		for (CadItem item : _items) {
			item.draw(gc, Color.ORANGERED, Color.VIOLET);
		}
	}
	
	public void save(PrintWriter out) {
			
			for (CadItem item : _items) {
				Class<? extends CadItem> cl = item.getClass();
				String className = cl.getName();
					
				String data = item.save();
					
				out.println(String.format("<%1$s>%2$s</%1$s>", className, data));
			}
		}
	
	public void load(BufferedReader reader) {
		
		try {
			String line = reader.readLine();
			while (line != null) {
				
				int i1 = line.indexOf('<') + 1;
				int i2 = line.indexOf('>');
				String classType = line.substring(i1, i2);
				int i3 = line.indexOf("</" + classType + ">");
				String data = line.substring(i2 + 1, i3);
				
				CadItem cadItem;
				switch (classType) {
				case "csci240.prinCad.model.CircleItem":
					cadItem = CircleItem.load(data);
					break;
				case "csci240.prinCad.model.RectangleItem":
					cadItem = RectangleItem.load(data);
					break;
				case "csci240.prinCad.model.SingleLineItem":
					cadItem = SingleLineItem.load(data);
					break;
				case "csci240.prinCad.model.EllipseItem":
					cadItem = EllipseItem.load(data);
					break;
				case "csci240.prinCad.model.BoxMarkerItem":
					cadItem = BoxMarkerItem.load(data);
					break;
				case "csci240.prinCad.model.CrisscrossMarkerItem":
					cadItem = CrisscrossMarkerItem.load(data);
					break;
				case "csci240.prinCad.model.PlusMarkerItem":
					cadItem = PlusMarkerItem.load(data);
					break; 
				default:
					throw new Exception("Failed to parse line: " + line);
				}
				if (cadItem != null) {
					_items.add(cadItem);
				}
					
				line = reader.readLine();
			}
		}
		catch (Exception ex) {
			Log.error("Failed to load file. Exception: ", ex);
		}
	}

	
}

