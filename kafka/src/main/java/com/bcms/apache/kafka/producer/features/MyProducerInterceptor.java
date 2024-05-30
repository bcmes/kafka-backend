package com.bcms.apache.kafka.producer.features;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

//veja que ele nao precisa ser um bean, pois apenas será setado nos .properties
public class MyProducerInterceptor implements ProducerInterceptor<String, String> {

    //estou interceptando e manipulando o record, antes do envio ao broker.
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        producerRecord.headers().add("correlationId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        //TODO No implementation
    }

    @Override
    public void close() {
        //TODO No implementation
    }

    @Override
    public void configure(Map<String, ?> map) {
        //TODO No implementation
    }
}
