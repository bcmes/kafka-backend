package com.bcms.apache.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Configuration
public class Producer {

    private final Logger log = LoggerFactory.getLogger(Producer.class);

    /**
     * AO gerar esse bean, o return precisa ser executado, e ao fazer isso, publicamos uma mensagem no Kafka.
     */
    @Bean
    public CompletableFuture<SendResult<String, String>> myKafkaProducer(KafkaTemplate<String, String> template) {
        log.info("Publicadando mensagem para o topic1.");
        return template.send("topic1", "message-test-1");
    }
}
