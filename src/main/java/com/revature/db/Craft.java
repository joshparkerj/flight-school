package com.revature.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Craft implements Serializable {

	private static final long serialVersionUID = 5188053125440209114L;
	private int id;
	private String name;
	private String certs;
	private int pilot_count;
	private String url;
	private List<Pilot> pilots;

	Craft() {

	}

	Craft(int i, String n, String c, int p) {
		id = i;
		name = n;
		certs = c;
		pilot_count = p;
		url = "https://flightschool.joshquizzes.com/api/aircraft/" + id + "/";
		pilots = new ArrayList<Pilot>();
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

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}

	public void addPilot(Pilot p) {
		pilots.add(p);
	}

	public String getCerts() {
		return certs;
	}

	public int getPilot_count() {
		return pilot_count;
	}

	public String getUrl() {
		return url;
	}

}
