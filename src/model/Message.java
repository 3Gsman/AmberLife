package model;

public class Message {
	
	private double timestamp;
	private String message;
	private String authorname;
	private String authorsurname;
	private String authorID;
	private String authorSSN;
	
	public Message(double timestamp, String message, String authorname, String authorsurname, String authorID, String authorSSN) {
		this.timestamp = timestamp;
		this.message = message;
		this.authorname = authorname;
		this.authorsurname = authorsurname;
		this.authorID = authorID;	
		this.authorSSN = authorSSN;	
	}
	
	public double getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthorID() {
		return authorID;
	}
	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public String getAuthorsurname() {
		return authorsurname;
	}
	public void setAuthorsurname(String authorsurname) {
		this.authorsurname = authorsurname;
	}

}
