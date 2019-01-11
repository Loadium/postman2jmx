package com.loadium.postman2jmx.model.jmx;

import org.apache.jorphan.collections.HashTree;

public class JmxFile {
    byte[] data;
    HashTree jmxTree;

    public JmxFile(byte[] data, HashTree jmxTree) {
        this.data = data;
        this.jmxTree = jmxTree;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public HashTree getJmxTree() {
        return jmxTree;
    }

    public void setJmxTree(HashTree jmxTree) {
        this.jmxTree = jmxTree;
    }
}
