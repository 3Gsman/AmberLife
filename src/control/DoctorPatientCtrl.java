package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Doctor;
import model.Patient;
import view.DoctorPatientFr;

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
		// TODO Auto-generated method stub
		
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
		
	}

}
