package com.revature.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PilotDB implements Accessible.PilotAccess {

	private Connection con;

	PilotDB(Connection c) {
		con = c;
	}

	public Pilot getByID(int id) {
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM pilot WHERE id = ?;")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Pilot p = new Pilot(rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("sex"));
					try (PreparedStatement ps2 = con.prepareStatement("SELECT * FROM pilot_craft WHERE pilot_id = ?;")){
						ps2.setInt(1,id);
						try (ResultSet rs2 = ps2.executeQuery()){
							while (rs2.next()) {
								p.addCraft(new Craft(rs2.getInt("aircraft_id"), rs2.getString("aircraft")));
							}
							return p;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Pilot();
	}

}
