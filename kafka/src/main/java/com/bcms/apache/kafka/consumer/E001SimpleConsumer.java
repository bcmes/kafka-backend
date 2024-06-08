package com.bcms.apache.kafka.consumer;

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
public class E001SimpleConsumer {

    private final Logger log = LoggerFactory.getLogger(E001SimpleConsumer.class);

    /**
     * Se o listener retornar, o retorno é enviado para o tópico do @SendTo
     *
     */
    @SendTo //se um erro ocorrer na deserializacao, a mensagem de errada é comitada, e o errorHandler captura o erro, e se ele responder uma mensagem, ela é dada para o @SendTo fazer o envio..
    @KafkaListener(id = "groupA", topics = "topic1", errorHandler = "validationErrorHandler")
    public Message<String> listen(@Valid MyKafkaPayload input) {
        log.info("O @KafkaListener recebeu a mensagem [{}]", input);
        return MessageBuilder.withPayload("any-paylod")
                .setHeader(KafkaHeaders.TOPIC, "topic2")
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
                .setHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString())
                .setHeader("someOtherHeader", "someValue")
                .build();
    }
}

//existe um RecordInterceptor para os consumidores, mas tou sem ideia de para que poderia usa-lo no momento !