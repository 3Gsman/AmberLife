package control.doctor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;
import control.ReturnsToFrame;
import model.ECG;
import view.panels.CompareGraphPanel;
import view.dialogs.ExitDialog;
import view.doctor.DoctorPatientFr;
import view.doctor.ECGchooserFr;

public class DoctorMeasureCtrl extends ReturnsToFrame implements ActionListener, WindowListener{
	
	private JFrame fr;
	private ECG e;
	private CompareGraphPanel cgp;
	
	/**
	 * Class constructor, sets the related frame and the ECG measure that will be displayed on it.
	 *
	 * @param  thiscatwontshutthehellup		Related frame and mention to my neighbour's cat.
	 * @param  e	ECG to be displayed in the related frame	
	 * @see    DoctorMeasureFr, ECG
	 */
	public DoctorMeasureCtrl(JFrame thiscatwontshutthehellup, ECG e) {
		this.fr = thiscatwontshutthehellup;
		this.e = e;
	}
	
	public DoctorMeasureCtrl(JFrame thiscatwontshutthehellup, CompareGraphPanel cgp) {
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
			 returnToPrevious();
			 fr.dispose();
		 }
		 else if (ev.getActionCommand().equals("COMPARE")){
			 try {
			 	DoctorPatientCtrl dpc = ((DoctorPatientFr)getPreviousWindow()).getController();
			 	ECGchooserFr ecgf = new ECGchooserFr();
			 	ECGchooserCtrl ecgc = new ECGchooserCtrl(dpc, ecgf, e);
			 	ecgc.setPreviousWindow(getPreviousWindow());
			 	ecgf.addController(ecgc);
			 	ecgf.initialize();
			 	ecgf.setVisible(true);
			 	
			 	fr.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
