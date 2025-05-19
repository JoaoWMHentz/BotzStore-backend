package com.kinematech.kinematech_backend.repository;

import com.kinematech.kinematech_backend.model.Payment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query("SELECT p FROM Payment p WHERE p.sale.id = :saleId")
    Payment findBySaleId(@Param("saleId") UUID saleId);
}
