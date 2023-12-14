package com.aos.customer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aos.customer.controllers.dto.CustomerRegistrationRequestDTO;
import com.aos.customer.domain.model.Customer;
import com.aos.customer.domain.repository.CustomerRepository;
import com.aos.customer.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
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
    customerRepository.save(customer);
  }

}
