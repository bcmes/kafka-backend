package com.bcms.apache.kafka.consumer.features;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;


@Configuration
public class MyConsumerInterceptor {

    private final Logger log = LoggerFactory.getLogger(MyConsumerInterceptor.class);

    //implementando RecordInterceptor
    public RecordInterceptor<String, String> recordInterceptor() { //tambem existe o BatchInterceptor
        return new RecordInterceptor<String, String>() { //há outros métodos úteis...
            @Override
            public ConsumerRecord<String, String> intercept(ConsumerRecord<String, String> record, Consumer<String, String> consumer) {
                log.info("Interceptamos o registro [{}] antes do @KafkaListener", record.value());
                return record; //se retornar null, o registro é comitado e não é enviado ao @KafkaListener
            }

            @Override
            public void afterRecord(ConsumerRecord<String, String> record, Consumer<String, String> consumer) {
                log.info("Interceptamos o registro [{}] após do @KafkaListener", record.value());
                RecordInterceptor.super.afterRecord(record, consumer);
            }
        };
    }

    //vai funcionar como global para todos os @KafkaListener, vc informando ou não a propriedade do @KafkaListener(containerFactory = "kafkaListenerContainerFactory")
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        factory.setRecordInterceptor(recordInterceptor());
//        return factory;
//    }
}