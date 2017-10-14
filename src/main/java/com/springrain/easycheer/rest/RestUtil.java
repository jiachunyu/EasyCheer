package com.springrain.easycheer.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestUtil {
	
	private static HttpHeaders createRestResponseHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);	
		return responseHeaders;
	}

	public static final HttpHeaders REST_RESPONSE_HEADERS = createRestResponseHeaders();
}
