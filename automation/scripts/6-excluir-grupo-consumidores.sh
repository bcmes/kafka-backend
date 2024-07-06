# Para excluir manualmente um ou vários grupos de consumidores.
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --delete --group my-group --group my-other-group