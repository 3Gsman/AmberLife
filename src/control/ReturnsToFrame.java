package control;

import javax.swing.JFrame;

public class ReturnsToFrame {

	JFrame previousWindow;
	
	public void returnToPrevious() {
		previousWindow.setVisible(true);
	}
	
	public void setPreviousWindow(JFrame prev) {
		this.previousWindow = prev;
	}
	public JFrame getPreviousWindow() {
		return previousWindow;
	}
}
