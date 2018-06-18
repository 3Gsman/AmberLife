package control.assistant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import control.MainCtrl;
import model.DBManagement;
import model.ECG;
import view.admin.AdminFr;
import view.assistant.AssistMeasureFr;

public class AssistMeasureCtrl implements ActionListener{
	
	private AssistMeasureFr fr;
	private ECG ecg;
	private String assistID;
	private String patientID;
	
	/**
	 * Class constructor, sets the related frame and the ECG measure that will be displayed on it.
	 *
	 * @param  thiscatwontshutthehellup		Related frame and mention to my neighbour's cat.
	 * @param  e	ECG to be displayed in the related frame	
	 * @see    AssistMeasureFr, ECG
	 */
	public AssistMeasureCtrl(AssistMeasureFr thiscatwontshutthehellup, ECG e, String assistID, String patientID) {
		this.fr = thiscatwontshutthehellup;
		this.ecg = e;
		this.assistID = assistID;
		this.patientID = patientID;
	}
	
	/**
	 * Returns the ECG that the related frame will represent.
	 * 
	 * @returns		returns the ECG represented in the AssistMeasureFr
	 * @see         ECG
	 */
	public ECG getECG() {
		return ecg;
	}
	
	public String getAssistID() {
		return assistID;
	}
	
	public String getPatientID() {
		return patientID;
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
			 MainCtrl.popBackStack();
		 }
		 else if (e.getActionCommand().equals("CONFIRM")){
			 //Saving Measures not implemented yet.
			 System.out.println(" CONFIRMED");
			 
			 try {
				DBManagement.confirmECG(ecg, assistID, patientID);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 MainCtrl.popBackStack(); 
		 }
		 else System.out.println("Invalid Action");
	}
	
}
