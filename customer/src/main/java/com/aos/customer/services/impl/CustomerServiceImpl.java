package com.aos.customer.services.impl;

import com.aos.amqp.RabbitMQMessageProducer;
import com.aos.clients.fraud.FraudCheckResponse;
import com.aos.clients.fraud.FraudClient;
import com.aos.clients.notification.NotificationRequest;

import org.springframework.stereotype.Service;

import com.aos.customer.controllers.dto.CustomerRegistrationRequestDTO;
import com.aos.customer.domain.model.Customer;
import com.aos.customer.domain.repository.CustomerRepository;
import com.aos.customer.services.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final RabbitMQMessageProducer rabbitMQMessageProducer;

  @Override
  public void registerCustomer(CustomerRegistrationRequestDTO customerRegistrationRequestDTO) {
    Customer customer = Customer.builder()
        .firstName(customerRegistrationRequestDTO.firstName())
        .lastName(customerRegistrationRequestDTO.lastName())
        .email(customerRegistrationRequestDTO.email())
        .build();
    // todo: check if email valid
    // todo: check if email not taken
    customerRepository.saveAndFlush(customer);

    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("fraudster");
    }

    NotificationRequest notificationRequest = new NotificationRequest(
        customer.getId(),
        customer.getEmail(),
        String.format("Hi %s, welcome to Amigoscode...",
            customer.getFirstName()));

    rabbitMQMessageProducer.publish(
        notificationRequest,
        "internal.exchange",
        "internal.notification.routing-key");
  }

}
