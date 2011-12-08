package org.offlike.server;

import java.util.Collection;

import org.offlike.server.data.Campaign;
import org.offlike.server.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private MongoDbService _dbService;
	
	@RequestMapping("/")
	public ModelAndView hello(){
		String currentUserId = UserContextHolder.getCurrentUserId();
		Collection<Campaign> campaigns = currentUserId == null ? ImmutableList.<Campaign>of() : _dbService.findCampaignsByOwnerId(currentUserId);
		
		return new ModelAndView("index",  ImmutableMap.<String, Object>of("campaigns", campaigns));
	}
	
	@RequestMapping("/test.json")
	@ResponseBody
	public Campaign test(){
		Campaign camp = new Campaign();
		camp.setTitle("test");
		return camp;
	}
}
