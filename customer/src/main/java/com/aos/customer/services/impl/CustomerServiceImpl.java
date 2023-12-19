package com.aos.customer.services.impl;

import com.aos.clients.fraud.FraudCheckResponse;
import com.aos.clients.fraud.FraudClient;
import com.aos.clients.notification.NotificationClient;
import com.aos.clients.notification.NotificationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aos.customer.config.CustomerConfig;
import com.aos.customer.controllers.dto.CustomerRegistrationRequestDTO;
import com.aos.customer.domain.model.Customer;
import com.aos.customer.domain.repository.CustomerRepository;
import com.aos.customer.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final NotificationClient notificationClient;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository, FraudClient fraudClient,
      NotificationClient notificationClient) {
    this.customerRepository = customerRepository;
    this.fraudClient = fraudClient;
    this.notificationClient = notificationClient;
  }

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

    // FraudCheckResponseDTO fraudCheckResponse =
    // customerConfig.restTemplate().getForObject(
    // "http://FRAUD/api/v1/fraud-check/{customerId}",
    // FraudCheckResponseDTO.class,
    // customer.getId());

    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("fraudster");
    }

    // todo: make it async, i.e add to queue
    NotificationRequest notificationRequest = new NotificationRequest(
        customer.getId(),
        customer.getEmail(),
        String.format("Hi %s, welcome to Amigoscode...",
            customer.getFirstName()));

    notificationClient.sendNotification(notificationRequest);
  }

}
