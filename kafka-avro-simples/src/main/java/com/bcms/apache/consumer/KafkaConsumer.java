package com.bcms.apache.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import selfgenerated.avro.User;

@Component
class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(
            topics = {"${spring.kafka.topics.my-topic-test}"}
    )
    void consumeKafkaEvent(@Payload ConsumerRecord<String, User> event) {
        System.out.println("mensagem recebida.");
        logger.info("mensagem recebida.");
        logger.info(String.valueOf(event));
    }
}
/** Para testar, basta disparar uma mensagem via CLI com o comando kafka-console-producer.sh
 * ,com a mensagem definida no avro, exemplo: '{"name":"Bruno", "favorite_number":0, "favorite_color":"Blue"}'
 * Ao puxar a mensagem do kafka, um ConsumerRecord obtem uma serie de informações, inclusive o value(a mensagem)
 * informada.
 * Obs.: Quando disparei a mensagem, o schema-registry não validou o contrato da mensagem. Pude até mandar uma mensagem
 * diferente.
 *  - Como fazer a validacao ao produzir a mensagem.
 *  - Como fazer a validacao ao consumir a mensagem.
 * */