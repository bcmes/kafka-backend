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
}

//existe um RecordInterceptor para os consumidores, mas tou sem ideia de para que poderia usa-lo no momento !