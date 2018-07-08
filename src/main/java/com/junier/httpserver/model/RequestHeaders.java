package com.junier.httpserver.model;

import java.util.Map;

public class RequestHeaders {
	
	private String url;
	
	private Map<String, String> params;
	
	public RequestHeaders() {
		
	}
	
	public RequestHeaders(String url) {
		this.url = url;
	}

	public RequestHeaders(String url, Map<String, String> params) {
		this.url = url;
		this.params = params;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}
