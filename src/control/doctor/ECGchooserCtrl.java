package control.doctor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import control.ReturnsToFrame;
import model.ECG;
import view.dialogs.AssistDialog;
import view.doctor.ECGchooserFr;
import view.panels.EcgPanel;

public class ECGchooserCtrl extends ReturnsToFrame implements ActionListener, MouseListener, WindowListener{
	
	private ECGchooserFr frame;
	private ECG first;
	private DoctorPatientCtrl controller;
	
	/**
	 * Class constructor, sets the related frame, the controller that will receive the ECGs, and the first ECG.
	 * 
	 * @param  c		Controller that will receive both ECGs
	 * @param  f		Related frame DoctorPatientFr
	 * @param  first	First ECG to compare
	 * @see    DoctorPatientCtrl, ECGchooserFR, ECG
	 */
	public ECGchooserCtrl(DoctorPatientCtrl c ,ECGchooserFr f, ECG first){
		frame = f;
		controller = c;
		this.first = first;
	}

	/**
	 * Returns the DoctorPatientCtrl associated with the class.
	 * 
	 * @return	the controller to receive the ECGs
	 * @see    DoctorPatientCtrl, ECGchooserFR, ECG
	 */
	public DoctorPatientCtrl getController() {
		return controller;
	}
	
	/**
	 * Takes the clicked ECGPanel as a second ECG, and calls the compare function of the controller passing
	 * both ECGs as parameters
	 * 
	 * @param e		The event triggering the method. Only ECGPanels are meant to be listened to.
	 * @see EcgPanel
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			EcgPanel ecg = (EcgPanel) e.getSource();
			controller.compare(first,ecg.getECG());
			frame.dispose();
			//this.returnToPrevious();
			System.out.println("Selected ECG: " + ecg.getECG().getName());
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorPatientCtrl");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Closes the current frame and returns to previous.
	 *  
	 * @param	e	WindowEvent triggering the method, in this case, the window closing.
	 * @see         AssistDialog
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		returnToPrevious();
		frame.dispose();	
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
		
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
