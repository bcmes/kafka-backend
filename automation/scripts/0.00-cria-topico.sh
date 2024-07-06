kafkaHostName=kafka
kafkaPort=29092
# Quando "apache/kafka" os comandos estão em "opt/kafka/bin/". Quando "confluentinc/cp-kafka" está em "usr/bin"
cmdPath=usr/bin

docker exec $kafkaHostName \
$cmdPath/kafka-topics.sh \
--bootstrap-server localhost:$kafkaPort \
--create \
--topic my-topic-name \
--partitions 1 \
--replication-factor 1
#--config x=y #Aqui vc pode setar alguma propriedade para o tópico criado.

# obs.: Tópicos podem criados automaticamente quando os dados são publicados
# pela primeira vez em um tópico inexistente.
# obs.: Lembre-se que a quantidade de partições impacta no paralelismo máximo de seus consumidores.
# obs.: os nomes de tópicos não podem ter mais de 249 caracteres.
# obs.: Ao nomear seu tópico, use somente "." ou "_" nunca misture os dois. Preferencialmente use "-"
# isso ocorre devido limitações em nomes de métricas, que podem colidir.