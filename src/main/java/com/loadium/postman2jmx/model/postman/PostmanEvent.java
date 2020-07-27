package com.loadium.postman2jmx.model.postman;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.loadium.postman2jmx.utils.ValueUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanEvent {

    @JsonProperty("listen")
    private String listen;

    @JsonProperty("script")
    private PostmanScript script;

    @JsonIgnore
    private List<String> variables = new ArrayList<>();

    @JsonIgnore
    private List<String> variableValues = new ArrayList<>();

    public PostmanEvent() {
    }

    public PostmanEvent(String listen, PostmanScript script) {
        this.listen = listen;
        setScript(script);
    }

    public String getListen() {
        return listen;
    }

    public void setListen(String listen) {
        this.listen = listen;
    }

    public PostmanScript getScript() {
        return script;
    }

    public void setScript(PostmanScript script) {
        this.script = script;
        Map<String, String> varMappings = ValueUtils.extractVariablesWithValues(script.getExec());
        varMappings.forEach((variable, value) -> {
            this.variables.add(variable);
            this.variableValues.add(value);
        });

    }

    public List<String> getVariables() {
        return variables;
    }

    public List<String> getVariableValues() {
        return variableValues;
    }
}
