package com.loadium.postman2jmx.utils;

import java.util.Collection;
import java.util.Map;

public class TypeUtils {

    public static boolean isLong(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static String join(String[] strs, String joinChar) {
        if (isNotEmpty(strs) && isNotEmpty(joinChar)) {
            String result = "";
            for (String str : strs) {
                result += str;
                result += joinChar;
            }
            return result.substring(0, result.length() - joinChar.length());
        }
        return "";
    }

    public static String join(Collection<String> strs, String joinChar) {
        if (isNotEmpty(strs) && isNotEmpty(joinChar)) {
            String result = "";
            for (String str : strs) {
                result += str;
                result += joinChar;
            }
            return result.substring(0, result.length() - joinChar.length());
        }
        return "";
    }
}
