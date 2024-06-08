package com.bcms.apache.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

import java.util.UUID;

@Component
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * Consome mensagens de um tópico e responde para outro tópico
     */
    @KafkaListener(id = "recebe-responde", topics = "topic1")
    @SendTo
    public Message<?> myKafkaListener(String event) {
        log.info("Mensagem [{}] recebida e processada...", event);
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "topic2")
                .setHeader(KafkaHeaders.KEY, "key-1")
                .setHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString())
                .build();
    }
}
