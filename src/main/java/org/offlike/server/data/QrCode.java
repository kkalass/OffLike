package org.offlike.server.data;

public class QrCode {

	private String id;
	private String campaignId;
	private String imageData;
	private Double latitude;
	private Double longitude;
	private Integer accuracy;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latituede) {
		this.latitude = latituede;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
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

	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accurency) {
		this.accuracy = accurency;
	}

}
