package com.loadium.postman2jmx.builder;

import com.loadium.postman2jmx.model.jmx.JmxHTTPSamplerProxy;
import com.loadium.postman2jmx.model.postman.PostmanGraphQLBody;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.config.gui.GraphQLUrlConfigGui;
import org.apache.jmeter.protocol.http.control.gui.GraphQLHTTPSamplerGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.apache.jmeter.testelement.TestElement;

public class JmxGraphQLBodyBuilder extends AbstractJmxBodyBuilder {

    @Override
    public HTTPSamplerProxy buildJmxBody(PostmanItem postmanItem) {

        HTTPSamplerProxy sampler = JmxHTTPSamplerProxy.newInstance(postmanItem);

        if (HTTPConstants.POST.equalsIgnoreCase(sampler.getMethod()) || HTTPConstants.PUT.equalsIgnoreCase(sampler.getMethod()) || HTTPConstants.DELETE.equalsIgnoreCase(sampler.getMethod())) {
            sampler.setPostBodyRaw(true);
            sampler.setFollowRedirects(false);

            // Set GraphQL request parameters
            PostmanGraphQLBody graphql = postmanItem.getRequest().getBody().getGraphql();
            sampler.setProperty(TestElement.GUI_CLASS, GraphQLHTTPSamplerGui.class.getName());
            sampler.setProperty(GraphQLUrlConfigGui.OPERATION_NAME, graphql.getOperationName());
            sampler.setProperty(GraphQLUrlConfigGui.QUERY, graphql.getQuery());
            sampler.setProperty(GraphQLUrlConfigGui.VARIABLES, graphql.getVariables());

            // Add GraphQL arguments
            Arguments arguments = new Arguments();
            if (graphql.getQuery() != null && !graphql.getQuery().isEmpty()) {
                HTTPArgument argument = new HTTPArgument();
                argument.setEnabled(true);
                argument.setAlwaysEncoded(false);
                argument.setAlwaysEncoded(false);
                argument.setMetaData("=");
                argument.setUseEquals(true);
                argument.setValue(graphql.toJsonString());
                arguments.addArgument(argument);
            }
            sampler.setArguments(arguments);
        }

        return sampler;
    }

}
