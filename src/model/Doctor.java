package model;

import java.util.Vector;

public class Doctor extends User {
	String number;
	String hospital;
	String phone;
	Vector<Patient> patientlist;
	
	public Doctor(String n, String ln, String dni, String num, String h, String p) {	
		super.setName(n);
		super.setLastname(ln);
		super.setId(dni);
		setNumber(num);
		setHospital(h);
		setPhone(p);
		setPatientlist(null);		
	}
	
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Vector<Patient> getPatientlist() {
		return patientlist;
	}
	public void setPatientlist(Vector<Patient> listapacientes) {
		this.patientlist = listapacientes;
	}
	
	
}
