package csci240.prinCad.model;

import java.util.ArrayList;

public class ModelData {

	// Collection of cad items
	final private ArrayList<CadItem> _items;
	public ArrayList<CadItem> getItems() {
		return _items;
	}
	
	// Constructor
	public ModelData(ArrayList<CadItem> items) {
		_items = new ArrayList<CadItem>();
		for (CadItem item : items) {
			_items.add(item.deepCopy()); // deep copy
		}
	}
}

