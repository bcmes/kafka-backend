package com.bcms.apache.kafka.consumer;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Component
@Validated
public class E001SimpleConsumer {

    private final Logger log = LoggerFactory.getLogger(E001SimpleConsumer.class);

    /**
     * id = define o group-id, nome do container e prefix do nome da thread deste listener.
     * Obs.: O 'consumer' só consome mensagens publicadas após sua 'subscrição no grupo', por padrão.
     */
//    @KafkaListener(id = "groupA", topics = "topic1")
//    public void listen(String input) {
//        log.info("O @KafkaListener recebeu a mensagem [{}]", input);
//    }

    /**
     * Para aumentar a taxa de processamento de mensagens de um único tópico e grupo:
     *  Se precisar definir N consumers para o mesmo topic e group, ao duplicar o método, o id causará erros, pois ele
     *  deve ser único. Nestes casos defina o group-ip e dê um id único para cada método.
     */
//    @KafkaListener(id = "groupA1", topics = "topic2", groupId = "groupA")
//    public void listen1(String input) {
//        System.out.println("listen1: " + input);
//    }
//
//    @KafkaListener(id = "groupA2", topics = "topic2", groupId = "groupA")
//    public void listen2(String input) {
//        System.out.println("listen2: " + input);
//    }

    /**
     * Embora a maneira acima funcione, é claro que ninguém faz isso, a forma correta é:
     * Obs.: Aqui pode usar o id, pois o spring cria para vc o id=groupA-0, id=groupA-1
     */
//    @KafkaListener(id = "groupA", topics = "topic2", concurrency = "2")
//    public void listen3(String input) {
//        System.out.println(input);
//    }

    /**
     * Consome mensagens de um tópico e responde para outro tópico
     */
//    @KafkaListener(id = "recebe-responde", topics = "topic01")
//    @SendTo
//    public Message<?> messageReturn(String event) {
//        log.info("Mensagem [{}] recebida e processada...", event);
//        return MessageBuilder.withPayload(event)
//                .setHeader(KafkaHeaders.TOPIC, "topic02")
//                .setHeader(KafkaHeaders.KEY, "key-1")
//                .setHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString())
//                .build();
//    }

    /**
     * Validação do payload do listener e tratamento de erros
     */
//    @KafkaListener(id = "groupA", topics = "topic1", errorHandler = "validationErrorHandler")
//    public void listen(@Valid MyKafkaPayload input) {
//        log.info("O @KafkaListener recebeu a mensagem [{}]", input);
//    }

    /**
     * Se o listener retornar, o retorno é enviado para o tópico do @SendTo
     *
     */
    @SendTo //se um erro ocorrer na deserializacao, a mensagem de errada é comitada, e o errorHandler captura o erro, e se ele responder uma mensagem, ela é dada para o @SendTo fazer o envio..
    @KafkaListener(id = "groupA", topics = "topic1", errorHandler = "validationErrorHandler")
    public Message<String> listen(@Valid MyKafkaPayload input) {
        log.info("O @KafkaListener recebeu a mensagem [{}]", input);
        return MessageBuilder.withPayload("any-paylod")
                .setHeader(KafkaHeaders.TOPIC, "topic2")
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
                .setHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString())
                .setHeader("someOtherHeader", "someValue")
                .build();
    }
}

//existe um RecordInterceptor para os consumidores, mas tou sem ideia de para que poderia usa-lo no momento !