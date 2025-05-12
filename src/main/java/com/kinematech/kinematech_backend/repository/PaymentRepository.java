package com.kinematech.kinematech_backend.repository;

import com.kinematech.kinematech_backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.sale.id = :saleId")
    Payment findBySaleId(@Param("saleId") Long saleId);
}
