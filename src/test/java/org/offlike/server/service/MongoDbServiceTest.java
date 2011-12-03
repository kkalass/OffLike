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
	public void testFindCampaign() {
		BasicDBObject camp = new BasicDBObject();
		camp.put("title", "test-title");
		DBCollection campaigns = database.getCollection("campaigns");
		campaigns.insert(camp);
		Campaign campaign = mongoDbService.findCampaignById(camp.getString("_id"));
		assertEquals("test-title", campaign.getTitle());
	}

	@Test
	public void testCreateQrCode() {
		Campaign campaign = new Campaign();
		campaign.setTitle("Save the whales");
		campaign.setDescription("Let us save the whales");
		campaign.setExternalLink("http://...");
		mongoDbService.createCampaign(campaign);
		
		// no QrCodes for the campaign
		List<QrCode> allQrCodes = mongoDbService.findQrCodesForCampaign(campaign.getId());
		assertEquals(0, allQrCodes.size());
		
		QrCode qrCode = new QrCode();
		qrCode.setImageData("abcdefgh");
		mongoDbService.createQrCode(campaign, qrCode);

		// one QrCodes for the campaign
		allQrCodes = mongoDbService.findQrCodesForCampaign(campaign.getId());
		assertEquals(1, allQrCodes.size());
		
	}
}
