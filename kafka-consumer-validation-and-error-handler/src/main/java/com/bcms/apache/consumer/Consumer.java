package com.bcms.apache.consumer;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * Validação do payload do listener e tratamento de erros
     */
    @KafkaListener(id = "groupA", topics = "topic1", errorHandler = "validationErrorHandler")
    public void myKafkaListener(@Valid MyKafkaPayload input) {
        log.info("O @KafkaListener recebeu a mensagem [{}]", input);
    }
}
