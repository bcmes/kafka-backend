# FAIL: O json deve estar em uma única linha, senao, cada linha será uma entrada.
# Se vc passar um arquivo json formatado para ele, como no exemplo abaixo, isso ocorrerá
#
#docker exec -i broker \
#opt/kafka/bin/kafka-console-producer.sh \
#--bootstrap-server localhost:9092 \
#--topic my-topic-name < payload.json


# OK: Do ponto de vista de Kafka, cada mensagem é uma matriz de bytes.
# O comando -rc deixa o json em uma linha só, retira quebras de linhas e espaços..
#
#jq -rc . payload.json | docker exec -i broker \
#opt/kafka/bin/kafka-console-producer.sh \
#--bootstrap-server localhost:9092 \
#--topic my-topic-name


# OK: Se quiser inserir vários payloads jsons, basta que o arquivo tenha varios jsons..
jq -rc . multi-payloads.json | docker exec -i broker \
opt/kafka/bin/kafka-console-producer.sh \
--bootstrap-server localhost:9092 \
--topic my-topic-name


# FAIL: OU: indicando um separador entre as mensagens, para o arquivo lido, certo ? ERRADO !
# a instrução abaixo não funciona como um separador de mensagens... não use com este propósito.
#
#echo '{"key":"1"}&{"name":"emp1","sent_at":1625519962875}&{"key":"2"}' | docker exec -i broker \
#opt/kafka/bin/kafka-console-producer.sh \
#--broker-list localhost:9092 \
#--property parse.key=true \
#--property key.separator="&" \
#--topic my-topic-name