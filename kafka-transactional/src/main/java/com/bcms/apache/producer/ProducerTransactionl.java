package com.bcms.apache.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class ProducerTransactionl {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerTransactionl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional //um kafka-template transactional só funciona em um bloco transactional
    @Bean
    public void process() {
        List<String> things = List.of("value-1","value-2","value-3");
        things.forEach(thing -> this.kafkaTemplate.send("topic1", null, thing, thing));
        anyOperation();//ao ocorrer um erro não tratado, ninguém é enviado ao topic1. Mas a operação é registrada no tópico '__transaction_state'
    }

    private void anyOperation() {
        throw new IllegalArgumentException("Ocorreu um erro após salvar os dados");
    }
}
