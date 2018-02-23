package control;

import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Doctor;
import model.FileManager;
import view.DoctorFr;

public class DoctorCtrl {

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
		JTextField nombre = new JTextField();
		JTextField lastname = new JTextField();
		JTextField id = new JTextField();
		JTextField ssn = new JTextField();
		JTextField municipality = new JTextField();
		JTextField address = new JTextField();
		Object[] genders = {"Male", "Female"};
		JComboBox boxgenders = new JComboBox(genders);
		Object[] status = {"Low", "Mild", "Moderate", "Critical"};
		JComboBox boxstatus = new JComboBox(status);
		JTextField message = new JTextField();
		
		Object[] inputFields = {"Name:", nombre,
								"Last Name:", lastname,
								"ID:", id,
								"SSN:", ssn,
								"Municipality:", municipality,
								"Address:", address, 
								"Gender: ", boxgenders,
								"Status: ", boxstatus,
								"Message: ", message};
		
		JOptionPane.showConfirmDialog(df, inputFields, "New Patient", JOptionPane.OK_CANCEL_OPTION);
		
	}

}
	

