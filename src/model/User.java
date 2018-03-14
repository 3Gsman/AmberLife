package model;

public class User {
	String name;
	String lastname;
	String id;

	
	public User(String id, String name, String lastname) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
	}
	
	public User() {
		
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
}
