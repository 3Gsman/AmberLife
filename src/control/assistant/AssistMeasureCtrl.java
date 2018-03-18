package control.assistant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import control.ReturnsToFrame;
import model.ECG;
import view.admin.AdminFr;
import view.assistant.AssistMeasureFr;
import view.dialogs.ExitDialog;

public class AssistMeasureCtrl extends ReturnsToFrame implements ActionListener, WindowListener{
	
	private AssistMeasureFr fr;
	private ECG e;
	
	/**
	 * Class constructor, sets the related frame and the ECG measure that will be displayed on it.
	 *
	 * @param  thiscatwontshutthehellup		Related frame and mention to my neighbour's cat.
	 * @param  e	ECG to be displayed in the related frame	
	 * @see    AssistMeasureFr, ECG
	 */
	public AssistMeasureCtrl(AssistMeasureFr thiscatwontshutthehellup, ECG e) {
		this.fr = thiscatwontshutthehellup;
		this.e = e;
	}
	
	/**
	 * Returns the ECG that the related frame will represent.
	 * 
	 * @returns		returns the ECG represented in the AssistMeasureFr
	 * @see         ECG
	 */
	public ECG getECG() {
		return e;
	}

	/**
	 * Listens to event commands emitted by AssistMeasureFr, and reacts to them accordingly:
	 * 
	 * CANCEL:      Return to the previous frame
	 * CONFIRM:		Upload the new ECG Measure to the DB (Unimplemented)
	 * 
	 * @param  e event triggering the action performed
	 * @see         AdminFr
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: ");
		 if (e.getActionCommand().equals("CANCEL")){
			 System.out.println(" Cancel");
			 returnToPrevious();
			 fr.dispose();
		 }
		 else if (e.getActionCommand().equals("CONFIRM")){
			 //Saving Measures not implemented yet.
			 System.out.println(" UNIMPLEMENTED");
			 returnToPrevious();
			 fr.dispose();	 
		 }
		 else System.out.println("Invalid Action");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Asks for a confirmation before the window is closed.
	 * 
	 * @param	e	WindowEvent triggering the method, in this case, the window closing.
	 * @see         ExitDialog
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		ExitDialog.confirmExit();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
