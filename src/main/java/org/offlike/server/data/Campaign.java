package org.offlike.server.data;

public class Campaign {

	private String id;
	private String title;
	private String ownerUserId;
	private String description;
	private String externalLink;

	public Campaign() {
	}

	public String getOwnerUserId() {
		return ownerUserId;
	}
	
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	
	public String getDescription() {
		return description;
	}

	public String getExternalLink() {
		return externalLink;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String name) {
		this.title = name;
	}

}
