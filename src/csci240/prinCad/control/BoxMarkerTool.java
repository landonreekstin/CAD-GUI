package csci240.prinCad.control;

import csci240.prinCad.model.BoxMarkerItem;
import javafx.scene.input.MouseEvent;

public class BoxMarkerTool extends MarkerTool {

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {
		
		double x = e.getX();
		double y = e.getY();
		
		canvas.draw();
		
		canvas.getGC().strokeRect(x-MarkerSize, y-MarkerSize,2*MarkerSize, 2*MarkerSize);
		canvas.reset(new BoxMarkerItem(x, y));
	}

}