package com.bcms.apache.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * Para aumentar a taxa de processamento de mensagens de um único tópico e grupo:
     *  Se precisar definir N consumers para o mesmo topic e group, ao duplicar o método, o id causará erros, pois ele
     *  deve ser único. Nestes casos defina o group-ip e dê um id único para cada método.
     *
     *  Obs.: Lembre-se que a quantidade de consumidores deve ser <= ao numero de partições do tópico. Para testar esse
     *     aqui, suba o topic2 com ao menos 2 partições.
     */
//    @KafkaListener(id = "groupA1", topics = "topic2", groupId = "groupA")
//    public void myKafkaListener1(String input) {
//        log.info("myKafkaListener1: {}",  input);
//    }
//
//    @KafkaListener(id = "groupA2", topics = "topic2", groupId = "groupA")
//    public void myKafkaListener2(String input) {
//        log.info("myKafkaListener2: {}",  input);
//    }

    /**
     * Embora a maneira acima funcione, é claro que ninguém faz isso, a forma correta está abaixo.
     * Obs.: Aqui pode usar o id, pois o spring criará para vc dois listeners, o id=groupA-0, id=groupA-1
     *  Você perceberá dois consumers, pelos nomes das threads geradas, "groupA-0-C-1" e o "groupA-1-C-1"
     */
    @KafkaListener(id = "groupA", topics = "topic2", concurrency = "2")
    public void myKafkaListener3(String input) {
        log.info("myKafkaListener3: {}",  input);
    }

}
