kafkaHostName=kafka
kafkaPort=29092
cmdPath=usr/bin

docker exec $kafkaHostName \
$cmdPath/kafka-topics.sh \
--bootstrap-server localhost:$kafkaPort \
--list