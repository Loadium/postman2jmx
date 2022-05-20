package com.loadium.postman2jmx.model.postman;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loadium.postman2jmx.utils.ValueUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostmanGraphQLBody {

    private String operationName;

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    private String query;

    private String variables;

    public PostmanGraphQLBody() {
    }

    public PostmanGraphQLBody(String query, String variables) {
        this.query = query;
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = ValueUtils.value(query);
    }

    public String getVariables() {
        return this.variables;
    }

    public void setVariables(String variables) {
        this.variables = ValueUtils.value(variables);
    }

    public String toJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception ignored) {
        }
        return "";
    }

}
