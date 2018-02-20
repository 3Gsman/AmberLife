package model;

public class Patient {
	
	
	String number;
	String name;
	String lastname;
	String id;
	String ssn;
	String municipality;
	String address;
	ECG[] ecgs;
	
	public Patient(String num) {
		setNumber(num);
	}
	
	public Patient(String num, String n, String ln, String dni) {
		setNumber(num);
		setName(n);
		setLastname(ln);
		setId(dni);
	}
	
	
	public ECG[] getECGs() {
		return ecgs;
	}
	
	public void setECGs(ECG[] ecgs) {
		this.ecgs = ecgs;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
