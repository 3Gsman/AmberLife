package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import model.Doctor;
import model.Patient;
import view.AssistMeasureFr;
import view.DoctorMeasureFr;
import view.DoctorPatientFr;
import view.EcgPanel;
import view.ExitDialog;
import view.NewMessageDialog;
import view.PatientPanel;

public class DoctorPatientCtrl extends ReturnsToFrame implements ActionListener, MouseListener, WindowListener {
	
	Patient p;
	Doctor d;
	DoctorPatientFr frame;
	
	
	
	public Doctor getDoctor() {
		return d;
	}
	
	public Patient getPatient() {
		return p;
	}
	
	public DoctorPatientCtrl(DoctorPatientFr frame){
		this.frame = frame;
	}
	
	public DoctorPatientCtrl(DoctorPatientFr frame, Doctor d, Patient p){
		this.frame = frame;
		this.d = d;
		this.p = p;
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			EcgPanel ecg = (EcgPanel) e.getSource();
			DoctorMeasureFr dmf = new DoctorMeasureFr();
			DoctorMeasureCtrl dmc = new DoctorMeasureCtrl(dmf,ecg.getECG());
			dmc.setPreviousWindow(frame);
			frame.setVisible(false);
			dmf.addController(dmc);
			dmf.initialize();
			dmf.setVisible(true);
			System.out.println("Selected ECG: " + ecg.getECG().getName());
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorPatientCtrl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("BACK")){ 
			returnToPrevious();
			frame.dispose();
		}
		else if (e.getActionCommand().equals("ECGS")){ 
			System.out.println("ECGS");
			frame.initializeECG();
			frame.setVisible(true);
		}else if (e.getActionCommand().equals("NEWMESSAGE")){ 
			System.out.println("New Message");
			NewMessage();
		}
		
		else if (e.getActionCommand().equals("MESSAGES")){ 
			try {
				System.out.println("MESSAGES");
				frame.initializeMessages();
				frame.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void NewMessage() {
		NewMessageDialog nmd = new NewMessageDialog(frame, p);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

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
