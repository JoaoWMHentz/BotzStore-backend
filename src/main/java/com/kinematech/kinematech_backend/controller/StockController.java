package com.kinematech.kinematech_backend.controller;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/product/{productId}")
    public Stock getByProductId(@PathVariable Long productId) {
        return stockService.findByProductId(productId);
    }

    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockService.delete(id);
    }
}