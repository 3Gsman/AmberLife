package model;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import control.MainCtrl;

public class DBManagement {

	/**
	 * Checks whether an user exists or not by reading into the DB and if it
	 * does, returns the type of user that it is (Assistant, Doctor, or Admin)
	 * 
	 * @param usuario
	 *            username of the checked user
	 * @param Password
	 *            password of the checked user
	 * @return the type of user, if it exists
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */

	private static String db;
	private static String userdb;
	private static String pass;
	
	public static void getDBData() throws IOException{
		Properties prop = new Properties();
		String fileName = "src/resources/config/database.config";
		InputStream is = new FileInputStream(fileName);
		prop.load(is);

	
		db = "jdbc:mariadb://" + prop.getProperty("ip")+":"+prop.getProperty("port")+"/"+prop.getProperty("name");
		userdb = prop.getProperty("user");
		pass = prop.getProperty("pass");
	}

	// private static String db =
	// "jdbc:mariadb://esp.uem.es:3306/pi2_bd_amberlife";
	// private static String userdb = "pi2_amberlife";
	// private static String pass = "rdysdhsks";

	public static String[] checkUser(String usuario, String Password) throws ClassNotFoundException, SQLException {
		MainCtrl.startLoading();
		String iduser;
		String user[] = new String[2];
		Connection c =  DBManagement.getConnection();

		try {
			Statement stmt = null;
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Username FROM User");/* WHERE Username='" + usuario + "' AND Password='"
					+ Password + "' AND Active != 0"*/
			
			//Añadimos aqui la algoritmia, "forzandolo" en vez de hacer la query en SQL, para comprobar si el usuario existe
		
			Vector<String> usernames =new Vector<String>();
			while(rs.next()) {
				usernames.add(rs.getString("Username"));
			}
			
			String id = Utilities.findUsername(usernames, usuario);
			System.out.println(id);
			rs.close();
			stmt.close();
			
			if(id.equals("")) 
				JOptionPane.showMessageDialog(MainCtrl.getMainFrame(), "User doesn't exist.", 
							"Error", JOptionPane.ERROR_MESSAGE);
			else {
				Statement stmt_2 = c.createStatement();
				ResultSet rs_2 = stmt_2.executeQuery("SELECT * FROM User WHERE Username='" + usuario + 
						"' AND Password='" +  Password + "' AND Active != 0");
				
				if (rs_2.next() == false) {
					user[0] = "false";
					JOptionPane.showMessageDialog(MainCtrl.getMainFrame(), "Incorrect Password.", "Error", JOptionPane.ERROR_MESSAGE);	//sale una ventana de diálogo para alertar de un error
				} else {
					user[0] = "true";
	
					iduser = rs_2.getString("IDUser");
	
					rs_2 = stmt_2.executeQuery("SELECT IDUser FROM CLINICAL WHERE IDuser='" + iduser + "'");
	
					if (rs_2.next() == false) {
						user[1] = "admin";
					} else {
	
						rs_2 = stmt_2.executeQuery("SELECT IDUser FROM Doctor WHERE iduser='" + iduser + "'");
	
						if (rs_2.next() == false) {
							user[1] = "tecnico";
						} else {
							user[1] = "medico";
						}
					}
				}
				stmt_2.close();
				rs_2.close();
			}

			stmt.close();
			c.close();
		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
		MainCtrl.stopLoading();
		return user;
	}

	/**
	 * Checks whether an assistant exists and returns their information from
	 * their username.
	 * 
	 * @param username
	 *            the username of the assistant to check
	 * @return an array of strings containing the data of the assistant
	 * @throws SQLException,
	 *             ClassNotFoundException
	 */

	public static Assistant readAssistant(String username) throws SQLException, ClassNotFoundException {
		MainCtrl.startLoading();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Assistant JOIN User ON Assistant.IDUser = User.IDUser"
				+ " WHERE Username = '" + username + "'");
		
		
		if (rs.next()) {
			Assistant ass = new Assistant(rs.getString("Name"), rs.getString("LastName"), rs.getString("IDuser"),
					rs.getString("Municipality"), rs.getString("Username"));
			rs.close();
			stmt.close();
			c.close();
			return ass;
		} else {
			rs.close();
			stmt.close();
			c.close();

			Assistant ass = new Assistant(null, null, null, null, null);
			MainCtrl.stopLoading();
			return ass;
		}
	}

