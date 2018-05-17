package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
					rs.getString("SSN"), rs2.getString("Number")));

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

	public Patient checkSsn(String ssn) throws SQLException, ClassNotFoundException {
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

	public Patient readPatient(String IDptt) throws IOException, SQLException, ClassNotFoundException {

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
		p.setGender(rs.getString("Gender"));
		p.setStatus(rs.getString("Status"));
		// p.setMessage

		ECG ecg = new ECG();

		Vector<ECG> ecgList = ecgList(IDptt);

		p.setECGs(ecgList);

		rs.close();
		stmt.close();
		c.close();

		return p;
	}
	
	public Patient readPatientAssist(String IDptt) throws IOException, SQLException, ClassNotFoundException {

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
		p.setGender(rs.getString("Gender"));
		p.setStatus(rs.getString("Status"));
		// p.setMessage

		ECG ecg = new ECG();

		Vector<ECG> ecgList = ecgList(IDptt);

		p.setECGs(ecgList);

		rs.close();
		stmt.close();
		c.close();

		return p;
	}

	public Vector<String> ecgIDList(String IDptt) throws IOException, ClassNotFoundException, SQLException {

		Vector<String> vector = new Vector<String>();
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select ecg.IDecg from ecg where ecg.IDptt ='" + IDptt + "'");
		while (rs.next()) {
			vector.add(rs.getString("IDecg"));

		}

		rs.close();
		stmt.close();
		c.close();

		return vector;
	}

	public Vector<ECG> ecgList(String IDptt) throws IOException, ClassNotFoundException, SQLException {
		
		Vector<ECG> vector = new Vector<ECG>();
		String database = "src/resources/BDAmberLife.db";
		Connection c = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:" + database);

		Statement stmt = null;
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from ecg where ecg.IDptt ='" + IDptt + "'");
		while (rs.next()) {
			vector.add(new ECG());
					
					//(rs.getString("IDecg"),rs.getString("IDuser"),
					//rs.getString("IDptt"),rs.getInt("Frequency"),rs.getString("Data"),
					//rs.getString("Date"),rs.getString("Seen"),rs.getString("Diagnostic")));

		}

		rs.close();
		stmt.close();
		c.close();

		return vector;
	}

}
