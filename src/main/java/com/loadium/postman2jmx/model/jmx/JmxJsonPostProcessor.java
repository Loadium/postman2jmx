package com.loadium.postman2jmx.model.jmx;

import com.loadium.postman2jmx.model.postman.PostmanEvent;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import org.apache.jmeter.extractor.json.jsonpath.JSONPostProcessor;
import org.apache.jmeter.extractor.json.jsonpath.gui.JSONPostProcessorGui;
import org.apache.jmeter.testelement.TestElement;

import java.util.ArrayList;
import java.util.List;

public class JmxJsonPostProcessor {

    public static JSONPostProcessor newInstance(String name) {
        JSONPostProcessor jsonPostProcessor = new JSONPostProcessor();
        jsonPostProcessor.setProperty(TestElement.GUI_CLASS, JSONPostProcessorGui.class.getName());
        jsonPostProcessor.setProperty(TestElement.TEST_CLASS, JSONPostProcessor.class.getName());
        jsonPostProcessor.setEnabled(true);
        jsonPostProcessor.setName(name);
        jsonPostProcessor.setMatchNumbers("0");
        jsonPostProcessor.setRefNames(name);
        jsonPostProcessor.setJsonPathExpressions("." + name);

        return jsonPostProcessor;
    }

    private static List<JSONPostProcessor> getInstances(List<String> variables) {
        List<JSONPostProcessor> jsonPostProcessors = new ArrayList<>();
        for (String variable : variables) {
            jsonPostProcessors.add(JmxJsonPostProcessor.newInstance(variable));
        }
        return jsonPostProcessors;

    }

    public static List<JSONPostProcessor> getJsonPostProcessors(PostmanItem postmanItem) {
        List<JSONPostProcessor> jsonPostProcessors = new ArrayList<>();
        for (PostmanEvent event : postmanItem.getEvents()) {
            jsonPostProcessors.addAll(JmxJsonPostProcessor.getInstances(event.getVariables()));
        }
        return jsonPostProcessors;
    }
}
