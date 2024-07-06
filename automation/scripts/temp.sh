# Enviando uma mensagem e sua key
echo 'test01:{"name":"Bruno", "favorite_color":"Blue"}' | docker exec -i broker \
opt/kafka/bin/kafka-console-producer.sh \
--broker-list localhost:9092 \
--property parse.key=true \
--property key.separator=: \
--topic my-topic-test1

#docker exec broker \
#opt/kafka/bin/kafka-console-consumer.sh \
#--bootstrap-server localhost:9092 \
#--topic my-topic-test1 \
##--from-beginning
##--consumer-property group.id=group-01