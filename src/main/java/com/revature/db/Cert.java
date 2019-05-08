package com.revature.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.revature.Constants;

public class Cert implements Serializable {

	private static final long serialVersionUID = -621842246422607931L;
	private int id;
	private String name;
	private int craft_count;
	private int pilot_count;
	private String url;
	private List<Pilot> pilots;
	private List<Craft> craft;

	Cert() {
		pilots = new ArrayList<Pilot>();
		craft = new ArrayList<Craft>();
	}

	Cert(int i, String n, int c, int p) {
		id = i;
		name = n;
		craft_count = c;
		pilot_count = p;
		url = Constants.url + "certification/" + id + "/";
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

	public int getCraft_count() {
		return craft_count;
	}

	public int getPilot_count() {
		return pilot_count;
	}

	public String getUrl() {
		return url;
	}

}
