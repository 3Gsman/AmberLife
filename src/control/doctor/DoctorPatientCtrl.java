package control.doctor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import control.MainCtrl;
import model.Doctor;
import model.ECG;
import model.Patient;
import view.assistant.AssistMeasureFr;
import view.dialogs.AssistDialog;
import view.dialogs.ExitDialog;
import view.dialogs.NewMessageDialog;
import view.doctor.DoctorMeasureFr;
import view.doctor.DoctorPatientFr;
import view.panels.CompareGraphPanel;
import view.panels.EcgPanel;
import view.panels.MessagePanel;
import view.panels.PatientPanel;

public class DoctorPatientCtrl implements ActionListener, MouseListener {
	
	private Patient p;
	private Doctor d;
	private DoctorPatientFr frame;
	
	/**
	 * Class constructor, sets the related frame.
	 *
	 * @param  frame		Related frame DoctorPatientFr
	 * @see    DoctorPatientFr
	 */
	public DoctorPatientCtrl(DoctorPatientFr frame){
		this.frame = frame;
	}
	
	/**
	 * Class constructor, sets the related frame, the user object representing the current user, and the displayed Patient.
	 *
	 * @param  frame	Related frame DoctorPatientFr
	 * @param  d		Doctor object representing the user
	 * @param  p		Patient object containing data of the displayed patient
	 * @see    DoctorPatientFr, Doctor, Patient
	 */
	public DoctorPatientCtrl(DoctorPatientFr frame, Doctor d, Patient p){
		this.frame = frame;
		this.d = d;
		this.p = p;
		
	}
	
	/**
	 * Returns a Doctor object representing the user
	 * 
	 * @return the user object
	 * @see Doctor
	 */
	public Doctor getDoctor() {
		return d;
	}
	
	/**
	 * Returns a Patient object representing the displayed patient
	 * 
	 * @return the displayed patient object
	 * @see Patient
	 */
	public Patient getPatient() {
		return p;
	}
	
	/**
	 * Opens a new DoctorMeasureFr displaying the ECG from the EcgPanel clicked.
	 * 
	 * @param e		The event triggering the method. Only ECGPanels are meant to be listened to.
	 * @see EcgPanel, DoctorMeasureFr, DoctorMeasureCtrl
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			EcgPanel ecg = (EcgPanel) e.getSource();
			DoctorMeasureFr dmf = new DoctorMeasureFr();
			DoctorMeasureCtrl dmc = new DoctorMeasureCtrl(dmf,ecg.getECG());
			MainCtrl.window.toBackStack(frame);
			frame.setVisible(false);
			dmf.addController(dmc);
			dmf.initialize();
			dmf.setVisible(true);
			System.out.println("Selected ECG: " + ecg.getECG().getName());
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorPatientCtrl");
		} catch (IOException e1) {
			e1.printStackTrace();
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
	
	/**
	 * Listens to event commands emitted by DoctorPatientFr, and reacts to them accordingly:
	 * 
	 * BACK:		Returns to the previous frame.
	 * ECGS:		Changes the display mode to display the ECGs, if not already set that way.
	 * MESSAGES:	Changes the display mode to display messages related to the patient, if not already set that way.
	 * REPLY:		Open a NewMessageDialog as a reply to the message related to the triggering button. (Placeholder only)
	 * NEWMESSAGE:	Open a NewMessageDialog for creating new messages. (Placeholder only)
	 * 
	 * @param  e event triggering the action performed
	 * @see         DoctorPatientFr, FileChooser
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action received: " + e.getActionCommand());
		if (e.getActionCommand().equals("BACK")){ 
			MainCtrl.window.popBackStack();
		}
		else if (e.getActionCommand().equals("ECGS")){ 
			try {
				frame.setModeECG();
				frame.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else  if (e.getActionCommand().equals("REPLY")){ 	
			 try {
				 MessagePanel mp = (MessagePanel) ((Component) e.getSource()).getParent();
				 NewMessageDialog nmd = new NewMessageDialog(p.getName(), p.getLastname(),
							"From: " + mp.getUser().getName() + " " + mp.getUser().getLastname() + " on " + mp.getDate() + "\n"
							 + "RE: " + mp.getMessage() + "\n") ;
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}else if (e.getActionCommand().equals("NEWMESSAGE")){ 
			System.out.println("New Message");
			try {
				NewMessageDialog nmd = new NewMessageDialog(p.getName(),p.getLastname(),"");
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}else if (e.getActionCommand().equals("MESSAGES")){ 
			try {
				frame.setModeMessages();
				frame.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else System.out.println("Invalid Action");
		
	}

	/**
	 * Takes two ECGs and creates a new DoctorMeasureFr, passing a special panel CompareGraphPanel as an argument to
	 * display both ECGs as a comparison instead of just one.
	 * 
	 * @param  first	First ECG to compare
	 * @param  second	Second ECG to compare
	 * @see    CompareGraphPanel, DoctorMeasureFr, DoctorMeasureCtrl
	 */
	public void compare(ECG first, ECG second) {
		try {
			CompareGraphPanel cgp = new CompareGraphPanel(first, second);
			DoctorMeasureFr dmf = new DoctorMeasureFr();
			DoctorMeasureCtrl dmc = new DoctorMeasureCtrl(dmf,cgp);
			MainCtrl.window.toBackStack(frame);
			frame.setVisible(false);
			dmf.addController(dmc);
			dmf.initialize();
			MainCtrl.window.setContentPane(dmf);
			MainCtrl.window.setVisible(true);
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorPatientCtrl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


}
