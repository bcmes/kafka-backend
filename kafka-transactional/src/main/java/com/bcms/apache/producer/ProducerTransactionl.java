package com.bcms.apache.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

//@Component
public class ProducerTransactionl {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerTransactionl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Se uma transação estiver ativa, todas as operações do KafkaTemplate executadas no escopo da transação usarão o Producer da transação.
    @Transactional //um kafka-template transactional só funciona em um bloco transactional
    @Bean
    public void process() {
        List<String> things = List.of("value-1","value-2","value-3");
        things.forEach(thing -> this.kafkaTemplate.send("topic1", null, thing, thing));
        anyOperation();//ao ocorrer um erro não tratado, ninguém é enviado ao topic1. Mas a operação é registrada no tópico '__transaction_state'
    }

    private void anyOperation() {
        throw new IllegalArgumentException("Ocorreu um erro após salvar os dados");
    }
}

//Quando há varias entradas e saídas na operação transacional, e vc quer controlar a ordem dos commits, granule com
//métodos internos que possuam seus "@Transactinal", assim os commits ocorrerão na ordem natural de leitura do código.
//Caso contrário, como o código acima, comitará primeiro as operações de "salve no banco de dados" e depois os "disparos
//para o kafka", pois ele empilha e depois desempinha comitando.

//Se a confirmação falhar na transação sincronizada (após a confirmação da transação principal), uma exceção será lançada para o chamador.
// Os aplicativos devem tomar medidas corretivas, se necessário, para compensar a transação primária confirmada.