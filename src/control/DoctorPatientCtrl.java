package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import model.Doctor;
import model.Patient;
import view.DoctorPatientFr;
import view.EcgPanel;
import view.NewMessageDialog;
import view.PatientPanel;

public class DoctorPatientCtrl extends ReturnsToFrame implements ActionListener, MouseListener {
	
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
			/*df.setVisible(false);
			DoctorPatientFr dpf = new DoctorPatientFr();
			DoctorPatientCtrl dpc = new DoctorPatientCtrl(dpf,doctor,p.getPatient());
	        dpc.setPreviousWindow(df);
	        dpf.addController(dpc);
			dpf.initialize();
		    dpf.setVisible(true);*/
			System.out.println(ecg.getECG().getName());
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorPatientCtrl");
		}/* catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
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

}
