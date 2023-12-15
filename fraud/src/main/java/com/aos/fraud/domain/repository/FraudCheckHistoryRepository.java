package com.aos.fraud.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aos.fraud.domain.model.FraudCheckHistory;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {

}
