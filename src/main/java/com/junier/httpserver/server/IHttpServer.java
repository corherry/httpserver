package com.junier.httpserver.server;

import java.io.IOException;
import java.net.Socket;

import com.junier.httpserver.model.RequestHeaders;
import com.junier.httpserver.model.ResponseHeaders;

public abstract class IHttpServer {

	private Socket socket;

	private String requestContent;

	private Object data;

	public IHttpServer(){

	}

	public IHttpServer(Socket socket, String requestContent, Object data) {
		this.socket = socket;
		this.requestContent = requestContent;
		this.data = data;
	}

	public void execute() throws IOException {
		RequestHeaders requestHeaders = requestReceive(socket, requestContent);
		ResponseHeaders responseHeaders = requestHandle(requestHeaders, data);
		requestRespond(socket, responseHeaders);
	}

	public abstract RequestHeaders requestReceive(Socket socket, String requestContent);
	
	public abstract ResponseHeaders requestHandle(RequestHeaders requestHeaders, Object data);

	public abstract void requestRespond(Socket socket, ResponseHeaders responseHeaders) throws IOException;
}
