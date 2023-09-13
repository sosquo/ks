package com.llz.service;

import com.llz.prop.HelloProperties;


public class HelloService {

    HelloProperties helloProperties;

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name) {
        return helloProperties.getPrefix() + " -- " + name + " -- " + helloProperties.getSuffix();
    }

}
