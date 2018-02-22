package control;

import java.io.IOException;

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
	
}
