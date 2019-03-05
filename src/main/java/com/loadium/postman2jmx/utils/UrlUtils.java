package com.loadium.postman2jmx.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {
    private static String URL_VARIABLE_PATTERN = "\\$\\{(.*?)}";

    private static Pattern pattern = Pattern.compile(URL_VARIABLE_PATTERN);


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

    public static boolean isVariableUrl(String url) {
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }

    public static String getVariableUrl(String url) {
        if (url.contains("/")) {
            return url.substring(0, url.indexOf("/"));
        } else if (url.contains("}")) {
            return url.substring(0, url.indexOf("}"));
        }
        return url;
    }

    public static String getVariablePath(String url) {
        return url.substring(url.lastIndexOf("}") + 1);
    }
}
