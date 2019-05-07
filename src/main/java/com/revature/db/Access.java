package com.revature.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Access implements Accessible {

	Connection con;
	public PilotDB Pilot;
	public CertDB Cert;
	public CraftDB Craft;
	private static Access uniqueInstance = null;

	public static Access getAccess() {
		if (uniqueInstance == null)
			uniqueInstance = new Access();
		return uniqueInstance;
	}

	private Access() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/flight_school");
			Pilot = new PilotDB(con);
			Cert = new CertDB(con);
			Craft = new CraftDB(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
