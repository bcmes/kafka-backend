package com.bcms.apache.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import selfgenerated.avro.User;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Configuration
public class KafkaProducer {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, User> kafkaTemplate;

    KafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User payload, String topicName) {

        final Message<User> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString()) //isso aqui é uma má prática
                .build();

        final CompletableFuture<SendResult<String, User>> completableFuture = kafkaTemplate.send(message);

        completableFuture.whenComplete((response, exception) -> {
            if (exception == null) {
                System.out.println("sucesso: " + response);
            } else {
                System.out.println("falha: " + exception);
            }
        });

    }

    @Bean
    public String sendMessageTopicKafka() {
        User user = new User();
        user.setName("Bruno");
        logger.info("Produzindo a mensagem: {}", user);
        sendMessage(user, "my-topic-test");
        return "foi";
    }
}
/**
 * flush() só é necessária se você tiver definido a propriedade do produtor linger.ms e quiser enviar imediatamente
 * um lote parcial. Por conveniência, o modelo tem um construtor com um parâmetro autoFlush que faz com que o modelo
 * faça flush() em cada envio.
 *
 * Podemos ter configuração de producer especifico para cada tópico(com regex), implementando o bean RoutingKafkaTemplate.
 *  O RoutingKafkaTemplate é uma extensão do KafkaTemplate do Spring Kafka que fornece suporte para roteamento de
 *  mensagens com base em chaves.
 *  - Por padrão é um producer para todas as threads, isso pode ser mudado com producerPerThread=true
 *      - use closeThreadBoundProducer() para encerrar o producer e excluí-lo do ThreadLocal.
 */