package com.revature.db;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Pilot implements Serializable {

	private static final long serialVersionUID = -8585962084002167973L;
	private int id;
	private String name;
	private Date dob;
	private String sex;
	private String url;
	private int age;
	private int aircraft_count;
	private List<Craft> craft;

	Pilot() {

	}

	Pilot(int i, String n, Date d, String s, int a) {
		id = i;
		name = n;
		dob = d;
		sex = s;
		aircraft_count = a;
		age = Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
		url = "https://flightschool.joshquizzes.com/api/pilot/" + id + "/";
		craft = new ArrayList<Craft>();
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

	public String getUrl() {
		return url;
	}

	public int getAge() {
		return age;
	}

	public int getAircraft_count() {
		return aircraft_count;
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

}
