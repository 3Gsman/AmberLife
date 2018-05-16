package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DBManagement {

	public String[] checkUser(String usuario, String Password) throws ClassNotFoundException, SQLException {
		String iduser;
		String user[] = new String[2];
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select IDUser from User where username='" + usuario + "' and password='" + Password + "'");

		if (rs.next() == false) {
			user[0] = "false";
		} else {
			user[0] = "true";

			iduser = rs.getString("IDUser");

			rs = stmt.executeQuery("select IDUser from clinical where iduser='" + iduser + "'");

			if (rs.next() == false) {
				user[1] = "admin";
			} else {

				rs = stmt.executeQuery("select IDUser from doctor where iduser='" + iduser + "'");

				if (rs.next() == false) {
					user[1] = "tecnico";
				} else {
					user[1] = "medico";
				}
			}
		}

		rs.close();
		stmt.close();
		c.close();

		return user;
	}

	public Vector<Assistant> getAssistants() throws ClassNotFoundException, SQLException {
		Vector<Assistant> v = new Vector<>();
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT Assistant.IDuser, assistant.Municipality, user.Username, user.Name, user.LastName\r\n"
						+ "from Assistant\r\n" + "join User\r\n" + "on assistant.iduser = user.iduser");

		while (rs.next()) {
			v.add(new Assistant(rs.getString("Name"), rs.getString("LastName"), rs.getString("IDUser"),
					rs.getString("Municipality"), rs.getString("Username")));

		}

		rs.close();
		stmt.close();
		c.close();

		return v;

	}
	
	public static Vector<Message> readMessages(String patientID) throws ClassNotFoundException, SQLException {
		Vector<Message> messages = new Vector<>();
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs_message = stmt.executeQuery("SELECT * FROM Message WHERE IDptt LIKE " + patientID);
		
		while (rs_message.next()) {
			ResultSet rs_author = stmt.executeQuery("SELECT User.IDuser, User.Name, User.LastName, Clinical.SSN "
					+ "FROM	User, Clinical WHERE IDUser LIKE" + rs_message.getString("IDuser"));
			Message m = new Message(Double.parseDouble(rs_message.getString("Date")), rs_message.getString("Data"), rs_author.getString("User.Name"),
					rs_author.getString("User.LastName"), rs_author.getString("User.IDuser"),rs_author.getString("Clinical.SSN"));
			messages.add(m);
			rs_author.close();
		}
		
		rs_message.close();
		stmt.close();
		c.close();

		return messages;
	}

	public Vector<Doctor> getDoctors() throws SQLException, ClassNotFoundException {
		Vector<Doctor> v = new Vector<>();
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT Doctor.IDuser, user.Username, user.Name, user.LastName, clinical.ssn\r\n"
						+ "from doctor\r\n" + "join User\r\n" + "on doctor.iduser = user.iduser\r\n"
						+ "join CLINICAL\r\n" + "on doctor.iduser = clinical.iduser");

		while (rs.next()) {
			Statement stmt2 = null;
			stmt2 = c.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT user.IDuser, telephone.number\r\n" + "from User\r\n"
					+ "join Telephone\r\n" + "on telephone.id = user.IDuser\r\n" + "where user.iduser ='"
					+ rs.getString("IDUser") + "'");

			v.add(new Doctor(rs.getString("Name"), rs.getString("LastName"), rs.getString("IDUser"),
					rs.getString("SSN")));

			rs2.close();
		}

		rs.close();
		stmt.close();
		c.close();

		return v;

	}
	
	//SE NECESITA PASAR A READECG el IDecg en vez del fichero
	public ECG readECG(String IDecg) throws ClassNotFoundException, SQLException {

		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from ECG where ecg.IDecg ='" + IDecg + "'");

		ECG ecg = new ECG();
		Vector<Double> num = new Vector<>();

		ecg.setName(IDecg);
		ecg.setFrequency(rs.getInt("Frequency"));
		ecg.setReport(rs.getString("Diagnostic"));

		rs.getString("Data");

		// rs.getBlob("Data");

		String data = rs.getString("Data");
		// byte[] bdata = blobdata.getBytes(1, (int) blobdata.length());
		// String data = new String(bdata);

		String[] numeros = null;

		numeros = data.toString().split(";");
		for (int i = 0; i < numeros.length; i++) {
			num.add(Double.valueOf(numeros[i]));
		}

		ecg.setData(num);

		rs.close();
		stmt.close();
		c.close();

		// Check the reading of the ecgs
		// System.out.print(ecg.toString());
		return ecg;

	}
	//*
	public static Vector<Patient> readPatients(String doctorID) throws ClassNotFoundException, SQLException {
		
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Patient WHERE IDuser LIKE " + doctorID);
		
		Vector<Patient> patients = new Vector<Patient>();
		
		while(rs.next()) {
			Patient p = new Patient(rs.getString("SSN"), rs.getString("Name"),
							rs.getString("LastName"),rs.getString("IDptt"));
			p.setMunicipality(rs.getString("Municipality"));
			p.setAddress(rs.getString("Address"));
			p.setGender(rs.getString("Sex"));
			p.setStatus(rs.getString("Status"));
			
			patients.add(p);
		}
		
		return patients;
	}
	
	public static Doctor readDoctor(String username) throws SQLException, ClassNotFoundException {
		
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs_user = stmt.executeQuery("SELECT * FROM User WHERE Username LIKE " + username);
		ResultSet rs_clinical = stmt.executeQuery("SELECT SSN FROM Clinical where IDuser LIKE " + rs_user.getString("IDuser"));
		ResultSet rs_doctor = stmt.executeQuery("SELECT MLN FROM Doctor where IDuser LIKE " + rs_user.getString("IDuser"));
		ResultSet rs_tlph = stmt.executeQuery("SELECT Number FROM Telephone where IDuser LIKE " + rs_user.getString("IDuser"));
		
		
		Vector<Integer> phones = new Vector<Integer>();
		
		while(rs_tlph.next()) {
			rs_tlph.getInt("Number");
		}
		Doctor d = new Doctor(rs_user.getString("Name"), rs_user.getString("LastName"), rs_user.getString("IDuser"),
								rs_clinical.getString("SSN"));
		d.setMln(rs_doctor.getString("MLN"));
		d.setPhone(phones);
		//public Doctor(String n, String ln, String dni, String num, String p)
		
		return null;
	}
}
