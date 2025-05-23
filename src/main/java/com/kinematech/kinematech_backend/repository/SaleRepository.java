
package com.kinematech.kinematech_backend.repository;

import com.kinematech.kinematech_backend.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {

    @Query("SELECT s FROM Sale s WHERE s.customer.id = :customerId")
    List<Sale> findAllByCustomerId(@Param("customerId") UUID customerId);
}
