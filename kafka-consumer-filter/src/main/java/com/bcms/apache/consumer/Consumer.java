package com.bcms.apache.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * id = define o group-id, nome do container e prefix do nome da thread deste listener.
     * Obs.: O 'consumer' só consome mensagens publicadas após sua 'subscrição no grupo', por padrão.
     *
     * A propriedade 'filter' do KafkaListener abaixo, substitui a implementaçãp padrão FilteringMessageListenerAdapter
     *    que é responsável pelo consumo idempotent, em caso de rebalance do Kafka.
     */
    @KafkaListener(id = "groupA", topics = "topic1", filter = "myFilter")
    public void myKafkaListener(String input) {
        log.info("O @KafkaListener recebeu uma mensagem [{}]", input);
    }
}
