package com.bcms.apache.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
public class MyFilter implements RecordFilterStrategy<String, String> {

    private final Logger log = LoggerFactory.getLogger(MyFilter.class);

    //Return true if the record should be discarded.
    //Aqui não deveria disparar exceptions, apenas liberar a mensagem para consumo ou não.
    @Override
    public boolean filter(ConsumerRecord<String, String> consumerRecord) {
        log.info("Registro filtrado {} ", consumerRecord);
        return true;
    }
}
