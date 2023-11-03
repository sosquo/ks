package com.example.kfk.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class MyKakfaPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (key instanceof String) {
            String keyStr = (String) key;
            if (keyStr.startsWith("0")) {
                return 0;
            }
            if (keyStr.startsWith("1")) {
                return 1;
            }
            if (keyStr.startsWith("2")) {
                return 2;
            }
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
