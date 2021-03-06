package control.doctor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import control.MainCtrl;
import model.DBManagement;
import model.ECG;
import view.panels.CompareGraphPanel;
import view.dialogs.EditDiagnosisDialog;
import view.dialogs.ExitDialog;
import view.dialogs.NewMessageDialog;
import view.doctor.DoctorMeasureFr;
import view.doctor.DoctorPatientFr;
import view.doctor.ECGchooserFr;

public class DoctorMeasureCtrl implements ActionListener{
	
	private DoctorMeasureFr fr;
	private ECG e;
	private CompareGraphPanel cgp;
	
	/**
	 * Class constructor, sets the related frame and the ECG measure that will be displayed on it.
	 *
	 * @param  thiscatwontshutthehellup		Related frame and mention to my neighbour's cat.
	 * @param  e	ECG to be displayed in the related frame	
	 * @see    DoctorMeasureFr, ECG
	 */
	public DoctorMeasureCtrl(DoctorMeasureFr thiscatwontshutthehellup, ECG e) {
		this.fr = thiscatwontshutthehellup;
		this.e = e;
	}
	
	public DoctorMeasureCtrl(DoctorMeasureFr thiscatwontshutthehellup, CompareGraphPanel cgp) {
		this.fr = thiscatwontshutthehellup;
		this.cgp = cgp;
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
	 * Returns a CompareGraphPanel owned by the controller.
	 * 
	 * @returns		the CompareGraphPanel owned by the controller, null if no comparison is being performed.
	 * @see         CompareGraphPanel
	 */
	public CompareGraphPanel getCGP() {
		return cgp;
	}

	/**
	 * Listens to event commands emitted by DoctorMeasureFr, and reacts to them accordingly:
	 * 
	 * BACK:		Returns to the previous frame.
	 * COMPARE:		Closes the current frame and opens a ECGChooserFr to choose an ECH for comparison.
	 *
	 * @param  e event triggering the action performed
	 * @see         DoctorMeasureFr, ECGchooserFr, ECGchooserCtrl
	 */
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Action received: " + ev.getActionCommand());
		 if (ev.getActionCommand().equals("BACK")){
			 MainCtrl.popBackStack();
		 }
		 else if (ev.getActionCommand().equals("COMPARE")){
			 try {
			 	DoctorPatientCtrl dpc = ((DoctorPatientFr)MainCtrl.getMainFrame().backstack.peek()).getController();
			 	ECGchooserFr ecgf = new ECGchooserFr(getClass().getResource("/resources/BG.png"));
			 	ECGchooserCtrl ecgc = new ECGchooserCtrl(dpc, ecgf, e);;
			 	ecgf.addController(ecgc);
			 	ecgf.initialize();
			 	MainCtrl.setPanel(ecgf);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 }
		 else if (ev.getActionCommand().equals("EDIT_DIAGNOSIS")){
			 System.out.println("Edit diagnosis button");
			 try {
				EditDiagnosisDialog nmd = new EditDiagnosisDialog(MainCtrl.getMainFrame(),this,e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 }
		 else if (ev.getActionCommand().equals("DIAGNOSIS_UPDATE")){
			System.out.println("Diagnosis updated");		
			fr.updateDiagnostic(e.getReport());
	
		 }
		 else System.out.println("Invalid Action");
	}

	
}
