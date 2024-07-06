cat payload.txt | docker exec -i broker \
opt/kafka/bin/kafka-console-producer.sh \
--bootstrap-server localhost:9092 \
--topic my-topic-name

# e o erro ocorrer:
# No such file or directory the input device is not a TTY. If you are using mintty,
# try prefixing the command with 'winpty'
# O erro acima informa que o Docker está tentando interagir com um dispositivo que não é um terminal (TTY).
# Isso ocorre se o -t estiver presente no comando, como em docker exec -it, deixe só com -i como acima.
