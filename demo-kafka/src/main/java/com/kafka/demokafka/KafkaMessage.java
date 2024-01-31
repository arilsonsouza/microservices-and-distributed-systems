package com.kafka.demokafka;

import java.time.LocalDateTime;

public record KafkaMessage(String message, LocalDateTime createdAt) {

}
