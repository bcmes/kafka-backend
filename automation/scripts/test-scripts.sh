## Enviando uma mensagem e sua key
echo 'test01:{"name":"Bruno", "age":41}' | docker exec -i broker \
opt/kafka/bin/kafka-console-producer.sh \
--broker-list localhost:9092 \
--property parse.key=true \
--property key.separator=: \
--topic topic1

#docker exec broker \
#opt/kafka/bin/kafka-console-consumer.sh \
#--bootstrap-server localhost:9092 \
#--topic my-topic-test1 \
##--from-beginning
##--consumer-property group.id=group-01

#docker exec -i broker \
#opt/kafka/bin/kafka-topics.sh \
#--bootstrap-server localhost:9092 \
#--describe \
#--topic topic3

#docker exec -i broker \
#opt/kafka/bin/kafka-topics.sh \
#--bootstrap-server localhost:9092 \
#--create \
#--topic topic2 \
#--partitions 2 \
#--replication-factor 1

# Enviando um lote de mensagens
#echo '{"name" : "Tara", "profession" : "Lucia"}
#{"name" : "Danielle", "profession" : "Baku"}
#{"name" : "Don", "profession" : "Dushanbe"}
#{"name" : "Ashley", "profession" : "Tashkent"}' | docker exec -i broker \
#opt/kafka/bin/kafka-console-producer.sh \
#--broker-list localhost:9092 \
#--topic topic1