package com.llz.autoconfigure;

import com.llz.prop.HelloProperties;
import com.llz.registry.MyBeanDefinitionRegistry;
import com.llz.selector.MyImportSelector;
import com.llz.service.ADirectImport;
import com.llz.service.CBeanRegistrar;
import com.llz.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(HelloProperties.class)

@Import({ADirectImport.class, MyImportSelector.class, MyBeanDefinitionRegistry.class})
public class MyAutoConfigure {

    @Autowired
    public HelloProperties helloProperties;

    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }
}
