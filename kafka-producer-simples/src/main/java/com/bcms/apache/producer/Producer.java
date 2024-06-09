package com.bcms.apache.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
public class Producer {

    private final Logger log = LoggerFactory.getLogger(Producer.class);

    /**
     * AO gerar esse bean, o return precisa ser executado, e ao fazer isso, publicamos uma mensagem no Kafka.
     */
    @Bean
    public CompletableFuture<SendResult<String, String>> myKafkaProducer(KafkaTemplate<String, String> template) {
        log.info("Mensagem publicada com sucesso no topic1.");
        return template.send("topic1", "message-test-1");
    }

    /**
     * O send é assincrono = nova thread.
     * '.whenComplete' recebe a confirmação do envio assincronamente.
     */
//    @Bean
//    public Void myKafkaProducer(KafkaTemplate<String, String> template) {
//        //o envio
//        CompletableFuture<SendResult<String, String>> responseFuture = template.send(
//                "topic4",
//                null,
//                //O timestamp do event, se não informado, ele é definido pelo broker.
//                // Esse comportamento ocorre porque o tópico por padrão é configurado como '--config message.timestamp.type=CreateTime'
//                Instant.EPOCH.getEpochSecond(),
//                "key-1",
//                "any-data");
//        //resposta do commit do envio
//        responseFuture.whenComplete((SendResult, exception) -> {
//            if (exception == null) {
//                log.info("mensagem enviada com sucesso: {}", SendResult);
//            } else {
//                log.info("falha no envio da mensagem: {}", exception);
//            }
//        });
//        log.info("Fim do método");
//        return null;
//    }

    /**
     * Este kafkaTemplate.send(..) tem várias sobrecargas, uma delas recebe Message<T> e outra ProducerRecord<K, V>
     */
//    @Bean
//    public Void myKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        //ProducerRecord<String, String> message = new ProducerRecord<>("topic3", null, Instant.EPOCH.getEpochSecond(), "key-1", "any-data");
//        //ou
//        Message<String> message = MessageBuilder
//                .withPayload("any-data")
//                .setHeader(KafkaHeaders.TOPIC, "topic3")
//                .setHeader(KafkaHeaders.KEY, "key-1")
//                .setHeader(KafkaHeaders.TIMESTAMP, Instant.EPOCH.getEpochSecond())
//                .build();
//        //o envio
//        CompletableFuture<SendResult<String, String>> responseFuture = kafkaTemplate.send(message);
//        //commit do envio
//        responseFuture.whenComplete((SendResult, exception) -> {
//            if (exception == null) {
//                log.info("mensagem enviada com sucesso: {}", SendResult);
//            } else {
//                log.info("falha no envio da mensagem: {}", exception);
//            }
//        });
//        log.info("Fim do método");
//        return null;
//    }

    /**
     * Utilizando a interface ProducerListener<K, V> no KafkaTemplate<K, V>
     */
//    @Bean
//    public Void myKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        //Antigamente podiamos implementar diretamente no metodo kafkaTemplate.send(producerRecord).addCallback(...) o ProducerListener<K, V>
//        //nao tente usar kafkaTemplate.send(producerRecord).addCallback(...) pois não existe mais, devido o send retornar um CompletableFuture e não mais um ListenableFuture
//        //Configurando.: Isso aqui poderia ficar em um bean de configuracao para o kafkaTemplate, pois essa config é 'global' para este objeto kafkaTemplate..
//        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
//            @Override
//            public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
//                log.info("mensagem enviada com sucesso. Message=[{}], Metadados=[{}]", producerRecord, recordMetadata);
//            }
//
//            @Override
//            public void onError(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata, Exception exception) {
//                log.info("falha no envio da mensagem. Messgae[{}], Metadados=[{}], Exception=[{}]", producerRecord, recordMetadata, exception.getLocalizedMessage());
//            }
//        });
//        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("topic3", null, Instant.EPOCH.getEpochSecond(), "key-1", "any-data");
//        kafkaTemplate.send(producerRecord);
//        log.info("Fim do método");
//        return null;
//    }

    /**
     * Se gostava do kafkaTemplate.send(producerRecord).addCallback(...), o substituto é:
     */
//    @Bean
//    public Void myKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("topic3", null, Instant.EPOCH.getEpochSecond(), "key-1", "any-data");
//        kafkaTemplate.send(producerRecord)
//                .thenAccept(sendResult -> log.info("mensagem enviada com sucesso. SendResult=[{}]", sendResult))
//                .exceptionally(ex -> {
//                    log.info("falha no envio da mensagem. Exception[{}]", ex.getLocalizedMessage());
//                    return null;
//                });
//        log.info("Fim do método");
//        return null;
//    }

    /**
     * Existe uma forma SINCRONA de enviar e aguardar a resposta do commit. Util para testes
     */
//    @Bean
//    public Void myKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("topic3", null, Instant.EPOCH.getEpochSecond(), "key-1", "any-data");
//        try {
//            kafkaTemplate.send(producerRecord).get(10, TimeUnit.SECONDS); //se nao responder no tempo determinado dá erro
//            //todo handleSuccess(data);
//        }
//        catch (ExecutionException e) {
//            //todo handleFailure(data, record, e.getCause());
//        }
//        catch (TimeoutException | InterruptedException | java.util.concurrent.TimeoutException e) {
//            //todo handleFailure(data, record, e);
//        }
//        log.info("Fim do método");
//        return null;
//    }
}
//RoutingKafkaTemplate : para usar o mesmo producer com varios serializers diferentes, que são selecionados conforme o tópico no envio do KafkaTemplate