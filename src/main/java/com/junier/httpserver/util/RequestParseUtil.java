package com.junier.httpserver.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.junier.httpserver.model.RequestHeaders;

public class RequestParseUtil {
	
	public static String getRequestHeadersContent(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder("");
		line = reader.readLine();
		sb.append(line).append("\r\n");
		if(StringUtils.isBlank(line)){
			return null;
		}
		String url = line.substring(line.indexOf('/'), line.lastIndexOf('/') - 5);
		System.out.println("url:"+url);
		System.out.println(url.startsWith("/queryBook"));
		if((url.indexOf(".") == -1) || (url.startsWith("/queryBook"))) {
			System.out.println("dongtai");
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\r\n");
				if (line.indexOf("content-length") != -1) {
					int length = Integer.parseInt(line.split(":")[1].trim());
					int size = 0;
					while (length > size) {
						line = reader.readLine();
						size++;
						length -= line.getBytes().length;
						sb.append(line);
						sb.append("\r\n");
					}
					break;
				}
			}
		}else{
			System.out.println("------------------");
			System.out.println(sb.toString());
			System.out.println("----------------");
			return sb.toString();
		}

		return sb.toString();
	}

	public static RequestHeaders getRequestHeaders(String content) {
		String[] contents = content.split("\\n");
		String url = null;
		if(contents.length > 0 && StringUtils.isBlank(contents[0]) == false) {
			url = contents[0];
			url = url.substring(url.indexOf('/'), url.lastIndexOf('/') - 5);
		}
		if(StringUtils.isBlank(url) == false && url.indexOf(".") != -1) {
			return new RequestHeaders(url);
		}else {
			Map<String, String> params = new HashMap<String, String>();
			for(int i = 0; i < contents.length; i++) {
				String str = contents[i];
				if(str.startsWith("Content-Disposition: form-data;")) {
					str = str.split("name=")[1];
					String key = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
					String value = contents[i + 2].replace("\r", "");
					params.put(key, value);
				}
			}
			return new RequestHeaders(url, params);
		}
		
	}

//	public static String getRequestUrl(Socket socket) throws IOException {
//		
////		InputStream is = socket.getInputStream();
////		InputStreamReader isr = new InputStreamReader(is);
////		BufferedReader reader = new BufferedReader(isr);
//		 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		String line = reader.readLine();
//		System.out.println("RequestParseUtil:url:" + line);
//		if (line == null) {
//			return null;
//		}
//		String resource = line.substring(line.indexOf('/'), line.lastIndexOf('/') - 5);
//		return resource;
//		
//		
//	}
}
