package com.bcms.apache.consumer;

import jakarta.validation.constraints.NotNull;

public record MyKafkaPayload(
        String name,
        @NotNull
        Integer age
) { }