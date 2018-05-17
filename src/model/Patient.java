package model;

import java.util.Vector;

public class Patient {
	
	private String name;
	private String lastname;
	private String id;
	private String ssn;
	private String municipality;
	private String address;
	private String gender;
	private String status;
	private Vector<ECG> ecgs;
	
	public Patient(String num) {
		setSsn(num);
	}
	
	/**
	 * Class constructor. Sets the given parameters.
	 * 
	 * @param num	the ssn of the patient
	 * @param n		the name of the patient
	 * @param ln	the last name of the patient
	 * @param dni	the id of the patient
	 */
	public Patient(String num, String n, String ln, String dni) {
		setSsn(num);
		setName(n);
		setLastname(ln);
		setId(dni);
	}
	
	
	/**
	 * Getter for the ecgs attribute.
	 * 
	 * @return the list of the patient's ECGs
	 */
	public Vector<ECG> getECGs() {
		return ecgs;
	}
	/**
	 * Setter for the ecgs attribute.
	 * 
	 * @param ecgs		the list of the patient's ECGs
	 */
	public void setECGs(Vector<ECG> ecgs) {
		this.ecgs = ecgs;
	}
	/**
	 * Getter for the name attribute.
	 * 
	 * @return the name of the patient
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter for the name attribute.
	 * 
	 * @param name		the name of the patient
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter for the lastname attribute.
	 * 
	 * @return the lastname of the patient
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * Setter for the lastname attribute.
	 * 
	 * @param lastname		the lastname of the patient
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * Getter for the id attribute.
	 * 
	 * @return the id of the patient
	 */
	public String getId() {
		return id;
	}
	/**
	 * Setter for the id attribute.
	 * 
	 * @param id		the id of the patient
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Getter for the ssn attribute.
	 * 
	 * @return the ssn of the patient
	 */
	
	public String getSsn() {
		return ssn;
	}
	
	/**
	 * Setter for the ssn attribute.
	 * 
	 * @param ssn		the ssn of the patient
	 */
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	/**
	 * Getter for the municipality attribute.
	 * 
	 * @return the municipality of the patient
	 */
	public String getMunicipality() {
		return municipality;
	}
	/**
	 * Setter for the municipality attribute.
	 * 
	 * @param municipality		the municipality of the patient
	 */
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	/**
	 * Getter for the address attribute.
	 * 
	 * @return the address of the patient
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Setter for the address attribute.
	 * 
	 * @param address		the address of the patient
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Getter for the gender attribute.
	 * 
	 * @return the gender of the patient
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * Setter for the gender attribute.
	 * 
	 * @param gender		the gender of the patient
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * Getter for the status attribute.
	 * 
	 * @return the status of the patient
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Setter for the status attribute.
	 * 
	 * @param status		the status of the patient
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
