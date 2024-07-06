Exemplos de recursos Java, Spring e demais ferramentas relacionadas ao Backend
==================

# Apache-Kafka
### A ordem em que eu analisei os recursos:

1. docker-compose para uso local
   - automation/docker-compose.yml
2. scripts para testes locais
   - automation/scripts/script.sh
3. kafka-consumer-simples
   - uso do @KafkaListener de forma básica com leitura earliest.
4. kafka-consumer-concurrency
   - gerando N consumers, threads, para o mesmo group-id. Lembre-se, consumers <= partições.
5. kafka-consumer-validation-and-error-handler
   - validação do payload no @KafkaListener.
   - tratamento de erros disparados pelo @KafkaListener.
   - Redirecionamento da resposta do @KafkaListener para outro tópico em caso de sucesso.
   - Redirecionamento da resposta de erro do errorHandler para outro tópico em caso de falhas.
   - configuração de pacotes confiáveis para deserialização.
   - deserializando objetos criados a serem consumidos.
6. kafka-consumer-message-forwarding
   - consome mensagens de um tópico e responde para outro.
7. kafka-consumer-interceptor
8. kafka-topic
   - registrando tópicos.
   - registrando um bean kafka-admin.
   - você verá que podemos definir um timestamp ao enviar um evento.
9. kafka-producer-simples
   - publicando mensagem de maneira assincrona e sincrona.
   - publicando mensagem e tratando a resposta.
   - publicando mensagens com ProducerRecord ou Message.
10. kafka-producer-interceptor
11. kafka-consumer-filter
12. kafka-producer-template-consumer
    - Exemplo de que o KafkaTemplate pode ser usado para consumir mensagens. Mas este aqui não funciona :)
13. kafka-producer-routing-template
    - roteando o serializador do KafkaTemplate conforme o tópico. Assim posso publicar diversos objetos diferentes.
14. kafka-consumer-application-events
    - Durante a operação de consumo dos registros, são disparados diversos eventos, que podem ser consumidos, veja: https://docs.spring.io/spring-kafka/reference/kafka/events.html
    - Listener Container Properties: https://docs.spring.io/spring-kafka/reference/kafka/container-props.html
15. kafka-transactional
16. kafka-consumer-transaction-batch
17. kafka-configure-type-mapping
    - produzindo e consumindo tipos diferentes para o mesmo tópico.
    - definindo o header type, para serialização e deserialização
18. kafka-consumer-retry