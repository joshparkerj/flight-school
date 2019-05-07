package com.revature.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cert implements Serializable {

	private static final long serialVersionUID = -621842246422607931L;
	private int id;
	private String name;
	private List<Pilot> pilots;
	private List<Craft> craft;

	Cert() {

	}

	Cert(int i, String n) {
		id = i;
		name = n;
		pilots = new ArrayList<Pilot>();
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

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}

	public void addPilot(Pilot p) {
		pilots.add(p);
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
