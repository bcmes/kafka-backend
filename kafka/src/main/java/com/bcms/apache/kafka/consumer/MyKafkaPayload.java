package com.bcms.apache.kafka.consumer;

import jakarta.validation.constraints.NotNull;

public record MyKafkaPayload(
        String name,
        @NotNull
        Integer age
) { }