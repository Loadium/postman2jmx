package com.loadium.postman2jmx.builder;

import com.loadium.postman2jmx.config.Postman2JmxConfig;
import com.loadium.postman2jmx.exception.NoPostmanCollectionItemException;
import com.loadium.postman2jmx.exception.NullPostmanCollectionException;
import com.loadium.postman2jmx.model.jmx.*;
import com.loadium.postman2jmx.model.postman.PostmanCollection;
import com.loadium.postman2jmx.model.postman.PostmanItem;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.extractor.json.jsonpath.JSONPostProcessor;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;

import java.io.*;
import java.util.List;

public abstract class AbstractJmxFileBuilder implements IJmxFileBuilder {

    protected JmxFile buildJmxFile(PostmanCollection postmanCollection, String jmxOutputFilePath) throws Exception {
        if (postmanCollection == null) {
            throw new NullPostmanCollectionException();
        }

        if (postmanCollection.getItems() == null || postmanCollection.getItems().isEmpty()) {
            throw new NoPostmanCollectionItemException();
        }

        Postman2JmxConfig config = new Postman2JmxConfig();
        config.setJMeterHome();

        // TestPlan
        TestPlan testPlan = JmxTestPlan.newInstance(postmanCollection.getInfo() != null ? postmanCollection.getInfo().getName() : "");

        // ThreadGroup controller
        LoopController loopController = JmxLoopController.newInstance();

        // ThreadGroup
        ThreadGroup threadGroup = JmxThreadGroup.newInstance(loopController);

        // Create TestPlan hash tree
        HashTree testPlanHashTree = new ListedHashTree();
        testPlanHashTree.add(testPlan);

        // Add ThreadGroup to TestPlan hash tree
        HashTree threadGroupHashTree = testPlanHashTree.add(testPlan, threadGroup);

        // Add Http Cookie Manager
        threadGroupHashTree.add(JmxCookieManager.newInstance());

        for (PostmanItem item : postmanCollection.getItems()) {
            IJmxBodyBuilder bodyBuilder = JmxBodyBuilderFactory.getJmxBodyBuilder(item);
            HTTPSamplerProxy httpSamplerProxy = bodyBuilder.buildJmxBody(item);

            // Add Http Sampler to ThreadGroup hash tree
            HashTree httpSamplerHashTree = threadGroupHashTree.add(httpSamplerProxy);
            if (!item.getEvents().isEmpty()) {
                List<JSONPostProcessor> jsonPostProcessors = JmxJsonPostProcessor.getJsonPostProcessors(item);
                httpSamplerHashTree.add(jsonPostProcessors);
            }

            // Add HTTP Header Manager (if headers are defined)
            if (!item.getRequest().getHeaders().isEmpty()) {
                HeaderManager headerManager = JmxHeaderManager.newInstance(item.getName(), item.getRequest().getHeaders());
                httpSamplerHashTree.add(headerManager);
            }
        }

        File file = new File(jmxOutputFilePath);
        OutputStream os = new FileOutputStream(file);
        SaveService.saveTree(testPlanHashTree, os);

        InputStream is = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        for (int len = 0; (len = is.read(buffer)) != -1; ) {
            bos.write(buffer, 0, len);
        }
        bos.flush();

        byte[] data = bos.toByteArray();
        JmxFile jmxFile = new JmxFile(data, testPlanHashTree);

        os.close();
        is.close();
        bos.close();

        return jmxFile;
    }

}
