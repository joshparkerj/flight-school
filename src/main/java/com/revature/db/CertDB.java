package com.revature.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CertDB {

	Connection con;

	public CertDB(Connection c) {
		con = c;
	}

	public Cert getByID(int id) {
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM cert WHERE id = ?;")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Cert c = new Cert(rs.getInt("id"), rs.getString("name"));
					try (PreparedStatement ps2 = con.prepareStatement("SELECT * FROM cert_pilot WHERE cert_id = ?;")) {
						ps2.setInt(1, id);
						try (ResultSet rs2 = ps2.executeQuery()) {
							while (rs2.next()) {
								c.addPilot(new Pilot(rs2.getInt("pilot_id"), rs2.getString("pilot"), rs2.getDate("dob"),
										rs2.getString("sex"), rs2.getInt("craft")));
							}
							try (PreparedStatement ps3 = con
									.prepareStatement("SELECT * FROM craft_cert WHERE cert_id = ?;")) {
								ps3.setInt(1, id);
								try (ResultSet rs3 = ps3.executeQuery()) {
									while (rs3.next()) {
										c.addCraft(new Craft(rs3.getInt("aircraft_id"), rs3.getString("aircraft")));
									}
									return c;
								}
							}

						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Cert();

	}

	public List<Cert> getCerts(int page) {
		List<Cert> c = new ArrayList<Cert>();
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM cert LIMIT 10 OFFSET ?;")) {
			ps.setInt(1, 10 * (page - 1));
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					c.add(new Cert(rs.getInt("id"), rs.getString("name")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public int getCount() {
		try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM cert;")) {
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
