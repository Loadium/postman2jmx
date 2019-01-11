package com.loadium.postman2jmx.model.jmx;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;

public class JmxThreadGroup {

    public static ThreadGroup newInstance(LoopController loopController) {
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        threadGroup.setName("Http URL/API Test");
        threadGroup.setEnabled(true);
        threadGroup.setSamplerController(loopController);
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setScheduler(false);
        threadGroup.setDuration(0);
        threadGroup.setDelay(0);
        threadGroup.setComment("");
        threadGroup.setProperty(ThreadGroup.ON_SAMPLE_ERROR, "continue");
        return threadGroup;
    }

}
