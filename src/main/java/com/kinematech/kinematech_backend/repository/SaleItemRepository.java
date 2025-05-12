package com.kinematech.kinematech_backend.repository;

import com.kinematech.kinematech_backend.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    @Query("SELECT si FROM SaleItem si WHERE si.sale.id = :saleId")
    List<SaleItem> findAllBySaleId(@Param("saleId") Long saleId);
}
