Exemplos de recursos Java, Spring e demais ferramentas relacionadas ao Backend
==================

# Apache-Kafka
### A ordem em que foram analisados foi:

1. docker-compose para uso local.: automation/docker-compose.yml
2. scripts para testes locais.: automation/scripts/script.sh
3. kafka-consumer-simples
4. kafka-consumer-concurrency
5. kafka-consumer-validation-and-error-handler
6. kafka-consumer-message-forwarding
7. kafka-consumer-interceptor
8. kafka-topic
9. kafka-producer-simples
10. kafka-producer-interceptor
11. kafka-consumer-filter
12. kafka-producer-template-consumer
13. kafka-producer-routing-template
14. kafka-consumer-application-events
    - Durante a operação de consumo dos registros, são disparados diversos eventos, que podem ser consumidos, veja: https://docs.spring.io/spring-kafka/reference/kafka/events.html
    - Listener Container Properties: https://docs.spring.io/spring-kafka/reference/kafka/container-props.html
15. kafka-transactional