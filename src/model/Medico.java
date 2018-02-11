package model;

public class Medico {
	String name;
	String lastname;
	String id;
	String number;
	String hospital;
	String phone;
	/*Paciente[]*/
	String[] listapacientes;
	
	public Medico(String n, String ln, String dni, String num, String h, String p) {
		setName(n);
		setLastname(ln);
		setId(dni);
		setNumber(num);
		setHospital(h);
		setPhone(p);
		setListapacientes(null);		
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
	public String[] getListapacientes() {
		return listapacientes;
	}
	public void setListapacientes(String[] listapacientes) {
		this.listapacientes = listapacientes;
	}
	
	
}
