package com.kafka.demokafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

  @KafkaListener(topics = "amigoscode", groupId = "groupId", containerFactory = "factory" // must be added or will
                                                                                          // default to in-built
  )
  void listener(KafkaMessage data) {
    System.out.println("Listener receive: " + data.message() + " at: " + data.createdAt());
  }
}
