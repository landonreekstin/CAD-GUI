package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class CadItem {
	
	// Is this item selected?
   	protected boolean _isSelected;


	public abstract void draw(GraphicsContext gc);
	
	public void draw(GraphicsContext gc, Color normalColor, Color selectColor) {
     	if (_isSelected) {
            	gc.setStroke(selectColor);
            	gc.setLineWidth(1);

            	draw(gc);
            	
            	gc.setStroke(normalColor);
            	gc.setLineWidth(0);
     	}
     	else {
            	draw(gc);
     	}
            	
	}
	
	public boolean getIsSelected() {return this._isSelected;}
	
	public abstract String save();
	
	public void boxSelect(CadBox selectionBox) {
     	if (selectionBox.contains(this.getRectangle()))
            	_isSelected = !_isSelected;
	}  	
	
	public void lineSelect(CadLine line) {
     	if (this.intersects(line))
            	_isSelected = !_isSelected;
	} 
	
	public void pointSelect(CadPoint point) {
     	if (this.intersects(point))
            	_isSelected = !_isSelected;
	} 
	
	// Get rectangle of derived CAD item
	public abstract CadBox getRectangle();
	
	public abstract boolean intersects(CadLine line);
	
	public abstract boolean intersects(CadPoint point);
	
	public abstract CadItem deepCopy();
	
}

