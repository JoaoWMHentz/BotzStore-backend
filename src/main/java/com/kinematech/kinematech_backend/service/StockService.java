package com.kinematech.kinematech_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.repository.*;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock findByProductId(Long productId) {
        return stockRepository.findByProductId(productId);
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public void delete(Long id) {
        stockRepository.deleteById(id);
    }
}