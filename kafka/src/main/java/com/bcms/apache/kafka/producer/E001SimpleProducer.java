package com.bcms.apache.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Configuration
public class E001SimpleProducer {

    private final Logger log = LoggerFactory.getLogger(E001SimpleProducer.class);

    /**
     * Para gerar esse bean, o return precisa ser executado, e ao fazer isso, publicamos uma mensagem no Kafka.
     */
//    @Bean
//    public CompletableFuture<SendResult<String, String>> send(KafkaTemplate<String, String> template) {
//        return template.send("topic1", "message-test-1");
//    }

    /**
     * O send é assincrono = nova thread.
     * '.whenComplete' recebe a confirmação do envio assincronamente.
     */
//    @Bean
//    public Void send(KafkaTemplate<String, String> kafkaTemplate) {
//        //o envio
//        CompletableFuture<SendResult<String, String>> completableFuture = kafkaTemplate.send(
//                "topic3",
//                null,
//                Instant.EPOCH.getEpochSecond(),//definindo o timestamp do event, se não informado, ele é definido pelo broker. Esse comportamento ocorre porque o tópico por padrão configurado como '--config message.timestamp.type=CreateTime'
//                "key-1",
//                "any-data");
//        //commit do envio
//        completableFuture.whenComplete((SendResult, exception) -> {
//            if (exception == null) {
//                log.info("mensagem enviada com sucesso: {}", SendResult);
//            } else {
//                log.info("falha no envio da mensagem: {}", exception);
//            }
//        });
//        log.info("Fim do método");
//        return null;
//    }

    /**
     * Este kafkaTemplate.send(..) tem várias sobrecargas, uma delas recebe Message<T> e outra ProducerRecord<K, V>
     */
    @Bean
    public Void send(KafkaTemplate<String, String> kafkaTemplate) {
        //ProducerRecord<String, String> message = new ProducerRecord<>("topic3", null, Instant.EPOCH.getEpochSecond(), "key-1", "any-data");
        //ou
        Message<String> message = MessageBuilder
                .withPayload("any-data")
                .setHeader(KafkaHeaders.TOPIC, "topic3")
                .setHeader(KafkaHeaders.KEY, "key-1")
                .setHeader(KafkaHeaders.TIMESTAMP, Instant.EPOCH.getEpochSecond())
                .build();
        //o envio
        CompletableFuture<SendResult<String, String>> completableFuture = kafkaTemplate.send(message);
        //commit do envio
        completableFuture.whenComplete((SendResult, exception) -> {
            if (exception == null) {
                log.info("mensagem enviada com sucesso: {}", SendResult);
            } else {
                log.info("falha no envio da mensagem: {}", exception);
            }
        });
        log.info("Fim do método");
        return null;
    }
}
