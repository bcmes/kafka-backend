package com.bcms.apache.producer;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerTest {

    @Bean
    public ApplicationRunner producingSeveralDifferentEvents(RoutingKafkaTemplate routingKafkaTemplate) {
        return args -> {
            routingKafkaTemplate.send("topic1", "dados-em-string");
            routingKafkaTemplate.send("topicX", "dados-em-bytes".getBytes());
            routingKafkaTemplate.send("topicY", new MyKafkaPayload("Jhon", 90));
        };
    }
}
