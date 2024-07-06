# Verifica a posição de um grupo de consumidores.:

docker exec broker opt/kafka/bin/kafka-consumer-groups.sh \
--bootstrap-server localhost:9092 \
--describe --group console-consumer-23299