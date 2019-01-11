package com.loadium.postman2jmx.builder;

import com.loadium.postman2jmx.model.jmx.JmxHTTPSamplerProxy;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import com.loadium.postman2jmx.model.postman.PostmanRawBody;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;

public class JmxRawBodyBuilder extends AbstractJmxBodyBuilder {

    @Override
    public HTTPSamplerProxy buildJmxBody(PostmanItem postmanItem) throws Exception {

        HTTPSamplerProxy httpSamplerProxy = JmxHTTPSamplerProxy.newInstance(postmanItem);

        if ("post".equalsIgnoreCase(httpSamplerProxy.getMethod())) {
            httpSamplerProxy.setPostBodyRaw(true);
            Arguments arguments = new Arguments();
            PostmanRawBody raw = postmanItem.getRequest().getBody().getRaw();

            if (raw.getValue() != null && !raw.getValue().isEmpty()) {
                HTTPArgument argument = new HTTPArgument();
                argument.setEnabled(true);
                argument.setAlwaysEncoded(false);
                argument.setMetaData("=");
                argument.setValue(raw.getValue());
                arguments.addArgument(argument);
            }
            httpSamplerProxy.setArguments(arguments);
        }

        return httpSamplerProxy;
    }
}
