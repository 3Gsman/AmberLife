package control;

import java.awt.Component;
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
import model.Patient;
import view.AssistFr;
import view.DoctorFr;
import view.DoctorPatientFr;
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
		}else if(e.getActionCommand().equals("SEARCH")) {
			try {
				searchPatient();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	@Override
	//ONLY PATIENTPANELS ARE MEANT TO BE LISTENED TO.
	public void mouseClicked(MouseEvent e) {
		try {
			PatientPanel p = (PatientPanel) e.getSource();
			df.setVisible(false);
			DoctorPatientFr dpf = new DoctorPatientFr();
			DoctorPatientCtrl dpc = new DoctorPatientCtrl(dpf,doctor,p.getPatient());
	        dpc.setPreviousWindow(df);
	        dpf.addController(dpc);
			dpf.initialize();
		    dpf.setVisible(true);
		}
		catch(ClassCastException cce){
			System.out.println("BAD CAST at DoctorCtrl");
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
	
	public void searchPatient() throws IOException {
		String dni = df.getID();
		FileManager id = new FileManager();
		boolean found= false;
		
		Patient resultado = id.checkId(dni);
		Patient p = null;
		
		for(int i = 0; i<doctor.getPatientlist().size(); i++) {
			if(resultado.getId().equals(doctor.getPatientlist().get(i).getId())) {
				found = true;
				p = doctor.getPatientlist().get(i);
				
			}
		}
		resultado = p;
		
		if(found == true) {
			System.out.println("Patient found.\n");		
			
			df.setVisible(false);
			DoctorPatientFr dpf = new DoctorPatientFr();
			DoctorPatientCtrl dpc = new DoctorPatientCtrl(dpf,doctor, resultado);
	        dpc.setPreviousWindow(df);
	        dpf.addController(dpc);
			dpf.initialize();
		    dpf.setVisible(true);
			
		}else {
			Object frame = null;	//crea un objeto ventana
            JOptionPane.showMessageDialog((Component) frame, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de di�logo para alertar de un error

		}
	}

}
	

