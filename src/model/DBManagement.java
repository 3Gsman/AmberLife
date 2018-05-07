package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		ResultSet rs = stmt.executeQuery( "select IDUser from User where username='" 
										+ usuario + "' and password='"
										+ Password + "'");
		
		if(rs.next() == false) {
			user[0] = "false";
		}else {
			user[0]= "true";
		
		
			iduser = rs.getString("IDUser");
			
			rs = stmt.executeQuery( "select IDUser from clinical where iduser='" + iduser +"'");
	
			if(rs.next() == false) {
				user[1] = "admin";
			}else {
			
				rs = stmt.executeQuery( "select IDUser from doctor where iduser='" + iduser +"'");
				
				if(rs.next() == false) {
					user[1] = "tecnico";
				}else {
					user[1] = "medico";
				}
			}
		}
		
		
		
		rs.close();
		stmt.close();
		c.close();
		
		
		return user;
	}
	
}
