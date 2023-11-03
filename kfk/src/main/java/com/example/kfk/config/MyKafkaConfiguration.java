package com.example.kfk.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class MyKafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<String, String> myKafkaTemplate() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //注意分区器在这里！！！
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyKakfaPartitioner.class);
        return new KafkaTemplate<String, String>(new DefaultKafkaProducerFactory<>(props));
    }

    @Bean
    ConsumerAwareListenerErrorHandler consumerErrorHandler() {
        return (message, exception, consumer) -> {
            System.out.println("自定义消费异常 ！ 捕获异常 = " + exception.getCause().getMessage() + "； 消费到的信息 = " + message.getPayload());
            return null;
        };
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        //被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        //消息过滤器策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            String value = (String)consumerRecord.value();
            if (value.contains("2")) {
                return false;
            }
            //将被过滤
            return true;
        });
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory manalKafkfaListenerContainerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //注意，这里设置手动提交
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(configProps));
        // ack模式：
        //          AckMode针对ENABLE_AUTO_COMMIT_CONFIG=false时生效，有以下几种：
        //          RECORD
        //          每处理一条commit一次
        //          BATCH(默认)
        //          每次poll的时候批量提交一次，频率取决于每次poll的调用频率
        //          TIME
        //          每次间隔ackTime的时间去commit(跟auto commit interval有什么区别呢？)
        //          COUNT
        //          累积达到ackCount次的ack去commit
        //          COUNT_TIME
        //          ackTime或ackCount哪个条件先满足，就commit
        //          MANUAL
        //          listener负责ack，但是背后也是批量上去
        //          MANUAL_IMMEDIATE
        //          listener负责ack，每调用一次，就立即commit
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }


}
