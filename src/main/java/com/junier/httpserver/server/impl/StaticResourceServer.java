package com.junier.httpserver.server.impl;

import java.io.*;
import java.net.Socket;

import com.junier.httpserver.util.RequestParseUtil;
import com.junier.httpserver.util.ResponseHeadersUtil;
import com.junier.httpserver.model.RequestHeaders;
import com.junier.httpserver.model.ResponseHeaders;
import com.junier.httpserver.server.IHttpServer;

public class StaticResourceServer extends IHttpServer {


	public StaticResourceServer() {
	}

	public StaticResourceServer(Socket socket, String requestContent, Object data) {
		super(socket, requestContent, data);
	}

	public RequestHeaders requestReceive(Socket socket, String requestContent) {
		return RequestParseUtil.getRequestHeaders(requestContent);
	}

	public ResponseHeaders requestHandle(RequestHeaders requestHeaders, Object data) {
		return ResponseHeadersUtil.findFile(requestHeaders.getUrl());
	}

	public void requestRespond(Socket socket, ResponseHeaders responseHeaders) throws IOException {
		BufferedInputStream bis;
		OutputStream os = socket.getOutputStream();
		int statusCode = responseHeaders.getStatusCode();
		String englishStatusCode = responseHeaders.getEnglishStatusCode();
		os.write(("HTTP/1.1 " + statusCode + " " + englishStatusCode + "\n").getBytes());
		os.write(("Content-Type: " + responseHeaders.getContentType() + ";charset=utf-8\n").getBytes());
		if (statusCode == 302) {
			os.write(("Location: http://" + responseHeaders.getData() + "\n").getBytes());
		}
		os.write("\n".getBytes());

		if (statusCode == 200) {
			bis = new BufferedInputStream(new FileInputStream((File)responseHeaders.getData()));
			byte[] b = new byte[1024];
			int length = -1;
			while ((length = bis.read(b)) != -1) {
				os.write(b, 0, length);
			}
			bis.close();
		}
		socket.close();
		os.flush();
		os.close();
	}

}
