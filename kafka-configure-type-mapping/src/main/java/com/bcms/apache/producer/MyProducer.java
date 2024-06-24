package com.bcms.apache.producer;

import com.bcms.apache.payload.MyPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Configuration
public class MyProducer {

    private final Logger log = LoggerFactory.getLogger(MyProducer.class);

    @Bean
//    @DependsOn("myKafkaListener")
    public CompletableFuture<SendResult<String, MyPayload>> myKafkaProducer(KafkaTemplate<String, MyPayload> template) {
        return template.send("topic1", new MyPayload("Bruno", 41)).whenComplete((SendResult, exception) -> {
            if (exception == null) {
                log.info("=>mensagem enviada com sucesso: {}", SendResult);
            } else {
                log.info("=>falha no envio da mensagem: {}", exception.getLocalizedMessage());
            }
        });
    }
}
