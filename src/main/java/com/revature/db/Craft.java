package com.revature.db;

import java.io.Serializable;

public class Craft implements Serializable {

	private static final long serialVersionUID = 5188053125440209114L;
	private int id;
	private String name;

	Craft() {

	}

	Craft(int i, String n) {
		id = i;
		name = n;
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

}
