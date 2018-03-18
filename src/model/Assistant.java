package model;


import java.io.IOException;

public class Assistant extends User {
	
	private String city;
	private String username;
	
	/**
	 * Class constructor. Creates an Assistant object from the given parameters.
	 * 
	 * @param n		name of the assistant
	 * @param ln	last name of the assistant
	 * @param dni	id of the assistant
	 * @param c		city of the assistant
	 * @param un	username of the assistant.
	 */
	public Assistant(String n, String ln, String dni, String c, String un) {
		name = n;
		lastname = ln;
		id = dni;
		city = c;
		username = un;
	}
	
	/**
	 * Class constructor. Reads the parameters from the DB (currently a text file as a placeholder) given the username.
	 * 
	 * @param un	username to be read from the DB
	 * @throws IOException
	 */
	public Assistant(String un) throws IOException {
		FileManagement t = new FileManagement();
		String t2[] = t.readAssistant(un);
		name = t2[0];
		lastname = t2[1];
		id = t2[2];
		city = t2[3];
		username = t2[4];
		
	}

	/**
	 * Getter for the city attribute.
	 * 
	 * @return the city field of the Assistant
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Getter for the username attribute.
	 * 
	 * @return the username field of the Assistant.
	 */
	public String getUsername() {
		return username;
	}
	
	
}
