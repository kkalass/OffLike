package org.offlike.server;

import org.offlike.server.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;

@Controller
public class LikeController {

	// TODO count the number of requests,
	private MongoDbService dbService;
 
	@RequestMapping("/like/{id}")
	public ModelAndView like(
			@RequestParam(value = "campaign_name", required=false) String campaignName,
			@RequestParam(value = "lat", required = false) Double lat,
			@RequestParam(value = "lng", required = false) Double lng,
			@RequestParam(value = "accuracy", required = false) Integer accuracy,
			@PathVariable("id") String id) {

		getDbService().activateQrCode(id, lat, lng, accuracy);

		int numCampaigns = getDbService().countCampaigns();
		return new ModelAndView("like", ImmutableMap.<String, Object> of(
				"campaignName", campaignName + " " + numCampaigns));
	}

	public MongoDbService getDbService() {
		return dbService;
	}

	public void setDbService(MongoDbService dbService) {
		this.dbService = dbService;
	}

}
