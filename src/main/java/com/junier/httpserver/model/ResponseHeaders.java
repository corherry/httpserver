package com.junier.httpserver.model;

import java.io.File;

public class ResponseHeaders {
	
	private int statusCode;
	
	private String englishStatusCode;
	
	private String contentType;
	
	private Object data;

	public ResponseHeaders() {
		
	}

	public ResponseHeaders(int statusCode, String englishStatusCode, String contentType) {
		this.statusCode = statusCode;
		this.englishStatusCode = englishStatusCode;
		this.contentType = contentType;
	}

	
	public ResponseHeaders(int statusCode, String englishStatusCode, String contentType, Object data) {
		this.statusCode = statusCode;
		this.englishStatusCode = englishStatusCode;
		this.contentType = contentType;
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getEnglishStatusCode() {
		return englishStatusCode;
	}

	public void setEnglishStatusCode(String englishStatusCode) {
		this.englishStatusCode = englishStatusCode;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object object) {
		this.data = data;
	}

	
}
