package com.loadium.postman2jmx.builder;

import com.loadium.postman2jmx.model.jmx.JmxHTTPSamplerProxy;
import com.loadium.postman2jmx.model.postman.PostmanFormDataBody;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import com.loadium.postman2jmx.model.postman.PostmanRawBody;
import org.apache.jmeter.config.Argument;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;

import java.util.List;

public class JmxFormDataBodyBuilder extends AbstractJmxBodyBuilder {

    @Override
    public HTTPSamplerProxy buildJmxBody(PostmanItem postmanItem) throws Exception {
        HTTPSamplerProxy httpSamplerProxy = JmxHTTPSamplerProxy.newInstance(postmanItem);

        Arguments arguments = new Arguments();
        List<PostmanFormDataBody> formDataList = postmanItem.getRequest().getBody().getFormDataList();

        HTTPArgument argument;
        for(PostmanFormDataBody formData : formDataList) {
            argument = new HTTPArgument();
            argument.setEnabled(true);
            argument.setName(formData.getKey());
            argument.setValue(formData.getValue());
            argument.setMetaData("=");
            argument.setAlwaysEncoded(false);
            argument.setUseEquals(true);
            arguments.addArgument(argument);
        }

        httpSamplerProxy.setArguments(arguments);
        return httpSamplerProxy;
    }
}
