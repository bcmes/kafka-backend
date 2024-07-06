#echo '{"key":"value"}' | docker exec -i broker \
#opt/kafka/bin/kafka-console-producer.sh \
#--bootstrap-server localhost:9092 \
#--topic my-topic-name

# Quando você usa -i com docker exec, você está dizendo ao Docker para manter a entrada padrão aberta,
# permitindo que você interaja com o processo que está sendo executado dentro do contêiner. Isso é útil
# quando você precisa enviar dados para o processo dentro do contêiner a partir do terminal local, como
# no caso de enviar dados para o kafka-console-producer.


# Enviando uma mensagem e sua key
#echo '123:{"name":"Bruno", "favorite_number":0, "favorite_color":"Blue"}' | docker exec -i kafka \
#usr/bin/kafka-console-producer \
#--broker-list localhost:29092 \
#--property parse.key=true \
#--property key.separator=: \
#--topic my-topic-test
#
# parse.key=true ,habilita o envio da key.
# key.separator=: ,usa o : como separador entre a key e a value (mensagem)


# Usando o schema-registry para validar o contrato da mensagem enviada
# Este aqui nao funcionou..
echo '5:{"nameX":"Bruno", "favorite_number":0, "favorite_color":"Blue"}' | docker exec -i kafka \
usr/bin/kafka-console-producer \
--broker-list localhost:29092 \
--topic my-topic-test \
--property value.schema.registry.url=http://schemaregistry:8085 \
--property value.schema.id=-2781481884135578471L \
--property parse.key=true \
--property key.separator=:
