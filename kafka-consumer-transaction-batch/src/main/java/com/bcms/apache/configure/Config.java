package com.bcms.apache.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.adapter.DefaultBatchToRecordAdapter;

@Configuration
public class Config {

    private final Logger log = LoggerFactory.getLogger(Config.class);

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true); //Esse aqui habilita o recebimento em lote.
        factory.setBatchToRecordAdapter(new DefaultBatchToRecordAdapter<>((record, ex) ->  { //os registros do lote que derem erro, são recebidos aqui..
            log.info("record[{}], exception[{}]", record, ex.getCause().getLocalizedMessage());
        }));
        return factory;
    }
}

/**
 * Um mecanismo alternativo para lidar com falhas durante o processamento de um lote, o BatchToRecordAdapter.
 * Quando um ContainerFactory com batchListener=true, e é configurado um BatchToRecordAdapter,
 * o listener é chamado com um registro por vez. Isso permite o tratamento de erros dentro do lote e, ao mesmo tempo,
 * possibilita a interrupção do processamento de todoo o lote, dependendo do tipo de exceção.
 * É fornecido um DefaultBatchToRecordAdapter, que pode ser configurado com um DefaultConsumerRecordRecoverer,
 * como o DeadLetterPublishingRecoverer.
 */