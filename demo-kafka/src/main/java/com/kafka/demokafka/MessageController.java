package com.kafka.demokafka;

import java.time.LocalDateTime;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

  private KafkaTemplate<String, KafkaMessage> kafkaTemplate;

  public MessageController(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping
  public void publish(@RequestBody MessageRequest request) {
    KafkaMessage message = new KafkaMessage(request.message(), LocalDateTime.now());
    kafkaTemplate.send("amigoscode", message);
  }

}
