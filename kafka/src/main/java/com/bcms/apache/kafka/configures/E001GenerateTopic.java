package com.bcms.apache.kafka.configures;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class E001GenerateTopic {

    /**
     * Antigamente era feito através do bean do KafkaAdmin. Por debaixo dos panos, ele usa AdminClient.
     */
//    @Bean
//    public NewTopic topic1() {
//        return TopicBuilder.name("topic3")
//                .partitions(10)
//                .replicas(1)
//                .compact()
//                .build();
//    }

    /**
     * Registrando vários tópicos de uma vez.
     */
    @Bean
    public KafkaAdmin.NewTopics topics456() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("topic01")
                        .partitions(3)
                        .build(),
                TopicBuilder.name("topic02")
                        .partitions(3)
                        .build(),
                TopicBuilder.name("topic03")
                        .partitions(3)
                        .build());
    }

    /**
     * Registrando um bean KafkaAdmin
     */
//    @Bean
//    public KafkaAdmin admin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//
////        Exemplo de como obter um KafkaAdmin:
////        KafkaAdmin kafkaAdmin = new KafkaAdmin(configs);
////        AdminClient client = AdminClient.create(kafkaAdmin.getConfigurationProperties());
////        .... o AdminClient tem uma serie de recursos avançados de administração ...
////        client.close();
//
//        return new KafkaAdmin(configs);
//    }

    /**
     * If the topic is configured to use CREATE_TIME, the default, the user-specified timestamp is recorded
     * (or generated if not specified). If the topic is configured to use LOG_APPEND_TIME,
     * the user-specified timestamp is ignored and the broker adds in the local broker time.
     */

}
