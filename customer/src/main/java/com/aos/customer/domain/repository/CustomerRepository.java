package com.aos.customer.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aos.customer.domain.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
