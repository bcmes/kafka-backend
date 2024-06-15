package com.bcms.apache.configure;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class MyRoutingKafkaTemplate {

    @Bean
    public RoutingKafkaTemplate routingTemplate(
            GenericApplicationContext genericApplicationContext,
            ProducerFactory<Object, Object> producerFactory
    ) {
        // Producer 1
        // Clone the PF with a different Serializer, register with Spring for shutdown
        Map<String, Object> mapProperties = new HashMap<>(producerFactory.getConfigurationProperties());
        mapProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        DefaultKafkaProducerFactory<Object, Object> byteArraySerializerKafkaProducerFactory = new DefaultKafkaProducerFactory<>(mapProperties);
        genericApplicationContext.registerBean(
                "byteArraySerializerKafkaProducerFactory",
                DefaultKafkaProducerFactory.class,
                () -> byteArraySerializerKafkaProducerFactory
        );
        // Producer 2
        Map<String, Object> mapProperties2 = new HashMap<>(producerFactory.getConfigurationProperties());
        mapProperties2.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        DefaultKafkaProducerFactory<Object, Object> jsonSerializerKafkaProducerFactory = new DefaultKafkaProducerFactory<>(mapProperties2);
        genericApplicationContext.registerBean(
                "jsonSerializerKafkaProducerFactory",
                DefaultKafkaProducerFactory.class,
                () -> jsonSerializerKafkaProducerFactory
        );

        Map<Pattern, ProducerFactory<Object, Object>> mapRouter = new LinkedHashMap<>();
        mapRouter.put(Pattern.compile("topicX"), byteArraySerializerKafkaProducerFactory);
        mapRouter.put(Pattern.compile("topicY"), jsonSerializerKafkaProducerFactory);
        mapRouter.put(Pattern.compile(".+"), producerFactory); // Default PF with StringSerializer
        return new RoutingKafkaTemplate(mapRouter); //defini 3 producers.
    }
}
//Obs.: Quando vc envia, vc está serializando (pegando o objeto e transformando em um array de bits).