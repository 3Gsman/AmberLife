package model;

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
import java.util.Vector;

import control.MainCtrl;

public class DBManagement {

	public static String[] checkUser(String usuario, String Password) throws ClassNotFoundException, SQLException {
		String iduser;
		String user[] = new String[2];
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT IDUser FROM User WHERE Username='" + usuario + "' AND Password='" + Password + "' AND Active != 0");

		if (rs.next() == false) {
			user[0] = "false";
		} else {
			user[0] = "true";

			iduser = rs.getString("IDUser");

			rs = stmt.executeQuery("SELECT IDUser FROM clinical WHERE IDuser='" + iduser + "'");

			if (rs.next() == false) {
				user[1] = "admin";
			} else {

				rs = stmt.executeQuery("SELECT IDUser FROM doctor WHERE iduser='" + iduser + "'");

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
	
	public static Assistant readAssistant(String username) throws SQLException, ClassNotFoundException {
		
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Assistant JOIN User ON Assistant.IDUser = User.IDUser"
				+ " WHERE username = '" + username + "'");
		
		Assistant ass = new Assistant(rs.getString("Name"), rs.getString("LastName"), rs.getString("IDuser"), 
				rs.getString("Municipality"), rs.getString("Username"));
		
		rs.close();
		stmt.close();
		c.close();

		
		return ass;
	}

	public static Vector<Assistant> getAssistants() throws ClassNotFoundException, SQLException {
		Vector<Assistant> v = new Vector<>();
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT Assistant.IDuser, Assistant.Municipality, User.Username, User.Name, User.LastName "
						+ "FROM Assistant JOIN User ON Assistant.IDuser = User.IDuser WHERE Active != 0");

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
		ResultSet rs_message = stmt.executeQuery("SELECT * FROM Message WHERE IDptt LIKE '" + patientID + "'");

		while (rs_message.next()) {
			Statement stmt2 = c.createStatement();
			ResultSet rs_author = stmt2.executeQuery("SELECT User.IDuser, User.Name, User.LastName, Clinical.SSN "
						+ "FROM	User, Clinical WHERE User.IDUser LIKE '" + rs_message.getString("IDuser") + "' AND "
						+ "CLINICAL.IDuser LIKE '"  + rs_message.getString("IDuser") + "'");
			
			
			Message m = new Message(rs_message.getString("Date"), rs_message.getString("Data"),
					rs_author.getString("Name"), rs_author.getString("LastName"),
					rs_author.getString("IDuser"), rs_author.getString("SSN"), patientID);
			
			messages.add(m);
			stmt2.close();
			rs_author.close();
		}

		rs_message.close();
		stmt.close();
		c.close();

		return messages;
	}

	public static Vector<Doctor> getDoctors() throws SQLException, ClassNotFoundException {
		Vector<Doctor> v = new Vector<>();
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Doctor.IDuser, User.Username, User.Name, User.LastName, clinical.ssn "
							+ "FROM Doctor JOIN User ON Doctor.IDuser = User.IDuser "
							+ "JOIN CLINICAL ON Doctor.IDuser = clinical.IDuser WHERE Active != 0");

		while (rs.next()) {
			Statement stmt2 = null;
			stmt2 = c.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT User.IDuser, Telephone.number FROM User "
					+ "JOIN Telephone ON Telephone.IDuser = user.IDuser WHERE User.IDuser ='"
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

	// SE NECESITA PASAR A READECG el IDecg en vez del fichero
	public static ECG readECG(String IDecg) throws ClassNotFoundException, SQLException {

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

	// *
	public static Vector<Patient> readPatients(String doctorID) throws ClassNotFoundException, SQLException, IOException {

		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT IDuser FROM Patient WHERE Doctor LIKE '" + doctorID + "'");

		Vector<Patient> patients = new Vector<Patient>();

		while (rs.next()) {
			/*Patient p = new Patient(rs.getString("SSN"), rs.getString("Name"), rs.getString("LastName"),
					rs.getString("IDptt"));
			p.setMunicipality(rs.getString("Municipality"));
			p.setAddress(rs.getString("Address"));
			p.setGender(rs.getString("Sex"));
			p.setStatus(rs.getString("Status"));
			p.setECGs(ecgList(rs.getString("IDptt")));*/
			patients.add(readPatient(rs.getString("IDuser")));
		}
		rs.close();
		stmt.close();
		c.close();

		return patients;
	}

	public static Doctor readDoctor(String username) throws SQLException, ClassNotFoundException {

		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
		
		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs_id = stmt.executeQuery("SELECT IDUser FROM User WHERE Username LIKE '" + username + "'");
		String id = rs_id.getString("IDuser");
		rs_id.close();
		stmt.close();
		
		Statement stmt2 =  c.createStatement();
		ResultSet rs = stmt2.executeQuery("SELECT User.Name, User.LastName, User.Password, User.Username," +
				"User.Email, Doctor.MLN, CLINICAL.SSN FROM User, Doctor, CLINICAL " +
				"WHERE User.IDUser LIKE '" + id + "' AND Doctor.IDUser LIKE '" + id + "' AND "
				+ " CLINICAL.IDUser LIKE '" + id + "'");
		
		Statement stmt3 = c.createStatement();
		ResultSet rs_tlph = 
				stmt3.executeQuery("SELECT Number FROM Telephone where IDuser LIKE '" + id + "'");

		Vector<Integer> phones = new Vector<Integer>();

		while (rs_tlph.next()) {
			phones.add(rs_tlph.getInt("Number"));
		}
		
		Doctor d = new Doctor(rs.getString("Name"), rs.getString("LastName"), id,
				String.valueOf(rs.getInt("SSN")));
		d.setMln(String.valueOf(rs.getInt("MLN")));
		d.setPhone(phones);
		// public Doctor(String n, String ln, String dni, String num, String p)

		rs.close();
		stmt2.close();
		rs_tlph.close();
		stmt3.close();
		c.close();
		return d;
	}

	public static Patient checkId(String dni) throws SQLException, ClassNotFoundException {
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
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

		return pt;

	}

	public static Patient checkSsn(String ssn) throws SQLException, ClassNotFoundException {
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
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

		return pt;

	}
	

	
	public static Patient readPatient(String IDptt) throws IOException, SQLException, ClassNotFoundException {

		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from patient where patient.IDptt ='" + IDptt + "'");

		Patient p = new Patient(IDptt);

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

		return p;
	}
	
	public static Vector<ECG> ecgList(String IDptt) throws IOException, ClassNotFoundException, SQLException {
		
		Vector<ECG> vector = new Vector<ECG>();
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from ecg where ecg.IDptt ='" + IDptt + "'");
		

		
		while (rs.next()) {
			ECG ecg = new ECG();
			Vector<Double> num = new Vector<>();
			
			ecg.setName(rs.getString("IDecg"));
			ecg.setReport(rs.getString("Diagnostic"));
			ecg.setFrequency(rs.getInt("Frequency"));
			
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

		return vector;
	}
	
	public static Vector<Message> readPatientMessages(String idptt) throws IOException, ClassNotFoundException, SQLException {


		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Vector<Message> messages = new Vector<>();
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);
		Statement stmt = null;
		stmt = c.createStatement();
		
		ResultSet rs_ms = stmt.executeQuery("SELECT User.name, User.LastName, CLINICAL.SSN, Message.Data, Message.Date "
				+ "FROM	User, Message, CLINICAL WHERE CLINICAL.IDuser LIKE Message.IDuser AND User.IDuser LIKE Message.IDuser "
				+ "AND Message.IDPtt = '" + idptt + "'");
		

		while(rs_ms.next()) {
			Message ms = new Message(rs_ms.getString("Date"), rs_ms.getString("Data"),
					rs_ms.getString("Name"), rs_ms.getString("LastName"),
					idptt, rs_ms.getString("SSN"), idptt);
			messages.add(ms);
		}
		rs_ms.close();
		stmt.close();
		c.close();
		
		return messages;
		
	}
	
	//Reads an .sql file to create the database
	public static void createDatabase() {
		
		try {
		InputStream is = new FileInputStream("manifest.mf");
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		        
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		        
		while(line != null){
		   sb.append(line);
		   line = buf.readLine();
		}
		        
		String fileAsString = sb.toString();
		
		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE );
		Statement create = conn.createStatement();
		create.execute(fileAsString);
		
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	//Use to create DB and initialize initial values
	/*public static void createDatabase() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE );
			Statement create = conn.createStatement();
			create.execute("CREATE TABLE 'User' ('IDuser' TEXT NOT NULL UNIQUE,'Password' TEXT NOT NULL," 
						+"'Active' INTEGER NOT NULL, 'Name' TEXT NOT NULL, 'LastName' TEXT NOT NULL,"
						+"'Username' TEXT NOT NULL UNIQUE, 'Email'TEXT NOT NULL, PRIMARY KEY('IDuser'));"
					+"CREATE TABLE 'ADMIN' ('IDuser' TEXT NOT NULL, FOREIGN KEY('IDuser') REFERENCES "
						+ "'User'('IDuser'),PRIMARY KEY('IDuser'));" 
					+"CREATE TABLE 'CLINICAL' ('IDuser'	TEXT NOT NULL, 'SSN' INTEGER NOT NULL,"
						+"FOREIGN KEY('IDuser') REFERENCES 'User'('IDuser'),PRIMARY KEY('IDuser'));"
					+"CREATE TABLE 'Assistant' ('IDuser' TEXT NOT NULL, 'Municipality' TEXT NOT NULL,"
							+ "FOREIGN KEY('IDuser') REFERENCES 'User'('IDuser'), PRIMARY KEY('IDuser'));"
					+"CREATE TABLE 'Doctor'('IDuser'TEXT NOT NULL, 'MLN'INTEGER NOT NULL,"
							+"FOREIGN KEY('IDuser') REFERENCES 'User'('IDuser'),PRIMARY KEY('IDuser'));"
					+"CREATE TABLE 'Telephone' ('IDuser' TEXT NOT NULL,'Number' TEXT NOT NULL,"
							+"PRIMARY KEY('IDuser','Number'), FOREIGN KEY('IDuser') REFERENCES 'Doctor'('IDuser'));"
					+"CREATE TABLE 'Patient' ('IDptt' TEXT NOT NULL UNIQUE, 'Name' TEXT NOT NULL, 'LastName' TEXT NOT NULL,"
							+"'Municipality' TEXT NOT NULL, 'Address' TEXT NOT NULL, 'Sex' TEXT NOT NULL,"
							+"'Status'	TEXT NOT NULL, 'SSN' TEXT NOT NULL, 'Doctor' TEXT NOT NULL, PRIMARY KEY('IDptt'),"
							+"FOREIGN KEY('Doctor') REFERENCES 'Doctor'('IDuser'));"
					+"CREATE TABLE 'Message'('IDmsg' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
							+"'IDuser'	TEXT NOT NULL, 'IDptt' TEXT NOT NULL, 'Data' TEXT NOT NULL,"
							+"'Date' TEXT NOT NULL,'Seen' INTEGER NOT NULL, FOREIGN KEY('IDuser') REFERENCES 'CLINICAL'('IDuser'),"
							+"FOREIGN KEY('IDptt') REFERENCES 'Patient'('IDptt'));"
					+"CREATE TABLE 'ECG'('IDecg' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
							+"'IDuser' TEXT NOT NULL, 'IDptt' TEXT NOT NULL, 'Frequency' INTEGER NOT NULL,"
							+"'Data' BLOB NOT NULL, 'Date' TEXT NOT NULL, 'Seen' TEXT NOT NULL, 'Diagnostic' TEXT,"
							+"FOREIGN KEY('IDuser') REFERENCES 'Assistant'('IDuser'), FOREIGN KEY('IDptt') REFERENCES 'Patient'('IDptt'));"
					);
			create.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	/*private static void populateDB() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + MainCtrl.DATABASE );
			Statement create = conn.createStatement();
			create.execute(
				"INSERT INTO User(IDuser, Password, Active, Name, LastName, Username, Email)" +
						"VALUES(56566443J,m1#M0ra13s@uem.es,1,Maria de la Luz,Morales Botello,?,?);"
				
				
				);
			
			
			/*Insert Cheatsheet:
			 * 
			 * "INSERT INTO User(IDuser, Password, Active, Name, LastName, Username, Email)" +
					"VALUES(?,?,?,?,?,?,?);";
			"INSERT INTO ADMIN(IDuser) VALUES (?);";
			"INSERT INTO CLINICAL(IDUser, SSN) VALUES(?,?);";
			"INSERT INTO Doctor(IDUser, MLN) VALUES(?,?);";
			"INSERT INTO Telephone(IDuser, Number) VALUES (?,?);";
			"INSERT INTO Assistant(IDuser, Municipality VALUES (?,?);";
			"INSERT INTO Patient(IDptt, Name, LastName, Municipality, Address, Sex, Status, SSN, Doctor)" +
					"VALUES(?,?,?,?,?,?,?,?,?);";
			"INSERT INTO Messages(IDuser, IDptt, Data, Date, Seen) VALUES (?,?,?,?,?);";
			"INSERT INTO ECG(IDuser, IDptt, Frequency, Data, Date, Seen, Diagnostic)" +
					+"VALUES(?,?,?,?,?,?,?);";
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
}
