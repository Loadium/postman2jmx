package com.loadium.postman2jmx.utils;

public enum JmxFileAttributesType {

    HASH_TREE("hashTree"),
    HEADER_MANAGER("HeaderManager"),
    HTTP_SAMPLER_PROXY("HTTPSamplerProxy"),
    HTTP_SAMPLER_DOMAIN("HTTPSampler.domain"),
    HTTP_SAMPLER_PORT("HTTPSampler.port"),
    HTTP_SAMPLER_PATH("HTTPSampler.path"),
    HTTP_SAMPLER_METHOD("HTTPSampler.method"),
    HTTP_SAMPLER_ARGUMENTS("HTTPsampler.Arguments"),
    ARGUMENT_VALUE("Argument.value"),
    NAME("name"),
    HTTP_SAMPLER_CONNECT_TIMEOUT("HTTPSampler.connect_timeout"),
    HTTP_SAMPLER_RESPONSE_TIMEOUT("HTTPSampler.response_timeout");


    String text;

    public String getText() {
        return text;
    }


    JmxFileAttributesType(String text) {
        this.text = text;
    }
}