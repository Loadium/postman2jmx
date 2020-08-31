package com.loadium.postman2jmx.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueUtils {
    // TODO: replace substring(...) solution for matching with (safer) regex matching, using java matcher groups
    private static final String[] patterns = {".environment.set", ".setGlobalVariable", ".setEnvironmentVariable", ".globals.set"};

    public static String value(String value) {
        if (value != null && value.contains("{{") && value.contains("}}")) {
            value = value.replace("{{", "${");
            value = value.replace("}}", "}");
        }
        return value;
    }

    public static Map<String, String> extractVariablesWithValues(List<String> execs) {
        Map<String, String> variableAssignments = new HashMap<>(); // TODO: better use SortedMap to preserve order? any advantages as opposed to HashMap?
        for (String exec : execs) {
            if (exec.contains(patterns[0]) || exec.contains(patterns[1]) || exec.contains(patterns[2]) || exec.contains(patterns[3])) {
                String variable = exec.substring(exec.indexOf("(") + 1, exec.indexOf(","));
                variable = variable.trim().replace("\'", "").replace("\"", "");
                String value = exec.substring(exec.indexOf(",") + 1, exec.indexOf(")"));
                value = value.trim();
                variableAssignments.put(variable, value);
            }
        }
        return variableAssignments;
    }
}
