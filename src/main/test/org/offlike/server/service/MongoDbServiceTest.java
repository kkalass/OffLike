package org.offlike.server.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.offlike.server.data.Campaign;
import org.offlike.server.data.QrCode;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDbServiceTest {

	private MongoDbService mongoDbService;
	private DB database;

	@Before
	public void setup() throws UnknownHostException, MongoException {
		Mongo m = new Mongo("localhost", 27017);
		database = m.getDB("offlike");
		database.dropDatabase();
		database = m.getDB("offlike");
		
		mongoDbService = new MongoDbService(database);
	}

	@Test
	public void testCreateCampaign() {
		assertEquals(0, mongoDbService.countCampaigns());

		Campaign campaignWales = new Campaign();
		campaignWales.setTitle("Save the whales");
		campaignWales.setDescription("Let us save the whales");
		campaignWales.setExternalLink("http://...");

		mongoDbService.createCampaign(campaignWales);
		assertEquals(1, mongoDbService.countCampaigns());

		List<Campaign> allCampaigns = mongoDbService.findAllCampaigns();
		Campaign campaignFromDb = allCampaigns.get(0);
		assertNotNull(campaignFromDb.getId());
		assertEquals("Save the whales", campaignFromDb.getTitle());
		assertEquals("Let us save the whales", campaignFromDb.getDescription());
		assertEquals("http://...", campaignFromDb.getExternalLink());

		Campaign campaignPeople = new Campaign();
		campaignPeople.setTitle("Better people");
		mongoDbService.createCampaign(campaignPeople);
		assertEquals(2, mongoDbService.countCampaigns());
	}
	
	@Test
	public void testFindCampaignById() {
		BasicDBObject camp = new BasicDBObject();
		camp.put("title", "test-title");
		DBCollection campaigns = database.getCollection("campaigns");
		campaigns.insert(camp);
		Campaign campaign = mongoDbService.findCampaignById(camp.getString("_id"));
		assertEquals("test-title", campaign.getTitle());
	}

	@Test
	public void testCreateQrCode() {
		Campaign campaignA = new Campaign();
		campaignA.setTitle("Save the whales");
		campaignA.setDescription("Let us save the whales");
		campaignA.setExternalLink("http://...");
		mongoDbService.createCampaign(campaignA);
		
		Campaign campaignB = new Campaign();
		campaignB.setTitle("Don't save the whales");
		campaignA.setDescription("Let us not save the whales");
		mongoDbService.createCampaign(campaignB);
		
		// no QrCodes for the campaign
		List<QrCode> allQrCodesA = mongoDbService.findQrCodesForCampaign(campaignA.getId());
		assertEquals(0, allQrCodesA.size());
		List<QrCode> allQrCodesB = mongoDbService.findQrCodesForCampaign(campaignB.getId());
		assertEquals(0, allQrCodesB.size());
		
		QrCode qrCode = new QrCode();
		qrCode.setImageData("abcdefgh");
		mongoDbService.createQrCode(campaignA, qrCode);

		// one QrCodes for the campaign A
		allQrCodesA = mongoDbService.findQrCodesForCampaign(campaignA.getId());
		assertEquals(1, allQrCodesA.size());
		allQrCodesB = mongoDbService.findQrCodesForCampaign(campaignB.getId());
		assertEquals(0, allQrCodesB.size());
		
		QrCode qrCodeOfCampaignA = allQrCodesA.get(0);
		assertNotNull(qrCodeOfCampaignA.getId());
		assertEquals(campaignA.getId(), qrCodeOfCampaignA.getCampaignId());
		assertEquals("abcdefgh", qrCodeOfCampaignA.getImageData());
	}
	
	@Test
	public void testFindQrCodeById() {
		BasicDBObject qrCode = new BasicDBObject();
		qrCode.put("imageData", "abcdefgh");
		qrCode.put("campaignId", "123456");
		qrCode.put("latitude", 12345.67);
		qrCode.put("longitude", 98765.43);
		qrCode.put("accuracy", 10);
		
		DBCollection qrCodes = database.getCollection("qrCodes");
		qrCodes.insert(qrCode);
		QrCode campaign = mongoDbService.findQrCodeById(qrCode.getString("_id"));
		assertEquals("abcdefgh", campaign.getImageData());
		assertEquals("123456", campaign.getCampaignId());
		assertEquals(new Double(12345.67), campaign.getLatitude());
		assertEquals(new Double(98765.43), campaign.getLongitude());
		assertEquals(new Integer(10), campaign.getAccuracy());
	}
}
