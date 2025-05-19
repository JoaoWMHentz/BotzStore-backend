package com.kinematech.kinematech_backend.repository;

import com.kinematech.kinematech_backend.model.Stock;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock, UUID> {

    @Query("SELECT s FROM Stock s WHERE s.product.id = :productId")
    Stock findByProductId(@Param("productId") UUID productId);
}
