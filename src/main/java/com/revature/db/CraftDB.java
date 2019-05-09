package com.revature.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CraftDB {

	Connection con;

	public CraftDB(Connection c) {
		con = c;
	}

	public Craft getByID(int id) {
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM aircraft_details WHERE id = ?;")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Craft c = new Craft(rs.getInt("id"), rs.getString("name"), rs.getString("certs"),
							rs.getInt("pilots"));
					try (PreparedStatement ps2 = con
							.prepareStatement("SELECT * FROM craft_pilot WHERE aircraft_id = ?;")) {
						ps2.setInt(1, id);
						try (ResultSet rs2 = ps2.executeQuery()) {
							while (rs2.next()) {
								c.addPilot(new Pilot(rs2.getInt("pilot_id"), rs2.getString("pilot"), rs2.getDate("dob"),
										rs2.getString("sex"), rs2.getInt("craft")));
							}
							return c;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Craft();

	}

	public List<Craft> getCrafts(int page, String searchparam) {
		List<Craft> c = new ArrayList<Craft>();
		String sqlstr;
		if (searchparam.length() == 0)
			sqlstr = "SELECT * FROM aircraft_details LIMIT 10 OFFSET ?;";
		else
			sqlstr = "SELECT * FROM aircraft_details WHERE name ~~* ? LIMIT 10 OFFSET ?;";
		try (PreparedStatement ps = con.prepareStatement(sqlstr)) {
			if (searchparam.length() == 0)
				ps.setInt(1, 10 * (page - 1));
			else {
				ps.setString(1, "%" + searchparam + "%");
				ps.setInt(2, 10 * (page - 1));
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					c.add(new Craft(rs.getInt("id"), rs.getString("name"), rs.getString("certs"), rs.getInt("pilots")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public int getCount() {
		try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM aircraft;")) {
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
