# Listar todos os grupos de consumidores em todos os tópicos.:

docker exec broker opt/kafka/bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list