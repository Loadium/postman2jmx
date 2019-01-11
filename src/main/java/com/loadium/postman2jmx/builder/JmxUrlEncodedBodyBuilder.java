package com.loadium.postman2jmx.builder;

import com.loadium.postman2jmx.model.jmx.JmxHTTPSamplerProxy;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import com.loadium.postman2jmx.model.postman.PostmanUrlEncodedBody;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;

import java.util.List;

public class JmxUrlEncodedBodyBuilder extends AbstractJmxBodyBuilder {

    @Override
    public HTTPSamplerProxy buildJmxBody(PostmanItem postmanItem) throws Exception {
        HTTPSamplerProxy httpSamplerProxy = JmxHTTPSamplerProxy.newInstance(postmanItem);

        Arguments arguments = new Arguments();
        List<PostmanUrlEncodedBody> urlEncodes = postmanItem.getRequest().getBody().getUrlEncodes();

        HTTPArgument argument;
        for (PostmanUrlEncodedBody urlEncode : urlEncodes) {
            argument = new HTTPArgument();
            argument.setEnabled(true);
            argument.setName(urlEncode.getKey());
            argument.setValue(urlEncode.getValue());
            argument.setMetaData("=");
            argument.setAlwaysEncoded(false);
            argument.setUseEquals(true);
            arguments.addArgument(argument);
        }

        httpSamplerProxy.setArguments(arguments);
        return httpSamplerProxy;
    }
}
