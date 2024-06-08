package com.bcms.apache.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;

@Configuration
public class MyKafkaErrorHandler {

    private final Logger log = LoggerFactory.getLogger(MyKafkaErrorHandler.class);

    @Bean
    public KafkaListenerErrorHandler validationErrorHandler() {
        return (m, e) -> {
            //em caso de erro a mensagem é comitada.
            log.info("Ocorreu o seguinte erro [{}] ao deserializar a mensagem [{}]", e.getCause().getLocalizedMessage(), m.getPayload().toString());
            return null; //essa resposta pode ser uasada por um @SendTo, será visto em outro exemplo
        };
    }
}
