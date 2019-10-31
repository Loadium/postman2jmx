package com.loadium.postman2jmx.builder;

import com.loadium.postman2jmx.exception.UnsupportedJmxFileBuilderException;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import com.loadium.postman2jmx.utils.ResponseBodyMode;

public class JmxBodyBuilderFactory {
    private static JmxRawBodyBuilder jmxRawBodyFileBuilder;
    private static JmxFormDataBodyBuilder jmxFormDataBodyFileBuilder;
    private static JmxUrlEncodedBodyBuilder jmxUrlEncodedBodyFileBuilder;
    private static JmxBinaryBodyBuilder jmxBinaryBodyFileBuilder;
    private static JmxEmptyBodyBuilder jmxEmptyBodyBuilder;


    public static IJmxBodyBuilder getJmxBodyBuilder(PostmanItem postmanItem) throws UnsupportedJmxFileBuilderException {
        String responseBodyMode = null;
        if (postmanItem.getRequest().getBody() != null) {
            responseBodyMode = postmanItem.getRequest().getBody().getMode();
        }

        if (responseBodyMode == null || ResponseBodyMode.EMPTY.getMode().equals(responseBodyMode)) {
            if (jmxEmptyBodyBuilder == null) {
                jmxEmptyBodyBuilder = new JmxEmptyBodyBuilder();
            }
            return jmxEmptyBodyBuilder;
        }

        if (ResponseBodyMode.RAW.getMode().equals(responseBodyMode)) {
            if (jmxRawBodyFileBuilder == null) {
                jmxRawBodyFileBuilder = new JmxRawBodyBuilder();
            }
            return jmxRawBodyFileBuilder;
        } else if (ResponseBodyMode.FORM_DATA.getMode().equals(responseBodyMode)) {
            if (jmxFormDataBodyFileBuilder == null) {
                jmxFormDataBodyFileBuilder = new JmxFormDataBodyBuilder();
            }
            return jmxFormDataBodyFileBuilder;
        } else if (ResponseBodyMode.URL_ENCODED.getMode().equals(responseBodyMode)) {
            if (jmxUrlEncodedBodyFileBuilder == null) {
                jmxUrlEncodedBodyFileBuilder = new JmxUrlEncodedBodyBuilder();
            }
            return jmxUrlEncodedBodyFileBuilder;
        } else if (ResponseBodyMode.FILE.getMode().equals(responseBodyMode)) {
            if (jmxBinaryBodyFileBuilder == null) {
                jmxBinaryBodyFileBuilder = new JmxBinaryBodyBuilder();
            }
            return jmxBinaryBodyFileBuilder;
        } else {
            throw new UnsupportedJmxFileBuilderException("There is no Jxm builder for response body type: " + responseBodyMode);
        }
    }
}
