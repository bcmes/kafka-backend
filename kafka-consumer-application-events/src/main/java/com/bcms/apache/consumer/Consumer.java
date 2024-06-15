package com.bcms.apache.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.event.ConsumerStartingEvent;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * id = define o group-id, nome do container e prefix do nome da thread deste listener.
     * Obs.: O 'consumer' só consome mensagens publicadas após sua 'subscrição no grupo', por padrão.
     */
    @KafkaListener(id = "groupA", topics = "topic1")
    public void myKafkaListener(String input) {
        log.info("O @KafkaListener recebeu uma mensagem [{}]", input);
    }

    /**
     * Durante a operação de consumo dos registros, são disparados diversos eventos, que podem ser consumidos,
     * veja: https://docs.spring.io/spring-kafka/reference/kafka/events.html
     */
    @EventListener
    public void listenKafkaListenerEvents(ConsumerStartingEvent event) {
        log.info("Ouvimos o evento: {}", event);
    }
}