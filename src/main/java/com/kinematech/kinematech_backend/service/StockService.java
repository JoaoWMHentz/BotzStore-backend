package com.kinematech.kinematech_backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.repository.*;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock findByProductId(UUID productId) {
        return stockRepository.findByProductId(productId);
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public void delete(UUID id) {
        stockRepository.deleteById(id);
    }
}