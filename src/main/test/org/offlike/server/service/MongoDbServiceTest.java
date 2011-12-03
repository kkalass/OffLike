package org.offlike.server.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.offlike.server.data.Campaign;

public class MongoDbServiceTest {

	@Test
	public void testCreateCampaign() {
		MongoDbService mongoDbService = new MongoDbService();
		assertEquals(0, mongoDbService.countCampaigns());

		Campaign campaignWales = new Campaign();
		campaignWales.setTitle("Save the whales");

		mongoDbService.createCampaign(campaignWales);
		assertEquals(1, mongoDbService.countCampaigns());

		List<Campaign> allCampaigns = mongoDbService.findAllCampaigns();
		Campaign campaignFromDb = allCampaigns.get(0);
		assertNotNull(campaignFromDb.getId());
		assertEquals("Save the whales", campaignFromDb.getTitle());

		Campaign campaignPeople = new Campaign();
		campaignPeople.setTitle("Better people");
		mongoDbService.createCampaign(campaignPeople);
		assertEquals(2, mongoDbService.countCampaigns());
	}
}
