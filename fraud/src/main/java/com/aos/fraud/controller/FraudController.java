package com.aos.fraud.controller;

import com.aos.clients.fraud.FraudCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aos.fraud.services.FraudCheckService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudController {

  private final FraudCheckService fraudCheckService;

  @Autowired
  public FraudController(FraudCheckService fraudCheckService) {
    this.fraudCheckService = fraudCheckService;
  }

  @GetMapping(path = "{customerId}")
  public ResponseEntity<Object> isFraudster(@PathVariable("customerId") Integer customerId) {
    log.info("fraud check request for customer {}", customerId);

    boolean isFraudster = fraudCheckService.isFraudlentCustomer(customerId);
    return ResponseEntity.ok().body(new FraudCheckResponse(isFraudster));
  }
}
