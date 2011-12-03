package org.offlike.server.service;

import org.offlike.server.data.Campaign;
import org.offlike.server.data.Poster;

public class MongoDbService {

	public int getNextCampaignNumber(){
		return 12;
	}
	
	public void createPoster(Campaign campaign) {
		Poster poster = new Poster();
		poster.setCampaignId(campaign.getId());
		
		// store in db
	}
}
