package com.aos.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aos.customer.controllers.dto.CustomerRegistrationRequestDTO;
import com.aos.customer.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public void registerCustomer(@RequestBody CustomerRegistrationRequestDTO customerRegistrationRequestDTO) {
    log.info("new customer registration {}", customerRegistrationRequestDTO);
    customerService.registerCustomer(customerRegistrationRequestDTO);
  }
}
