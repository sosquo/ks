package com.llz.service;

public class CBeanRegistrar {

    public void sayHi() {
        System.out.println("3、通过@Import({MyBeanDefinitionRegistry.class}) =》 MyBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar => registerBeanDefinitions =》 registry.registerBeanDefinition(\"CBean\", new RootBeanDefinition(CBeanRegistrar.class)); , 无需其他注解");
    }
}
