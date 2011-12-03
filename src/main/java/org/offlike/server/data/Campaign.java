package org.offlike.server.data;

public class Campaign {

	private String id;
	private String title;
	private String description;
	private String refererUrl;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRefererUrl() {
		return refererUrl;
	}

	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}

	public Campaign() {
		// this.id = 12; // get id from database
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setId(String id) {
		this.id = id;
	}

}
