package com.llz.app;

import com.llz.service.ADirectImport;
import com.llz.service.BImportSelector;
import com.llz.service.CBeanRegistrar;
import com.llz.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MySpringBootTest {

    @Autowired
    HelloService helloService;

    @Autowired
    ADirectImport aDirectImport;

    @Autowired
    BImportSelector bImportSelector;

    @Autowired
    CBeanRegistrar cBeanRegistrar;

    @Test
    public void test() {
        String info = helloService.sayHello("敏敏");
        System.out.println("info = " + info);
        aDirectImport.sayHi();
        bImportSelector.sayHi();
        cBeanRegistrar.sayHi();
    }

}
