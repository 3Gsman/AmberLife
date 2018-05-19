package model;


import java.io.IOException;
import java.sql.SQLException;

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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Assistant(String un) throws IOException, ClassNotFoundException, SQLException {
		Assistant ass = DBManagement.readAssistant(un);
		name = ass.name;
		lastname = ass.lastname;
		id = ass.id;
		city = ass.city;
		username = ass.username;
		
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
