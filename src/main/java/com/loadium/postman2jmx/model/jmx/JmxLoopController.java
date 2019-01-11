package com.loadium.postman2jmx.model.jmx;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.testelement.TestElement;

public class JmxLoopController {

    public static LoopController newInstance() {
        LoopController loopController = new LoopController();
        loopController.setEnabled(true);
        loopController.setLoops(5);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());

        return loopController;
    }
}
