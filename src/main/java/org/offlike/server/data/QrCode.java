package org.offlike.server.data;

public class QrCode {

	private String id;
	private String campaignId;
	private String imageData;
	private double latituede;
	private double longitude;
	private int accurency;

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

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public int getAccurency() {
		return accurency;
	}

	public void setAccurency(int accurency) {
		this.accurency = accurency;
	}

}
