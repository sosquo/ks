package com.example.kfk.util;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.TopicPartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class MyKafkaUtil {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    AdminClient adminClient;

    @PostConstruct
    public void setAdminClient() {
        HashMap<String, Object> conf = new HashMap<>();
        conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        adminClient = KafkaAdminClient.create(conf);
    }

    public void createTopics(Collection<String> topics, int numPartitions, short replicationFactor) {
        List<NewTopic> newTopics = new ArrayList<>();
        for (String topic : topics) {
            newTopics.add(new NewTopic(topic, Optional.empty(), Optional.empty()));
        }
         adminClient.createTopics(newTopics);

    }

    public void deleteTopics(Collection<String> topics) {
        adminClient.deleteTopics(topics);
    }

    public String detailTopics(Collection<String> topics) {
        AtomicReference<String> infos = new AtomicReference<String>("");
        try {
            adminClient.describeTopics(topics).all().get().forEach((topic, topicDescribe) -> {
                List<TopicPartitionInfo> partitions = topicDescribe.partitions();
                for (TopicPartitionInfo partition : partitions) {
                    infos.set(infos.get() + topic + "-" + partition.partition() + "\r\n");
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return infos.get();
    }

    public List<String> getAllTopics() {
        try {
            return adminClient.listTopics().listings().get().stream().map(TopicListing::name).collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
