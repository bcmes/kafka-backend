package com.bcms.apache.consumer.filter;

//@EnableKafka
//@Configuration
public class FilterConfiguration {

//    private final KafkaProperties kafkaProperties;

//    public FilterConfiguration(KafkaProperties kafkaProperties) {
//        this.kafkaProperties = kafkaProperties;
//    }

    /*
    @Bean
    ConsumerFactory<String, User> consumerFactory(SslBundles sslBundles) {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(sslBundles));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, User> filter() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRecordFilterStrategy(consumerRecord -> consumerRecord.value().getName() == "Bruno");
        return factory;
    }
     */
}
