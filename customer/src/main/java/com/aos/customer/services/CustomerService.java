package com.aos.customer.services;

import com.aos.customer.controllers.dto.CustomerRegistrationRequestDTO;

public interface CustomerService {
  public void registerCustomer(CustomerRegistrationRequestDTO customerRegistrationRequestDTO);
}
