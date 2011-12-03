package org.offlike.server;

import org.joda.time.DateTime;
import org.offlike.server.data.Campaign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;

@org.springframework.stereotype.Controller
public class Controller {

	@RequestMapping("/")
	public ModelAndView hello(){
		return new ModelAndView("index",  ImmutableMap.<String, Object>of("name", "Test User", "Datum", new DateTime()));
	}
	
	@RequestMapping("/test.json")
	@ResponseBody
	public Campaign test(){
		Campaign camp = new Campaign();
		camp.setTitle("test");
		return camp;
	}
}