	/**
	 * Gets all assistants from the DB and returns a list with their data.
	 * 
	 * @return the list of assistants registered within the system.
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */

	public static Vector<Assistant> getAssistants() throws ClassNotFoundException, SQLException {
		MainCtrl.startLoading();
		Vector<Assistant> v = new Vector<>();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT Assistant.IDuser, Assistant.Municipality, User.Username, User.Name, User.LastName "
						+ "FROM Assistant JOIN User ON Assistant.IDuser = User.IDuser WHERE Active != 0");

		while (rs.next()) {
			v.add(new Assistant(rs.getString("Name"), rs.getString("LastName"), rs.getString("IDUser"),
					rs.getString("Municipality"), rs.getString("Username")));
		}

		rs.close();
		stmt.close();
		c.close();

		Utilities.sortUser(v, 0, v.size()-1);
		
		MainCtrl.stopLoading();
		return v;

	}

	/**
	 * Gets all the messages from one patient from the DB and returns a list
	 * with their data.
	 * 
	 * @return the list of messages of one patient registered within the system.
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */

	public static Vector<Message> readMessages(String patientID) throws ClassNotFoundException, SQLException {
		MainCtrl.startLoading();
		Vector<Message> messages = new Vector<>();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs_message = stmt.executeQuery("SELECT * FROM Message WHERE IDptt LIKE '" + patientID + "'");

		while (rs_message.next()) {
			Statement stmt2 = c.createStatement();
			ResultSet rs_author = stmt2.executeQuery("SELECT User.IDuser, User.Name, User.LastName, CLINICAL.SSN "
					+ "FROM	User, CLINICAL WHERE User.IDUser LIKE '" + rs_message.getString("IDuser") + "' AND "
					+ "CLINICAL.IDuser LIKE '" + rs_message.getString("IDuser") + "'");
			if (rs_author.next()) {
				Message m = new Message(rs_message.getString("Date"), rs_message.getString("Data"),
						rs_author.getString("Name"), rs_author.getString("LastName"), rs_author.getString("IDuser"),
						rs_author.getString("SSN"), patientID);

				messages.add(m);
				stmt2.close();
				rs_author.close();
			}
		}

		rs_message.close();
		stmt.close();
		c.close();
		
		Utilities.sortMessages(messages, 0, messages.size()-1);

		MainCtrl.stopLoading();
		return messages;
	}

	/**
	 * Gets all doctors from the DB and returns a list with their data.
	 * 
	 * @return the list of doctors registered within the system.
	 * @throws SQLException,
	 *             ClassNotFoundException
	 */

	public static Vector<Doctor> getDoctors() throws SQLException, ClassNotFoundException {
		MainCtrl.startLoading();
		Vector<Doctor> v = new Vector<>();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Doctor.IDuser, User.Username, User.Name, User.LastName, CLINICAL.ssn "
				+ "FROM Doctor JOIN User ON Doctor.IDuser = User.IDuser "
				+ "JOIN CLINICAL ON Doctor.IDuser = CLINICAL.IDuser WHERE Active != 0");

		while (rs.next()) {
			Statement stmt2 = null;
			stmt2 = c.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT User.IDuser, Telephone.Number FROM User "
					+ "JOIN Telephone ON Telephone.IDuser = User.IDuser WHERE User.IDuser ='" + rs.getString("IDUser")
					+ "'");

			v.add(new Doctor(rs.getString("Name"), rs.getString("LastName"), rs.getString("IDUser"),
					rs.getString("SSN")));

			rs2.close();
		}

		rs.close();
		stmt.close();
		c.close();
		
		Utilities.sortUser(v, 0, v.size()-1);

		MainCtrl.stopLoading();
		return v;

	}

	/**
	 * Parses an ECG file into an ECG object.
	 * 
	 * @param filename
	 *            The path to the ECG file
	 * @return the resultant ECG object
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */

	public static ECG readECG(int IDecg) throws ClassNotFoundException, SQLException {
		MainCtrl.startLoading();
		Connection c = DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from ECG where ECG.IDecg ='" + IDecg + "'");

		ECG ecg = new ECG(IDecg,rs.getInt("Frequency"), new Vector<Double>(),rs.getString("Diagnostic"),rs.getString("Date"));
		Vector<Double> num = new Vector<>();

		rs.getString("Data");
		String data = rs.getString("Data");
		String[] numeros = null;
		numeros = data.toString().split(";");
		for (int i = 0; i < numeros.length; i++) {
			num.add(Double.valueOf(numeros[i]));
		}

		ecg.setData(num);

		rs.close();
		stmt.close();
		c.close();

		MainCtrl.stopLoading();
		return ecg;

	}

	/**
	 * Gets all patients of one doctor from the DB and returns a list with their
	 * data.
	 * 
	 * @return the list of patients of one doctor registered within the system.
	 * @throws SQLException,
	 *             ClassNotFoundException
	 */

	public static Vector<Patient> readPatients(String doctorID)
			throws ClassNotFoundException, SQLException, IOException {

		MainCtrl.startLoading();
		Connection c = DBManagement.getConnection();
		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT IDptt FROM Patient WHERE Doctor LIKE '" + doctorID + "'");

		Vector<Patient> patients = new Vector<Patient>();

		while (rs.next()) {
			/*
			 * Patient p = new Patient(rs.getString("SSN"),
			 * rs.getString("Name"), rs.getString("LastName"),
			 * rs.getString("IDptt"));
			 * p.setMunicipality(rs.getString("Municipality"));
			 * p.setAddress(rs.getString("Address"));
			 * p.setGender(rs.getString("Sex"));
			 * p.setStatus(rs.getString("Status"));
			 * p.setECGs(ecgList(rs.getString("IDptt")));
			 */
			patients.add(readPatient(rs.getString("IDptt")));
		}
		rs.close();
		stmt.close();
		c.close();
		
		Utilities.sortPatients(patients, 0, patients.size()-1);
		
		MainCtrl.stopLoading();
		return patients;
	}

	/**
	 * Checks whether an doctor exists and returns their information from their
	 * username.
	 * 
	 * @param username
	 *            the username of the doctor to check
	 * 
	 * @return an array of strings containing the data of the doctor
	 * @throws SQLException,
	 *             ClassNotFoundException
	 */

	public static Doctor readDoctor(String username) throws SQLException, ClassNotFoundException {
		MainCtrl.startLoading();
		Connection c = DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs_id = stmt.executeQuery("SELECT IDuser FROM User WHERE Username LIKE '" + username + "'");

		String id = null;

		if (rs_id.next())
			id = rs_id.getString("IDuser");

		rs_id.close();
		stmt.close();

		Statement stmt2 = c.createStatement();
		ResultSet rs = stmt2.executeQuery("SELECT User.Name, User.LastName, User.Password, User.Username,"
				+ "User.Email, Doctor.MLN, CLINICAL.SSN FROM User, Doctor, CLINICAL " + "WHERE User.IDUser LIKE '" + id
				+ "' AND Doctor.IDuser LIKE '" + id + "' AND " + " CLINICAL.IDuser LIKE '" + id + "'");

		Statement stmt3 = c.createStatement();
		ResultSet rs_tlph = stmt3.executeQuery("SELECT Number FROM Telephone where IDuser LIKE '" + id + "'");

		Vector<Integer> phones = new Vector<Integer>();

		while (rs_tlph.next()) {
			phones.add(rs_tlph.getInt("Number"));
		}

		if (rs.next()) {
			Doctor d = new Doctor(rs.getString("Name"), rs.getString("LastName"), id, String.valueOf(rs.getInt("SSN")));
			d.setMln(String.valueOf(rs.getInt("MLN")));
			d.setPhone(phones);

			rs.close();
			stmt2.close();
			rs_tlph.close();
			stmt3.close();
			c.close();
			MainCtrl.stopLoading();
			return d;
		} else {
			Doctor d = new Doctor(null, null, null, null);
			MainCtrl.stopLoading();
			return d;
		}
	}

	/**
	 * Checks whether a patient exists within the DB using their id number and
	 * returns an array containing the data that the Assistant has the
	 * authorization to see, to protect confidential information.
	 * 
	 * @param id
	 *            the id of the patient to check
	 * @return an array with the authorized data of the patient
	 * 
	 * @throws SQLException,
	 *             ClassNotFoundException
	 */

	public static Patient checkId(String dni) throws SQLException, ClassNotFoundException {
		MainCtrl.startLoading();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();

		Patient pt = new Patient("null", "null", "null", "null");

		stmt.executeUpdate("create view PatientMinInfo as select IDPtt, Name,LastName,SSN from Patient");

		ResultSet rs = stmt.executeQuery("SELECT * FROM PatientMinInfo WHERE IDPtt='" + dni + "'");

		if (rs.next() == true) {
			pt.setId(rs.getString("IDPtt"));
			pt.setName(rs.getString("Name"));
			pt.setLastname(rs.getString("LastName"));
			pt.setSsn(rs.getString("SSN"));
		}

		stmt.executeUpdate("drop view PatientMinInfo");

		rs.close();
		stmt.close();
		c.close();

		MainCtrl.stopLoading();
		return pt;

	}

	/**
	 * Checks whether a patient exists within the DB using their ssn number and
	 * returns an array containing the data that the Assistant has the
	 * authorization to see, to protect confidential information.
	 * 
	 * @param id
	 *            the ssn of the patient to check
	 * @return an array with the authorized data of the patient
	 * 
	 * @throws SQLException,
	 *             ClassNotFoundException
	 */

	public static Patient checkSsn(String ssn) throws SQLException, ClassNotFoundException {
		MainCtrl.startLoading();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();

		Patient pt = new Patient("null", "null", "null", "null");
		pt.setSsn("null");

		stmt.executeUpdate("create view PatientMinInfo as select IDPtt, Name,LastName,SSN from Patient");

		ResultSet rs = stmt.executeQuery("SELECT * FROM PatientMinInfo WHERE SSN='" + ssn + "'");

		if (rs.next() == true) {
			pt.setId(rs.getString("IDPtt"));
			pt.setName(rs.getString("Name"));
			pt.setLastname(rs.getString("LastName"));
			pt.setSsn(rs.getString("SSN"));
		}

		stmt.executeUpdate("drop view PatientMinInfo");

		rs.close();
		stmt.close();
		c.close();

		MainCtrl.stopLoading();
		return pt;

	}

	/**
	 * Checks whether a patient exists in the DB and returns their data as a
	 * Patient object, containing confidential information that only Doctors may
	 * see.
	 * 
	 * @param username
	 *            the username of the patient to check
	 * @return the resultant Patient file
	 * @throws IOException,
	 *             SQLException, ClassNotFoundException
	 */

	public static Patient readPatient(String IDptt) throws IOException, SQLException, ClassNotFoundException {
		
		MainCtrl.startLoading();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Patient where Patient.IDptt ='" + IDptt + "'");

		Patient p = new Patient(IDptt);

		if (rs.next()) {
			p.setId(IDptt);
			p.setName(rs.getString("Name"));
			p.setLastname(rs.getString("LastName"));
			p.setSsn(rs.getString("SSN"));
			p.setMunicipality(rs.getString("Municipality"));
			p.setAddress(rs.getString("Address"));
			p.setGender(rs.getString("Sex"));
			p.setStatus(rs.getString("Status"));

			Vector<ECG> ecgList = ecgList(IDptt);

			p.setECGs(ecgList);

			rs.close();
			stmt.close();
			c.close();

			MainCtrl.stopLoading();
			return p;
		} else {
			rs.close();
			stmt.close();
			c.close();

			MainCtrl.stopLoading();
			return p;
		}
	}

	/**
	 * Gets all ECGs of one patient from the DB and returns a list with their
	 * data.
	 * 
	 * @return the list of ECGs of one patient registered within the system.
	 * @throws IOException,
	 *             ClassNotFoundException, SQLException
	 */

	public static Vector<ECG> ecgList(String IDptt) throws IOException, ClassNotFoundException, SQLException {

		MainCtrl.startLoading();
		Vector<ECG> vector = new Vector<ECG>();
		Connection c = DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from ECG where ECG.IDptt ='" + IDptt + "'");

		while (rs.next()) {
			ECG ecg = new ECG(rs.getInt("IDecg"),rs.getInt("Frequency"),new Vector<Double>(),rs.getString("Diagnostic"),rs.getString("Date"));
			Vector<Double> num = new Vector<>();


			String data = rs.getString("Data");
			String[] numeros = null;

			numeros = data.toString().split(";");
			for (int i = 0; i < numeros.length; i++) {
				num.add(Double.valueOf(numeros[i]));
			}

			ecg.setData(num);
			vector.add(ecg);

		}

		rs.close();
		stmt.close();
		c.close();
		
		Utilities.sortECG(vector, 0, vector.size()-1);
		MainCtrl.stopLoading();
		return vector;
	}

	/**
	 * Reads a list of all messages relating to a patient from the DB
	 * 
	 * @param number
	 *            id number of the patient
	 * @return a list of all the messages relating to a patient
	 * @throws IOException,
	 *             ClassNotFoundException, SQLException
	 */
	public static Vector<Message> readPatientMessages(String idptt)
			throws IOException, ClassNotFoundException, SQLException {

		MainCtrl.startLoading();
		Vector<Message> messages = new Vector<>();

		Connection c = DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();

		ResultSet rs_ms = stmt
				.executeQuery("SELECT User.name, User.LastName, User.IDuser, CLINICAL.SSN, Message.Data, Message.Date "
						+ "FROM	User, Message, CLINICAL WHERE CLINICAL.IDuser LIKE Message.IDuser AND User.IDuser LIKE Message.IDuser "
						+ "AND Message.IDPtt = '" + idptt + "'");

		while (rs_ms.next()) {
			Message ms = new Message(rs_ms.getString("Date"), rs_ms.getString("Data"), rs_ms.getString("Name"),
					rs_ms.getString("LastName"), rs_ms.getString("IDuser"), rs_ms.getString("SSN"), idptt);
			messages.add(ms);
		}
		rs_ms.close();
		stmt.close();
		c.close();

		MainCtrl.stopLoading();
		return messages;

	}

	public static void reassignPatients(String IDuser) throws ClassNotFoundException, SQLException {
		int i;
		String idPatient = null;
		String idDoctor = null;

		MainCtrl.startLoading();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();

		ResultSet rd = stmt.executeQuery("SELECT count(Doctor)\r\n" + "from Patient\r\n" + "where Doctor = '" + IDuser
				+ "'" + "group by Doctor\r\n" + "order by count(Doctor) asc");

		int countid = 0;
		if (rd.next())
			countid = rd.getInt("count(Doctor)");

		while (countid > 0) {

			ResultSet rp = stmt
					.executeQuery("SELECT IDPtt\r\n" + "from Patient\r\n" + "where Doctor = '" + IDuser + "'");

			if (rd.next())
				idPatient = rp.getString("IDPtt");

			ResultSet rs = stmt.executeQuery("SELECT Patient.Doctor, count(Patient.Doctor)\r\n" + "from Patient\r\n"
					+ "join User on User.IDUser = Patient.Doctor\r\n" + "where Patient.Doctor != '" + IDuser
					+ "' and User.Active != 0\r\n" + "group by Patient.Doctor\r\n"
					+ "order by count(Patient.Doctor) asc");

			if (rd.next())
				idDoctor = rs.getString("Doctor");

			stmt.executeUpdate("UPDATE Patient set Doctor ='" + idDoctor + "' where Doctor = '" + IDuser + "'"
					+ "and IDPtt = '" + idPatient + "'");

			rp.close();
			rs.close();
			countid--;
		}

		rd.close();
		stmt.close();
		c.close();
		MainCtrl.stopLoading();

	}

	public static Boolean checkDoctor(String dni) throws SQLException, ClassNotFoundException {
		MainCtrl.startLoading();
		Connection c = DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		Boolean isdoctor;

		ResultSet rs = stmt.executeQuery("SELECT * FROM Doctor WHERE IDUser='" + dni + "'");

		if (rs.next() == true) {
			isdoctor = true;
		} else {
			isdoctor = false;
		}

		rs.close();
		stmt.close();
		c.close();

		MainCtrl.stopLoading();
		return isdoctor;

	}
	
	public static boolean validatePatient(String name, String lastname, String id) {
		MainCtrl.startLoading();
		boolean valid = true;
		
		
		Pattern p = Pattern.compile("^[ A-Za-z]+$");
		Matcher m = p.matcher(name);
		valid = m.matches();
	
		if(!valid) {
			JOptionPane.showMessageDialog(MainCtrl.getMainFrame(), "Please, use characters and spaces only in the name");
		}
		else {
		
		Matcher m2 = p.matcher(lastname);
		valid = m2.matches();
			if(!valid) JOptionPane.showMessageDialog(MainCtrl.getMainFrame(), "Please, use characters and spaces only in the surname");
			else {
				String id_numbers = id.substring(0, 7);
				String id_letter = id.substring(8,8);
				if(!(id_numbers.matches("[0-9]+") && id_letter.contains("[a-zA-Z]+") && id.length() == 9)) {
					JOptionPane.showMessageDialog(MainCtrl.getMainFrame(), "ID format is invalid");
					valid = false;
				}
			}
		}
		MainCtrl.stopLoading();
		return valid;
	}
	
	public static boolean validateUser(String name, String lastname, String id, String email) {
		boolean valid = true;
		
		MainCtrl.startLoading();
		Pattern p = Pattern.compile("^[ A-Za-z]+$");
		Matcher m = p.matcher(name);
		valid = m.matches();
	
		if(!valid) {
			JOptionPane.showMessageDialog(MainCtrl.getMainFrame(), "Please, use characters and spaces only in the name");
		}
		else {
		
		Matcher m2 = p.matcher(lastname);
		valid = m2.matches();
			if(!valid) JOptionPane.showMessageDialog(MainCtrl.getMainFrame(), "Please, use characters and spaces only in the surname");
			else {
				String id_numbers = id.substring(0, 7);
				String id_letter = id.substring(8,8);
				if(!(id_numbers.matches("[0-9]+") && id_letter.contains("[a-zA-Z]+") && id.length() == 9)) {
					JOptionPane.showMessageDialog(MainCtrl.getMainFrame(), "ID format is invalid");
					valid = false;
				}
				else {
					//Email check: TO DO
				}	
			}
			
		}
		MainCtrl.stopLoading();
		return valid;
	}
	
	public static void confirmECG (ECG ecg, String assid, String pttid) throws SQLException {
		MainCtrl.startLoading();
		Connection c =  DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();
		
		String data = ecg.getData().get(0).toString();
		
		for(int i = 1; i < ecg.getData().size(); i++) {
			data = data + ";" + ecg.getData().get(i).toString();
		}
		
		
		Date date = new Date();
		
		
		stmt.executeUpdate("INSERT INTO ECG(IDuser, IDptt, Frequency, Data, Date, Seen, Diagnostic)"
				+ " VALUES('" + assid +"','" +pttid+"','"+ecg.getFrequency()+"','"+data+"',"
				+"'"+date+"','0','" +ecg.getReport()+"')");
		
		stmt.close();
		c.close();
		MainCtrl.stopLoading();
	}
	
	
	public static String getAssistECG(ECG e) throws SQLException{
		MainCtrl.startLoading();
		String assistECG = null;
		String name = null;
		String lastName = null;
		
		int id = e.getId();
		
		Connection c = DBManagement.getConnection();

		Statement stmt = null;
		stmt = c.createStatement();

		ResultSet rs_ms = stmt.executeQuery("Select User.Name, User.LastName from User join ECG on User.IDuser = ECG.IDuser where ECG.IDecg = " + id + ";");
		

		while (rs_ms.next()) {
			name = rs_ms.getString("Name");
			lastName = rs_ms.getString("LastName");
		}
		
		assistECG = name +" "+ lastName;
		 
		rs_ms.close();
		stmt.close();
		c.close();
		
		MainCtrl.stopLoading();
		return assistECG;
	}
	
	/**
	 * Returns a Connection to the assigned DDBB
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		MainCtrl.startLoading();
		Connection c = null;
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
	
			//String db = "jdbc:mariadb://esp.uem.es:3306/pi2_bd_amberlife";
			//String userdb = "pi2_amberlife";
			//String pass = "rdysdhsks";
		
			c = DriverManager.getConnection(db, userdb, pass);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Component f = null;
			JOptionPane.showMessageDialog(f, "Failed to connect to server", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainCtrl.stopLoading();
		return c;
	}
	
}
