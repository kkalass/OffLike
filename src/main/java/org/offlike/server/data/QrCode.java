package org.offlike.server.data;

public class QrCode {

	private String id;
	private String campaignId;
	private String qrCodeImageLink;
	private int counter = 0;
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

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void increaseCounter() {
		this.counter++;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getQrCodeImageLink() {
		return qrCodeImageLink;
	}
	
	public void setQrCodeImageLink(String qrCodeImageLink) {
		this.qrCodeImageLink = qrCodeImageLink;
	}
	
	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

}
