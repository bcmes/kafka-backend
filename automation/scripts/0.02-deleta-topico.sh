docker exec broker opt/kafka/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--delete --topic my-topic-name