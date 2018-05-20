package model;

public class Message {
	
	private String timestamp;
	private String message;
	private String authorname;
	private String authorsurname;
	private String authorID;
	private String authorSSN;
	private String patientID;
	
	public Message(String timestamp, String message, String authorname, String authorsurname,
					String authorID, String authorSSN, String patientID) {
		this.timestamp = timestamp;
		this.message = message;
		this.authorname = authorname;
		this.authorsurname = authorsurname;
		this.authorID = authorID;	
		this.patientID = patientID;
		this.setAuthorSSN(authorSSN);	
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
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

	public String getAuthorSSN() {
		return authorSSN;
	}

	public void setAuthorSSN(String authorSSN) {
		this.authorSSN = authorSSN;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

}
