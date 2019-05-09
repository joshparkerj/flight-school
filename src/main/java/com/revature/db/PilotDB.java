package com.revature.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PilotDB implements Accessible.PilotAccess {

	private Connection con;

	PilotDB(Connection c) {
		con = c;
	}

	public Pilot getByID(int id) {
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM pilot_details WHERE id = ?;")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Pilot p = new Pilot(rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("sex"),
							rs.getInt("craft"));
					try (PreparedStatement ps2 = con
							.prepareStatement("SELECT * FROM pilot_aircraft(?);")) {
						ps2.setInt(1, id);
						try (ResultSet rs2 = ps2.executeQuery()) {
							while (rs2.next()) {
								p.addCraft(new Craft(rs2.getInt("aircraft_id"), rs2.getString("aircraft"),
										rs2.getString("certs"), rs2.getInt("pilots")));
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

	public List<Pilot> getPilots(int page) {
		List<Pilot> p = new ArrayList<Pilot>();
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM pilot_details LIMIT 10 OFFSET ?;")) {
			ps.setInt(1, 10 * (page - 1));
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					p.add(new Pilot(rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("sex"),
							rs.getInt("craft")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public int getCount() {
		try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM pilot;")) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
