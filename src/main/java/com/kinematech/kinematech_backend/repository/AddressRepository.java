package com.kinematech.kinematech_backend.repository;

import com.kinematech.kinematech_backend.model.Address;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    @Query("SELECT a FROM Address a WHERE a.customer.id = :customerId")
    Address findByCustomerId(@Param("customerId") UUID customerId);
}
