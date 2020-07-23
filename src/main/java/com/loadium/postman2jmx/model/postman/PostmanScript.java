package com.loadium.postman2jmx.model.postman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.loadium.postman2jmx.model.deserializer.ExecDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanScript {

    @JsonProperty("type")
    private String type;

    @JsonProperty("exec")
    private List<String> execs = new ArrayList<>();

    public PostmanScript() {
    }

    public PostmanScript(String type, List<String> execs) {
        this.type = type;
        this.execs = execs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getExec() {
        return execs;
    }

    public void setExec(List<String> execs) {
        this.execs = execs;
    }
}
