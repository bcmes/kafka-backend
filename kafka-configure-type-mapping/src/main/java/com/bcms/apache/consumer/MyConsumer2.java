package com.bcms.apache.consumer;

import com.bcms.apache.payload.MyOtherPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer2 {

    private final Logger LOG = LoggerFactory.getLogger(MyConsumer2.class);

    /*
     * Veja que produzimos 2 payloads diferentes para o mesmo tópico, e criamos um consumer para cada payload. Isso
     *  implica que todos os payloads serão entregues a cada consumer, e como cada consumer está consumindo só um tipo
     *  especifico, naturalmente receberá o tipo incorreto e dará erro.
     */
    @KafkaListener(id = "groupB", topics = "topic1")
    public void myKafkaListener(MyOtherPayload input) {
        LOG.info("=>O @KafkaListener recebeu a mensagem [{}]", input);
    }
}
