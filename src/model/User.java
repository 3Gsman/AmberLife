package model;

public class User {
	protected String name;
	protected String lastname;
	protected String id;

	/**
	 * Class constructor. Sets the user's id, name, and last name.
	 * 
	 * @param id		id of the user
	 * @param name		name of the user
	 * @param lastname	last name of the user
	 */
	public User(String id, String name, String lastname) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
	}
	
	/**
	 * Empty constructor for the class.
	 */
	public User() {
		
	}
	/**
	 * Getter for the name attribute.
	 * 
	 * @return the user's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter for the name attribute.
	 * 
	 * @param name		the user's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter for the lastname attribute.
	 * 
	 * @return the user's last name
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * Setter for the lastname attribute.
	 * 
	 * @param lastname		the user's last name
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * Getter for the id attribute.
	 * 
	 * @return the user's id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Setter for the id attribute.
	 * 
	 * @param id		the user's id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
