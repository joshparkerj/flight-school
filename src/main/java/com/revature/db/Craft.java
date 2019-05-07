package com.revature.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Craft implements Serializable {

	private static final long serialVersionUID = 5188053125440209114L;
	private int id;
	private String name;
	private List<Pilot> pilots;

	Craft() {

	}

	Craft(int i, String n) {
		id = i;
		name = n;
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

}
