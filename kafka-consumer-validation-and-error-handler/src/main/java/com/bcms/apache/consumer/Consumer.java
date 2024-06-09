package com.bcms.apache.consumer;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Component
@Validated
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * Validação do payload do listener e tratamento de erros
     */
//    @KafkaListener(id = "groupA", topics = "topic1", errorHandler = "validationErrorHandler1")
//    public void myKafkaListener(@Valid MyKafkaPayload input) {
//        log.info("O @KafkaListener recebeu a mensagem [{}]", input);
//    }

    /**
     * Se o KafkaListener retornar, o retorno é redirecionado via @SendTo. Mas se um erro ocorrer na deserializacao,
     *      a mensagem errada é comitada, e o errorHandler captura o erro, e se o errorHandler responder uma mensagem,
     *      ela é dada para o @SendTo fazer o envio..
     */
    @SendTo
    @KafkaListener(id = "groupA", topics = "topic1", errorHandler = "validationErrorHandler2")
    public Message<String> myKafkaListener(@Valid MyKafkaPayload input) {
        log.info("O @KafkaListener recebeu a mensagem [{}]", input);
        return MessageBuilder.withPayload("any-paylod")
                .setHeader(KafkaHeaders.TOPIC, "topic2")
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
                .setHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString())
                .setHeader("someOtherHeader", "someValue")
                .build();
    }
}
