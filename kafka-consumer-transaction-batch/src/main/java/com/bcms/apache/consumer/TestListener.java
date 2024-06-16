package com.bcms.apache.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener {

    private final Logger log = LoggerFactory.getLogger(TestListener.class);

    @KafkaListener(id = "batchRecordAdapter", topics = "topic1")
    public void listener(MyRecord data) {
        //Só colocar um List<?> acima, não faz o Listener receber em lote. Continua recebendo um registro por vez.
        // Vc deve habilitar o recebimento em lote no factory com factory.setBatchListener(true)
        //Como estamos usando o novo recurso de 'BatchToRecordAdapter' podemos receber um a um os registros do lote,
        // para melhor tratamento dos itens do lote em caso de erro, assim não precisamos reprocessar o lote inteiro.
        //Funciona assim: O lote inteiro é recebido, mas o listener recebe um a um os registros, e os registros com erro
        // podem ser imediatamente tratados (enviando para um novo tópico, etc..) e assim o lote inteiro é comitado no fim
        // do processo.
        if ("Don".equals(data.name())) {
            throw new RuntimeException("reject partial");
        }
        log.info("Evento processado com sucesso {}", data);
    }

}
