package org.offlike.server.data;

import org.joda.time.DateTime;

public class User {

	private String id;
	
	private String username;
	
	private DateTime createdAt;
	
	public User(String id, String username, DateTime createdAt) {
		this.id = id;
		this.username = username;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public DateTime getCreatedAt() {
		return createdAt;
	}
	
}
