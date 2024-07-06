#docker exec broker \
#opt/kafka/bin/kafka-topics.sh \
#--bootstrap-server localhost:9092 \
#--describe \
#--topic my-topic-name

docker exec kafka \
usr/bin/kafka-topics \
--bootstrap-server localhost:29092 \
--describe \
--topic my-topic-test