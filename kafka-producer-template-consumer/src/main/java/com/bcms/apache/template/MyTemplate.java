package com.bcms.apache.template;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Configuration
public class MyTemplate {

    private final Logger log = LoggerFactory.getLogger(MyTemplate.class);

    /**
     * Produzindo uma mensagem..
     */
    @Bean
    public CompletableFuture<SendResult<String, String>> myKafkaProducer(KafkaTemplate<String, String> template) {
        log.info("Mensagem publicada com sucesso no topic1.");
        return template.send("topic1", "message-test-1");
    }

    /**
     * Observe que o KafkaTemplate comumente usado para produzir, pode ser usado para consumir.
     * Mas para o consumer abaixo funcionar, é preciso definir um bean de um consumerFactory. Como não defini o bean
     *    esse exemplo não funciona.
     */
    @Bean
    public Void myKafkaConsumer(KafkaTemplate<String, String> template) {
        //esse .receive() tem 4 sobrecargas.
        ConsumerRecord<String, String> record = template.receive("topic1", 0, 0, Duration.ofSeconds(10));
        log.info("Mensagem[{}] consumida no topic1, partition 0, offset 0.", record);
        return null;
    }
}
