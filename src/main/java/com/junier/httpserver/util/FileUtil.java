package com.junier.httpserver.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {
	
	public static String getfilePath(String filename) {
		URL url = FileUtil.class.getClassLoader().getResource(filename);
		if(url == null) {
			return null;
		}else {
			return url.getPath();
		}
	}
	
	public static String getFileType(String filename) {

		return filename.split("\\.")[1];
		
	}
	
	public static Map<String, String> getRedirectIp(String filename){
		Map<String, String> map = new HashMap<String, String>();
		try {
			File file = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader bf = new BufferedReader(isr);
			String content = null;
			while((content = bf.readLine()) != null) {
				String contents[] = content.split("=");
				map.put(contents[0].trim(), contents[1].trim());
			}
			fis.close();
			isr.close();
			bf.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
