package com.kinematech.kinematech_backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.repository.*;

@Service
public class SaleItemService {
    @Autowired
    private SaleItemRepository saleItemRepository;

    public List<SaleItem> findAllBySaleId(UUID saleId) {
        return saleItemRepository.findAllBySaleId(saleId);
    }

    public SaleItem save(SaleItem saleItem) {
        return saleItemRepository.save(saleItem);
    }

    public void delete(UUID id) {
        saleItemRepository.deleteById(id);
    }
}