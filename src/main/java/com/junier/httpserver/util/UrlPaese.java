package com.junier.httpserver.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class UrlPaese {

    public static Map<String, String> toMap(String url) {
        Map<String, String>map = null;
        if (StringUtils.isBlank(url) == false && url.indexOf("?") > -1 && url.indexOf("=") > -1) {
            url = url.split("\\?")[1];
            System.out.println(url);
            map = new HashMap<String, String>();
            String[] arrTemp = url.split("&");
            for (String str : arrTemp) {
                String[] qs = str.split("=");
                map.put(qs[0], qs[1]);
                System.out.println(qs[0] + " " + qs[1]);
            }

        }
        return map;
    }

    public static String getQueryString(String url, String name){
        return UrlPaese.toMap(url).get(name);
    }
}
