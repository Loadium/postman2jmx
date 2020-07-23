package com.loadium.postman2jmx.model.jmx;

import com.loadium.postman2jmx.model.postman.PostmanEvent;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import org.apache.jmeter.extractor.json.jsonpath.JSONPostProcessor;
import org.apache.jmeter.extractor.json.jsonpath.gui.JSONPostProcessorGui;
import org.apache.jmeter.testelement.TestElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JmxJsonPostProcessor {
    public static String FULL_RESPONSE_PATTERN = "responseBody";
    public static String CHILD_ELEMENT_PATTERN = "^(\\w+)\\.?(.*)$";

    private static Logger logger = LoggerFactory.getLogger(JmxJsonPostProcessor.class.getName());

    public static JSONPostProcessor newInstance(String name, String jsonPathExpression) {
        JSONPostProcessor jsonPostProcessor = new JSONPostProcessor();
        jsonPostProcessor.setProperty(TestElement.GUI_CLASS, JSONPostProcessorGui.class.getName());
        jsonPostProcessor.setProperty(TestElement.TEST_CLASS, JSONPostProcessor.class.getName());
        jsonPostProcessor.setEnabled(true);
        jsonPostProcessor.setName(name);
        jsonPostProcessor.setMatchNumbers("0");
        jsonPostProcessor.setRefNames(name);
        jsonPostProcessor.setJsonPathExpressions(jsonPathExpression);

        return jsonPostProcessor;
    }

    private static List<JSONPostProcessor> getInstances(List<String> variables, List<String> varValues) {
        List<JSONPostProcessor> jsonPostProcessors = new ArrayList<>();
        if (variables.size()!=varValues.size()) {
            logger.error("List of variables does not match the length of its values!");
        }
        for (int i=0; i<variables.size(); i++) {
            String variable = variables.get(i);
            String value = varValues.get(i);
            JSONPostProcessor postProcessor = JmxJsonPostProcessor.newInstance(variable, getPathExpression(value));
            if (!Pattern.compile(CHILD_ELEMENT_PATTERN).matcher(value).find() && !Pattern.compile(FULL_RESPONSE_PATTERN).matcher(value).find()) {
                postProcessor.setDefaultValues(value.replace("\"", ""));
            }
            jsonPostProcessors.add(postProcessor);
        }
        return jsonPostProcessors;

    }

    public static List<JSONPostProcessor> getJsonPostProcessors(PostmanItem postmanItem) {
        List<JSONPostProcessor> jsonPostProcessors = new ArrayList<>();
        for (PostmanEvent event : postmanItem.getEvents()) {
            jsonPostProcessors.addAll(JmxJsonPostProcessor.getInstances(event.getVariables(), event.getVariableValues()));
        }
        return jsonPostProcessors;
    }

    public static String getPathExpression(String postmanExpr) {
        Matcher m;
        m = Pattern.compile(FULL_RESPONSE_PATTERN).matcher(postmanExpr);
        if (m.find()) {
            return "$";
        }
        m = Pattern.compile(CHILD_ELEMENT_PATTERN).matcher(postmanExpr);
        if (m.find()) {
            return m.group(2);
        }
        logger.warn("No pattern for Postman variable assignment found - skip proper translation...");
        return postmanExpr.replace("\"", "");   // TODO: fallback only - should not happen!
    }
}
