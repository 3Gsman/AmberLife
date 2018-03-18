package model;

import java.util.Vector;

public class Doctor extends User {
	
	private String number;
	private String hospital;
	private String phone;
	private Vector<Patient> patientlist;
	
	/**
	 * Creates a doctor object from the parameters given.
	 * 
	 * @param n		name of the doctor
	 * @param ln	last name of the doctor
	 * @param dni	id of the doctor
	 * @param num	ssn of the doctor
	 * @param h		hospital where the doctor works
	 * @param p		list of the doctor's patients
	 */
	public Doctor(String n, String ln, String dni, String num, String h, String p) {	
		super.setName(n);
		super.setLastname(ln);
		super.setId(dni);
		setNumber(num);
		setHospital(h);
		setPhone(p);
		setPatientlist(null);		
	}
	
	/**
	 * Getter for the ssn attribute.
	 * 
	 * @return the ssn field of the Doctor
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Setter for the ssn attribute.
	 * 
	 * @param number	the ssn of the doctor
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * Getter for the hospital attribute.
	 * 
	 * @return the hospital field of the Doctor
	 */
	public String getHospital() {
		return hospital;
	}
	
	/**
	 * Setter for the hospital attribute.
	 * 
	 * @param hospital		the hospital where the doctor works
	 */
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	/**
	 * Getter for the phone attribute.
	 * 
	 * @return the phone field of the Doctor
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Setter for the phone attribute.
	 * 
	 * @param phone		the phone where the doctor works
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Getter for the patientlist attribute.
	 * 
	 * @return the list of the doctor's patients
	 */
	public Vector<Patient> getPatientlist() {
		return patientlist;
	}
	
	/**
	 * Setter for the patientlist attribute.
	 * 
	 * @param patientlist		the list of the doctor's patients registered in the system
	 */
	public void setPatientlist(Vector<Patient> patientlist) {
		this.patientlist = patientlist;
	}
	
	
}
