package com.bcms.apache.configure;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyKafkaAdmin {
    //só para ver como é gerar um bean KafkaAdmin básico.
    //When using Spring Boot, a KafkaAdmin bean is automatically registered so you only need the NewTopic do exemplo topic()
    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(configs);
    }


}
