package control.assistant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.MainCtrl;
import model.ECG;
import view.admin.AdminFr;
import view.assistant.AssistMeasureFr;

public class AssistMeasureCtrl implements ActionListener{
	
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
			 MainCtrl.window.popBackStack();
		 }
		 else if (e.getActionCommand().equals("CONFIRM")){
			 //Saving Measures not implemented yet.
			 System.out.println(" UNIMPLEMENTED");
			 MainCtrl.window.popBackStack(); 
		 }
		 else System.out.println("Invalid Action");
	}
	
}
