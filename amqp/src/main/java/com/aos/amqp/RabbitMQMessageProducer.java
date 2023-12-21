package com.aos.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQMessageProducer {
  private final AmqpTemplate amqpTemplate;

  public RabbitMQMessageProducer(AmqpTemplate amqpTemplate) {
    this.amqpTemplate = amqpTemplate;
  }

  public void publish(Object payload, String exchange, String routingKey) {
    log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
    amqpTemplate.convertAndSend(exchange, routingKey, payload);
    log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
  }
}
