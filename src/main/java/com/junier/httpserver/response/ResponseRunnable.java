package com.junier.httpserver.response;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.junier.httpserver.model.RequestHeaders;
import com.junier.httpserver.model.ResponseHeaders;
import com.junier.httpserver.server.IHttpServer;

public class ResponseRunnable implements Runnable{

	private IHttpServer httpServer;


	public ResponseRunnable(IHttpServer httpServer) {
		this.httpServer = httpServer;
	}

	public void run() {
		try {
			httpServer.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		RequestHeaders requestHeaders = httpServer.requestReceive(socket, requestContent);
//		ResponseHeaders responseHeaders = null;
//		responseHeaders = httpServer.requestHandle(requestHeaders, map);
//
//		try {
//			httpServer.requestRespond(socket, responseHeaders);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
}
