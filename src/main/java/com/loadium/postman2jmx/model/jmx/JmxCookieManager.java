package com.loadium.postman2jmx.model.jmx;

import org.apache.jmeter.protocol.http.control.CookieManager;
import org.apache.jmeter.protocol.http.gui.CookiePanel;
import org.apache.jmeter.testelement.TestElement;

public class JmxCookieManager {
    public static CookieManager newInstance() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setProperty(TestElement.GUI_CLASS, CookiePanel.class.getName());
        cookieManager.setProperty(TestElement.TEST_CLASS, CookieManager.class.getName());
        cookieManager.setName("Cookie Manager");
        cookieManager.setEnabled(true);
        return cookieManager;
    }
}
