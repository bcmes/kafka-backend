package com.bcms.apache.producer;

import com.bcms.apache.payload.MyOtherPayload;
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

    private final Logger LOG = LoggerFactory.getLogger(MyProducer.class);
    private final String topicName = "topic1";

    @Bean
//    @DependsOn("myKafkaListener")
    public CompletableFuture<SendResult<String, MyPayload>> myKafkaProducer(KafkaTemplate<String, MyPayload> template) {
        return template.send(topicName, new MyPayload("Bruno", 41)).whenComplete((SendResult, exception) -> {
            if (exception == null) {
                LOG.info("=>mensagem enviada com sucesso: {}", SendResult);
            } else {
                LOG.info("=>falha no envio da mensagem: {}", exception.getLocalizedMessage());
            }
        });
    }

    /**
     * Outro producer para o mesmo tópico com um payload diferente.
     */
    @Bean
    public CompletableFuture<SendResult<String, MyOtherPayload>> myKafkaProducer2(KafkaTemplate<String, MyOtherPayload> template) {
        return template.send(topicName, new MyOtherPayload(true, 'M')).whenComplete((SendResult, exception) -> {
            if (exception == null) {
                LOG.info("=>mensagem enviada com sucesso: {}", SendResult);
            } else {
                LOG.info("=>falha no envio da mensagem: {}", exception.getLocalizedMessage());
            }
        });
    }
}
