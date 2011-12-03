package org.offlike.server.data;

public class QrCode {

	private String id;
	private int campaignId;
	private String imageData;
	private double latituede;
	private double longitude;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public double getLatituede() {
		return latituede;
	}

	public void setLatituede(double latituede) {
		this.latituede = latituede;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String qrCode) {
		this.imageData = qrCode;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

}
