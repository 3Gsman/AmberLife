package control;

import javax.swing.JFrame;

public class ReturnsToFrame {

	protected JFrame previousWindow;
	
	/** Makes a JFrame previously made invisible visible again, "returning" to it.
	 * 
	 * @see JFrame.
	 */
	public void returnToPrevious() {
		previousWindow.setVisible(true);
	}
	
	/** Sets a window preceding the child class, so that they can "return" to a previous window previously hidden.
	 * 
	 * @param	prev	The previous frame that will be return to.
	 * @see JFrame.
	 */
	public void setPreviousWindow(JFrame prev) {
		this.previousWindow = prev;
	}
	
	/** Returns the frame saved as previous.
	 * 
	 * @returns	the window set as previous.
	 * @see JFrame.
	 */
	public JFrame getPreviousWindow() {
		return previousWindow;
	}
}
