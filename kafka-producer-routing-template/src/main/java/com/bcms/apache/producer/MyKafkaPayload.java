package com.bcms.apache.producer;

public record MyKafkaPayload(
        String name,
        Integer age
) { }