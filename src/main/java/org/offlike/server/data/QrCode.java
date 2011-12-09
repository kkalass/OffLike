package org.offlike.server.data;

import org.joda.time.DateTime;

public class QrCode {

	private String id;
	private String campaignId;
	private String qrCodeImageLink;
	private int counter = 0;
	private Double latitude;
	private Double longitude;
	private Integer accuracy;

	private DateTime createdAt;
	private DateTime registeredAt;
	private String registeredByUserId;
	
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

	
	public DateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public DateTime getRegisteredAt() {
		return registeredAt;
	}
	public void setRegisteredAt(DateTime registeredAt) {
		this.registeredAt = registeredAt;
	}
	
	public String getRegisteredByUserId() {
		return registeredByUserId;
	}
	public void setRegisteredByUserId(String registeredByUserId) {
		this.registeredByUserId = registeredByUserId;
	}
	
	public boolean isActivated() {
		return longitude != null && latitude != null;
	}
}
