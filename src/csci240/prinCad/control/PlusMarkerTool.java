package csci240.prinCad.control;

import csci240.prinCad.model.PlusMarkerItem;
import javafx.scene.input.MouseEvent;

public class PlusMarkerTool extends MarkerTool {

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {
		
		double x = e.getX();
		double y = e.getY();
		
		canvas.draw();
		
		canvas.getGC().strokeLine(x - MarkerSize, y, x + MarkerSize, y);
		canvas.getGC().strokeLine(x, y - MarkerSize, x, y + MarkerSize);
		
		canvas.reset(new PlusMarkerItem(x, y));
	}

}

