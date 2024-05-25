package com.bcms.apache.kafka.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Configuration
public class E001SimpleProducer {

    /**
     * Para gerar esse bean, o return precisa ser executado, e ao fazer isso, publicamos uma mensagem no Kafka.
     */
//    @Bean
//    public CompletableFuture<SendResult<String, String>> send(KafkaTemplate<String, String> template) {
//        return template.send("topic1", "message-test-1");
//    }
}
