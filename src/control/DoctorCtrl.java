package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Doctor;
import model.FileManager;
import view.DoctorFr;
import view.PatientDialog;
import view.PatientPanel;

public class DoctorCtrl extends ReturnsToFrame implements ActionListener, MouseListener{

	String name;
	DoctorFr df;
	Doctor doctor;
	
	public DoctorCtrl(String user, DoctorFr vd) throws IOException {
		FileManager file = new FileManager();
		
		df = vd;
		name = user;
		doctor = file.readDoctor(name);
		
		int i = 0;
		FileManager getpatients = new FileManager();
		for(i = 0; i < doctor.getPatientlist().size(); i ++) {
			
			doctor.getPatientlist().set(i,getpatients.readPatient(doctor.getPatientlist().get(i).getNumber()));

		}
	}
	
	public void registerPatient() {
		//Pasar a MCV
		PatientDialog pd = new PatientDialog(df);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("BACK")){ 
			returnToPrevious();
			df.dispose();
		}
		else if (e.getActionCommand().equals("NEW")) {
			registerPatient();
		}
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	@Override
	//ONLY PATIENTPANELS ARE MEANT TO BE LISTENED TO.
	public void mouseClicked(MouseEvent e) {
		PatientPanel p = (PatientPanel) e.getSource();
		System.out.println(p.getPatient().getName());
		
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

}
	

