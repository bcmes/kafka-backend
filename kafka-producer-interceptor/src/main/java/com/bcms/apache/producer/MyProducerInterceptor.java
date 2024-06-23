package com.bcms.apache.producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

//veja que ele nao precisa ser um bean, pois apenas será setado nos .properties
public class MyProducerInterceptor implements ProducerInterceptor<String, String> {

    private final Logger log = LoggerFactory.getLogger(MyProducerInterceptor.class);

    //estou interceptando e manipulando o record, antes do envio ao broker.
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        log.info("Mensagem interceptada antes do envio ao broker. Message[{}]", producerRecord);
        producerRecord.headers().add("correlationId", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        //TODO Ao receber a resposta do servidor, ou a resposta de qualquer chamada do usuário, antes do producer fazer a confirmação.
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
/**
 * Se você tiver vários interceptores de produtor gerenciados pelo Spring que precisem ser aplicados ao KafkaTemplate,
 * será necessário usar o CompositeProducerInterceptor. O CompositeProducerInterceptor permite que interceptores de
 * produtores individuais sejam adicionados em ordem. Os métodos das implementações subjacentes do ProducerInterceptor
 * são invocados na ordem em que foram adicionados ao CompositeProducerInterceptor.
 */