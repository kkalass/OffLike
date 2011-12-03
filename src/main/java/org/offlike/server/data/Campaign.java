package org.offlike.server.data;

public class Campaign {

	private String id;
	private String title;
	private String externalLink;
	private String description;

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

	public String getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
