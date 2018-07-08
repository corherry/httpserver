package com.junier.httpserver.util;

import java.io.File;
import java.net.URL;
import java.util.Map;
import com.junier.httpserver.model.ResponseHeaders;

public class ResponseHeadersUtil {
	
	private static final String REDIRECT_FILE = "redirect.properties"; 
	
	public static ResponseHeaders findFile(String filename) {
		ResponseHeaders responseHeaders = null;
		filename = filename.substring(1);
		String fileType = FileUtil.getFileType(filename);
		String contentType = getContentType(fileType);
		URL url = FileUtil.class.getClassLoader().getResource(filename);
		if(url == null) { //not exist file
			url = FileUtil.class.getClassLoader().getResource(REDIRECT_FILE);
			System.out.println(url.getPath());
			if(url == null) {  //exist file
				responseHeaders = new ResponseHeaders(404, "NOT FOUND", contentType);
			}else {
				Map<String, String> ipMap = FileUtil.getRedirectIp(url.getPath());
				if(ipMap.containsKey(filename)) {
					responseHeaders = new ResponseHeaders(302, "OK", contentType, ipMap.get(filename));
				}else {
					responseHeaders = new ResponseHeaders(404, "NOT FOUND", contentType);
				}
			}
			
		}else { //exist file
			File file = new File(url.getPath());
			responseHeaders = new ResponseHeaders(200, "OK", contentType, file);
		}
		return responseHeaders;
	}
	
	public static String getContentType(String fileType) {
		if(fileType.equals("html")) {
			return "text/html;charset=utf-8";
		}else if(fileType.equals("css")) {
			return "text/css";
		}else if(fileType.equals("txt")) {
			return "text/html;charset=utf-8";
		}else {
			return "image/" + fileType;
		}
	}
}
