package com.loadium.postman2jmx.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {
    private static String URL_VARIABLE_PATTERN = "\\$\\{(.*?)}";

    private static String URL_PARTITIONS_PATTERN = "^((http[s]?|ftp):\\/)?\\/?([^:\\/\\s]+)(:([^\\/]*))?((\\/?(?:[^\\/\\?#]+\\/+)*)([^\\?#]*))(\\?([^#]*))?(#(.*))?$";

    private static Pattern pattern = Pattern.compile(URL_VARIABLE_PATTERN);

    private static Pattern urlPartitionPattern = Pattern.compile(URL_PARTITIONS_PATTERN);


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
        Matcher matcher = urlPartitionPattern.matcher(url);
        matcher.matches();
        return matcher.group(3);
    }

    public static String getVariablePath(String url) {
        Matcher matcher = urlPartitionPattern.matcher(url);
        matcher.matches();
        String path = matcher.group(6);
        if (matcher.group(9) != null) {
            path = path + matcher.group(9);
        }
        return path;
    }

    public static String getVariableProtocol(String url) {
        Matcher matcher = urlPartitionPattern.matcher(url);
        matcher.matches();
        return matcher.group(2);
    }

    public static int getVariablePort(String url) {
        Matcher matcher = urlPartitionPattern.matcher(url);
        matcher.matches();
        return matcher.group(5) != null ? Integer.valueOf(matcher.group(5)) : -1;
    }
}
