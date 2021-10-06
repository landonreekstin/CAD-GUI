package csci240.prinCad.control;

import csci240.prinCad.model.CrisscrossMarkerItem;
import javafx.scene.input.MouseEvent;

public class CrisscrossMarkerTool extends MarkerTool {

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {
		
		double x = e.getX();
		double y = e.getY();
		
		canvas.draw();
		
		canvas.getGC().strokeLine(x - MarkerSize, y - MarkerSize, x + MarkerSize, y + MarkerSize);
		canvas.getGC().strokeLine(x + MarkerSize, y - MarkerSize, x - MarkerSize, y + MarkerSize);
		canvas.reset(new CrisscrossMarkerItem(x, y));
	}

}