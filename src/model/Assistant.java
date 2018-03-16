package model;


import java.io.IOException;

public class Assistant extends User {
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
		FileManagement t = new FileManagement();
		String t2[] = t.readAssistant(un);
		name = t2[0];
		lastname = t2[1];
		id = t2[2];
		city = t2[3];
		username = t2[4];
		
	}

	public String getCity() {
		return city;
	}
	public String getUsername() {
		return username;
	}
	
}
