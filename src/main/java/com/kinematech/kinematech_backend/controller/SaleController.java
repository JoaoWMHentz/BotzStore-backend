package com.kinematech.kinematech_backend.controller;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAll() {
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable UUID id) {
        return saleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<Sale> getByCustomerId(@PathVariable UUID customerId) {
        return saleService.findAllByCustomerId(customerId);
    }

    @PostMapping
    public Sale create(@RequestBody Sale sale) {
        return saleService.save(sale);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        saleService.delete(id);
    }
}