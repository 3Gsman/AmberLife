package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assistant {
	String name;
	String lastname;
	String id;
	String city;
	String username;
	
	public Assistant(String n, String ln, String dni, String c, String un) {
		name = n;
		lastname = ln;
		id = dni;
		city = c;
		username = un;
	}
	
	public Assistant(String un) throws IOException {
		FIleManager t = new FIleManager();
		String t2[] = t.leerTecnico(un);
		name = t2[0];
		lastname = t2[1];
		id = t2[2];
		city = t2[3];
		username = t2[4];
		
	}
	

	
	public String getName() {
		return name;
	}
	public String getLastName() {
		return lastname;
	}
	public String getId() {
		return id;
	}
	public String getCity() {
		return city;
	}
	public String getUsername() {
		return username;
	}
	
}
