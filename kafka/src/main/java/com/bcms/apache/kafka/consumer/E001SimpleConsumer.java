package com.bcms.apache.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class E001SimpleConsumer {

    /**
     * id = define o group-id, nome do container e prefix do nome da thread deste listener.
     * Obs.: O 'consumer' só consome mensagens publicadas após sua 'subscrição no grupo', por padrão.
     */
//    @KafkaListener(id = "groupA", topics = "topic1")
//    public void listen(String input) {
//        System.out.println(input);
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
}
