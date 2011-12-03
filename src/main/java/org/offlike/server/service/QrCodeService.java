package org.offlike.server.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.offlike.server.data.Campaign;

public class QrCodeService {

	private static final String GOOGLE_QR_API_URL = "http://chart.apis.google.com/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=http%3A%2F%2Fofflike.org%2Fcampaign%2F";

	public void generateQrCode(Campaign campaign, String fileName) throws Exception {
		String generatedCampagneId = "" + campaign.getId();
		URL url = new URL(GOOGLE_QR_API_URL + generatedCampagneId);
		BufferedImage read = ImageIO.read(url);
		ImageIO.write(read, "png", new File(fileName));	
	}
	
	public static void main(String[] args) throws Exception {
		Campaign campaign = new Campaign();
		new QrCodeService().generateQrCode(campaign, "qrCode.png");
	}
}
