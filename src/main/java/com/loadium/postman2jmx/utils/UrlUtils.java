package com.loadium.postman2jmx.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {

    public static URL getUrl(String httpUrl) throws MalformedURLException {
        if (!httpUrl.startsWith("http") && !httpUrl.startsWith("https")) {
            httpUrl = "http://" + httpUrl;
        }
        return new URL(httpUrl);
    }

    public static String getDomain(URL url) {
        return url.getHost();
    }

    public static String getPath(URL url) {
        String path = url.getPath();
        String queryString = url.getQuery();

        if (TypeUtils.isNotEmpty(queryString)) {
            path = path + "?" + queryString;
        }
        return path;
    }

    public static int getPort(URL url) {
        return url.getPort();
    }

    public static String getProtocol(URL url) {
        return url.getProtocol();
    }
}
