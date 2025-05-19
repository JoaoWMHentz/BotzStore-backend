package com.kinematech.kinematech_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.repository.*;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Optional<Sale> findById(UUID id) {
        return saleRepository.findById(id);
    }

    public List<Sale> findAllByCustomerId(UUID customerId) {
        return saleRepository.findAllByCustomerId(customerId);
    }

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    public void delete(UUID id) {
        saleRepository.deleteById(id);
    }
}