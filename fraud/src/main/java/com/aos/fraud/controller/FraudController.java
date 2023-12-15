package com.aos.fraud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aos.fraud.controller.dto.FraudCheckResponseDTO;
import com.aos.fraud.services.FraudCheckService;

@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

  private FraudCheckService fraudCheckService;

  @Autowired
  public FraudController(FraudCheckService fraudCheckService) {
    this.fraudCheckService = fraudCheckService;
  }

  @GetMapping(path = "{customerId}")
  public ResponseEntity<Object> isFraudster(@PathVariable("customerId") Integer customerId) {
    boolean isFraudster = fraudCheckService.isFraudlentCustomer(customerId);
    return ResponseEntity.ok().body(new FraudCheckResponseDTO(isFraudster));
  }
}
