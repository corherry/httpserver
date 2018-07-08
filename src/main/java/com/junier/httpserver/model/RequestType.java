package com.junier.httpserver.model;

public class RequestType {
	
	private int type;
	
	private String url;
	
	public RequestType() {
		
	}

	public RequestType(int type, String url) {
		super();
		this.type = type;
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
