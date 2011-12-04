package org.offlike.server.service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BitlyServiceTest {

	private String api;
	private String api_name;
	private String url;
	private BitlyUrlService bitlyUrlService;

	@Before
	public void init() {
		api = "R_04d0bd3644180040b49b0fdc4398fe5c";
		api_name = "joecks";
		url = "http://offlike.heroku.com/";

		bitlyUrlService = new BitlyUrlService(api_name, api, url);
	}
	
	//@Test
	public void shortenUrl(){
		String longUrl = bitlyUrlService.getLongUrl("somelongcode");
		String shortUrl = bitlyUrlService.getShortUrl(longUrl);
		assertTrue(longUrl.length() > shortUrl.length());
		System.out.println(longUrl + " : " + shortUrl);
	}

}
