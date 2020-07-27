package com.loadium.postman2jmx.model.postman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.loadium.postman2jmx.utils.ValueUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanQuery {
    private String key;
    private String value;
    private String description;
    private String disabled;


    public PostmanQuery() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = ValueUtils.value(key);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = ValueUtils.value(value);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
