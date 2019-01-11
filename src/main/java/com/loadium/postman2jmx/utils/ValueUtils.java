package com.loadium.postman2jmx.utils;

import java.util.ArrayList;
import java.util.List;

public class ValueUtils {
    private static final String[] patterns = {".environment.set", ".setGlobalVariable", ".setEnvironmentVariable", ".globals.set"};

    public static String value(String value) {
        if (value.contains("{{") && value.contains("}}")) {
            value = value.replace("{{", "${");
            value = value.replace("}}", "}");
        }
        return value;
    }

    public static List<String> extractVariables(List<String> execs) {
        List<String> variables = new ArrayList<>();

        for (String exec : execs) {
            if (exec.contains(patterns[0]) || exec.contains(patterns[1]) || exec.contains(patterns[2]) || exec.contains(patterns[3])) {
                String variable = exec.substring(exec.indexOf("(") + 1, exec.indexOf(","));
                variable = variable.trim().replace("\'", "").replace("\"", "");
                variables.add(variable);
            }
        }
        return variables;

    }
}
