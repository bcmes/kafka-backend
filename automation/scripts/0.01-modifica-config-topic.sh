docker exec broker opt/kafka/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--alter --topic my-topic-name \
--partitions 10

#obs.: ao adicionar mais partições, os dados existentes nas partições atuais, não são redistríbuidos.
# Apenas ocorrerá uma redistribuição de partições entre os consumidores.
#obs.: Kafka não suporta a redução do número de partições de um tópico.