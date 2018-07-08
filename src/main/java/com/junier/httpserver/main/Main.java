package com.junier.httpserver.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.junier.httpserver.model.Book;
import org.apache.commons.lang3.StringUtils;

import com.junier.httpserver.model.RequestHeaders;
import com.junier.httpserver.response.ResponseRunnable;
import com.junier.httpserver.server.impl.DynamicResourceServer;
import com.junier.httpserver.server.impl.StaticResourceServer;
import com.junier.httpserver.util.RequestParseUtil;

public class Main {
	  public static void main(String[] args) throws IOException {
	        ServerSocket server = new ServerSocket(5555);
	        System.out.println("Http Server1 has started...");
		  	Map<String, Book> bookMap = new ConcurrentHashMap<String, Book>();
	        while (true) {
	            Socket socket = server.accept();
	            String requestContent = RequestParseUtil.getRequestHeadersContent(socket);
				if(StringUtils.isBlank(requestContent)) {
					System.out.println("请求错误");
					socket.close();
					continue;
				}
	            String url = requestContent.split("\\n")[0];
				url = url.substring(url.indexOf('/'), url.lastIndexOf('/') - 5);
	            System.out.println("requestContent:"+requestContent);
	            if(url.indexOf(".") != -1) {
	            	System.out.println("static");
	            	new Thread(new ResponseRunnable(new StaticResourceServer(socket, requestContent, bookMap))).start();
	            }else{
	            	System.out.println("Dynamic");
	            	new Thread(new ResponseRunnable(new DynamicResourceServer(socket, requestContent, bookMap))).start();
	            }
	        }
	    }
}
