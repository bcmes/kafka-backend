package com.bcms.apache.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * devido @RetryableTopic automaticamente será criado os tópicos: topic1-dlt, topic1-retry-1000
     */
    @RetryableTopic(attempts = "2", numPartitions = "2", replicationFactor = "3",
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 5000))
    @KafkaListener(id = "groupA", topics = "topic1")
    public void myKafkaListener(String input) throws InterruptedException {
        log.info("O @KafkaListener recebeu uma mensagem [{}]", input);
        Thread.sleep(1000L);
        throw new NoSuchElementException("Registro não encontrado");
    }
}
