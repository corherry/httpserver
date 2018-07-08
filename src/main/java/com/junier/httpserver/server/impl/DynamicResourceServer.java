package com.junier.httpserver.server.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.junier.httpserver.execute.Execute;
import com.junier.httpserver.execute.impl.AddExecute;
import com.junier.httpserver.execute.impl.DeleteExecute;
import com.junier.httpserver.execute.impl.QueryExecute;
import com.junier.httpserver.execute.impl.UpdateExecute;
import com.junier.httpserver.model.Book;
import com.junier.httpserver.model.RequestHeaders;
import com.junier.httpserver.model.ResponseHeaders;
import com.junier.httpserver.server.IHttpServer;
import com.junier.httpserver.util.ReflectUtil;
import com.junier.httpserver.util.RequestParseUtil;
import com.junier.httpserver.util.UrlPaese;

public class DynamicResourceServer extends IHttpServer{

	public DynamicResourceServer() {
	}

	public DynamicResourceServer(Socket socket, String requestContent, Object data) {
		super(socket, requestContent, data);
	}

	public RequestHeaders requestReceive(Socket socket, String requestContent) {
		return RequestParseUtil.getRequestHeaders(requestContent);
	}

	public ResponseHeaders requestHandle(RequestHeaders requestHeaders, Object data){
		Map<String, String> map = requestHeaders.getParams();
		Book book = new Book();

		Execute execute;
		if("/addBook".equals(requestHeaders.getUrl())){
			execute = new AddExecute();
		}else if("/deleteBook".equals(requestHeaders.getUrl())){
			execute = new DeleteExecute();
		}else if("/updateBook".equals(requestHeaders.getUrl())){
			execute = new UpdateExecute();
		}else{
			Map<String, String> paramsMap = UrlPaese.toMap(requestHeaders.getUrl());
			map = paramsMap;
			execute = new QueryExecute();
		}
		if(map == null){
			book = null;
		}else {
			for (String key : map.keySet()) {
				try {
					ReflectUtil.setProperty(book, key, map.get(key));
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		String result = execute.ececute((Map<String, Book>) data, book);
		System.out.println("result:"+result);
		return new ResponseHeaders(200, "OK", "text/html", result);
	}

	public void requestRespond(Socket socket, ResponseHeaders responseHeaders) throws IOException {
		String result = (String)responseHeaders.getData();
		OutputStream os = socket.getOutputStream();
		int statusCode = responseHeaders.getStatusCode();
		String englishStatusCode = responseHeaders.getEnglishStatusCode();
		os.write(("HTTP/1.1 " + statusCode + " " + englishStatusCode + "\n").getBytes());
		os.write(("Content-Type: " + responseHeaders.getContentType() + ";charset=utf-8\n").getBytes());
		os.write("\n".getBytes());
		System.out.println(result.toString());
		os.write((result.toString()+"\n").getBytes());
		os.flush();
		os.close();
		socket.close();
	}

}
