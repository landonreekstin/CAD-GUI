package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

import csci240.prinCad.control.CadTool;
import csci240.prinCad.model.ModelManager;
import javafx.scene.Scene;

public interface CanvasCommandInterface {
	public void setActiveTool(CadTool activeTool);
	public void setIsModelFile(boolean isModelFile);
	public void draw();
	public void clear();
	public void delete();
	public void undo();
	public void emptyState();
	public Scene getScene();
	public void loadFromFile(BufferedReader reader);
	public void passFileName(File file);
	public void saveToFile(PrintWriter out);
	public boolean getIsModelFile();
	public String getCurrentFile();
	public void toggleSelection();
	public ModelManager getModel();
}
