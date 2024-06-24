package com.bcms.apache.consumer;

import com.bcms.apache.payload.MyPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    private final Logger log = LoggerFactory.getLogger(MyConsumer.class);

    @KafkaListener(id = "groupA", topics = "topic1")
    public void myKafkaListener(MyPayload input) {
        log.info("=>O @KafkaListener recebeu a mensagem [{}]", input);
    }
}
