package org.offlike.server.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.offlike.server.data.Campaign;
import org.offlike.server.data.QrCode;
import org.springframework.beans.factory.annotation.Autowired;

public class QrCodeService {

	private static final String GOOGLE_QR_API = "http://chart.apis.google.com/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=";
	
	
	private final MongoDbService mongoDbService;
	
	@Autowired
	public QrCodeService(MongoDbService mongoDbService) {
		this.mongoDbService = mongoDbService;
	}
	
	public URL generateQrCode(String campaignId)  {
		QrCode qrCode = new QrCode();
		Campaign campaign = mongoDbService.findCampaignById(campaignId);
		mongoDbService.createQrCode(campaign, qrCode);
		
		try {
			return new URL(GOOGLE_QR_API + UrlBuilder.createEncodedLikeURL(qrCode.getId(), campaign.getTitle()));
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
}
