package com.bcms.apache.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@Configuration
public class MyKafkaErrorHandler {

    private final Logger log = LoggerFactory.getLogger(MyKafkaErrorHandler.class);

    @Bean
    public KafkaListenerErrorHandler validationErrorHandler1() {
        return (m, e) -> {
            //em caso de erro a mensagem é comitada.
            log.info("Ocorreu o seguinte erro [{}] ao deserializar a mensagem [{}]", e.getCause().getLocalizedMessage(), m.getPayload().toString());
            return null; //essa resposta pode ser uasada por um @SendTo, será visto em outro exemplo
        };
    }

    @Bean
    public KafkaListenerErrorHandler validationErrorHandler2() {
        return (m, e) -> {
            //em caso de erro a mensagem é comitada.
            log.info("Ocorreu o seguinte erro ao deserializar a mensagem.: {}", e.getCause().getLocalizedMessage());
            return MessageBuilder.withPayload(m.getPayload().toString())
                    .setHeader(KafkaHeaders.TOPIC, "topic3")
                    .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
                    .setHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString())
                    .setHeader("someOtherHeader", "someValue")
                    .build();
        };
    }
}
