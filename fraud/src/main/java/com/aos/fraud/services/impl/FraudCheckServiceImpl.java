package com.aos.fraud.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aos.fraud.domain.model.FraudCheckHistory;
import com.aos.fraud.domain.repository.FraudCheckHistoryRepository;
import com.aos.fraud.services.FraudCheckService;

@Service
public class FraudCheckServiceImpl implements FraudCheckService {

  private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

  @Autowired
  public FraudCheckServiceImpl(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
    this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
  }

  @Override
  public boolean isFraudlentCustomer(Integer customerId) {
    fraudCheckHistoryRepository.save(
        FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .build());

    return false;
  }

}
