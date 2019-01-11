package com.loadium.postman2jmx.builder;

import com.loadium.postman2jmx.model.jmx.JmxHTTPSamplerProxy;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;

public class JmxEmptyBodyBuilder extends AbstractJmxBodyBuilder {

    @Override
    public HTTPSamplerProxy buildJmxBody(PostmanItem postmanItem) throws Exception {
        return JmxHTTPSamplerProxy.newInstance(postmanItem);
    }
}
