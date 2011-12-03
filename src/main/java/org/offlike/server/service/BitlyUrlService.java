package org.offlike.server.service;

import static com.rosaloves.bitlyj.Bitly.*;

import com.rosaloves.bitlyj.Url;

public class BitlyUrlService {

	private final String BITLY_SERVICE_NAME;
	private final String BITLY_SERVICE_APICODE;
	private final String OFFLIKE_URL;

	public BitlyUrlService(String bitlyServiceName,String bitlyApiCode,String offlikeBaseUrl) {
		BITLY_SERVICE_NAME = bitlyServiceName;
		BITLY_SERVICE_APICODE = bitlyApiCode;
		OFFLIKE_URL = offlikeBaseUrl;
	}
	
	public String getShortUrl(String urlString) {

		Url url = as(BITLY_SERVICE_NAME, BITLY_SERVICE_APICODE).call(
				shorten(urlString));

		return url.getShortUrl();
	}

	public String getLongUrl(String payload) {
		return OFFLIKE_URL;
	}

}
