package org.offlike.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	
	@RequestMapping("/test")
	public ModelAndView test(){
		return new ModelAndView("test");
	}
}
