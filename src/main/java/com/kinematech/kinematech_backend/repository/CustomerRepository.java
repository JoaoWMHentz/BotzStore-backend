package com.kinematech.kinematech_backend.repository;

import com.kinematech.kinematech_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
