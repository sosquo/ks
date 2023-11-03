package com.example.kfk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Slf4j
public class CronTimer {

    /**
     * @KafkaListener注解所标注的方法并不会在IOC容器中被注册为Bean， 而是会被注册在KafkaListenerEndpointRegistry中，
     * 而KafkaListenerEndpointRegistry在SpringIOC中已经被注册为Bean
     **/
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Autowired
    private ConsumerFactory consumerFactory;

    /**
     * 监听工厂设置禁止KafkaListener自启动
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory delayContainFactory() {
        ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory);
        //禁止KafkaListener自启动
        containerFactory.setAutoStartup(false);
        return containerFactory;
    }

    @Scheduled(cron = "10 * * * * *")
    public void startListener() {
        MessageListenerContainer listenerConsumer = registry.getListenerContainer("myTimerConsumer");
        if (!listenerConsumer.isRunning()) {
            log.info("开启myTimerConsumer 监听器");
            listenerConsumer.start();
        }
        if (listenerConsumer.isContainerPaused()) {
            log.info("恢复myTimerConsumer 监听器");
            listenerConsumer.resume();
        }
    }

    @Scheduled(cron = "40 * * * * *")
    public void stopListener() {
        MessageListenerContainer listenerConsumer = registry.getListenerContainer("myTimerConsumer");
        if (!listenerConsumer.isContainerPaused()) {
            log.info("暂停myTimerConsumer 监听器");
//            listenerConsumer.stop();
            listenerConsumer.pause();
        }
    }

}
