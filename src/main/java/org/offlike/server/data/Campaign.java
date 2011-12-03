package org.offlike.server.data;

public class Campaign {

	private int id;
	private String name;

	public Campaign() {
		this.id = 12; // get id from database
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
