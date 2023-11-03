package com.example.kfk;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.Metadata;
import org.apache.kafka.clients.NetworkClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KfkApplication {

    public static void main(String[] args) {
        String shenmm = "";
        String chenmq = shenmm;
        KafkaClient client = null;
        SpringApplication.run(KfkApplication.class, args);
    }

}
