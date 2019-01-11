package com.loadium.postman2jmx.config;

import org.apache.jmeter.util.JMeterUtils;

import java.io.File;

public class Postman2JmxConfig {

    public void setJMeterHome() throws Exception {
        String path = new File("").getAbsolutePath();
        path = path + "/jmeter";
        JMeterUtils.setJMeterHome(path);
    }


}
