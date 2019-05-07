package com.revature.db;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pilot implements Serializable {

	private static final long serialVersionUID = -8585962084002167973L;
	private int id;
	private String name;
	private Date dob;
	private String sex;
	private List<Craft> craft;

	Pilot() {

	}

	Pilot(int i, String n, Date d, String s) {
		id = i;
		name = n;
		dob = d;
		sex = s;
		craft = new ArrayList<Craft>();
	}

	public List<Craft> getCraft() {
		return craft;
	}

	public void setCraft(List<Craft> craft) {
		this.craft = craft;
	}

	public void addCraft(Craft c) {
		craft.add(c);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
