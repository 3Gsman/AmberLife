package model;

public class Doctor {
	String name;
	String lastname;
	String id;
	String number;
	String hospital;
	String phone;
	Patient[] patientlist;
	
	public Doctor(String n, String ln, String dni, String num, String h, String p) {
		setName(n);
		setLastname(ln);
		setId(dni);
		setNumber(num);
		setHospital(h);
		setPhone(p);
		setPatientlist(null);		
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Patient[] getPatientlist() {
		return patientlist;
	}
	public void setPatientlist(Patient[] listapacientes) {
		this.patientlist = listapacientes;
	}
	
	
}
