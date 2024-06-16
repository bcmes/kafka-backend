package com.bcms.apache.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


//@Component
public class ProducerLocalTransactional {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerLocalTransactional(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        test();
    }

    //KafkaTemplate Local Transactions: Exemplo de como realizar sends transacionais em um bloco não transacional.
    //Se vc já estivesse em um bloco transacional, uma nova transação "aninhada" seria criada.
    public boolean test() {
        boolean result = Boolean.TRUE.equals(
                this.kafkaTemplate.executeInTransaction(t -> {
                    t.send("topic1", "thing1");
                    t.send("topic2", "thing2");
                    throw new IllegalArgumentException("deu pau"); //ao lançar a exceção, nenhuma mensagem chegou ao seu tópico de destino.
//                    return true;
                })
        );
        return result;
    }
}

//Para permitir que o template também seja executado em blocos não transacionais:
//Você pode definir a propriedade allowNonTransactional do template como true. Nesse caso, o modelo permitirá que a
// operação seja executada sem uma transação, chamando o método createNonTransactionalProducer() do ProducerFactory;
// o produtor será armazenado em cache ou vinculado a thread, normalmente para reutilização.