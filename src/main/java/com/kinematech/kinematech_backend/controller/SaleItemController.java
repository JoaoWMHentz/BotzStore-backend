package com.kinematech.kinematech_backend.controller;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale-items")
public class SaleItemController {
    @Autowired
    private SaleItemService saleItemService;

    @GetMapping("/sale/{saleId}")
    public List<SaleItem> getBySaleId(@PathVariable Long saleId) {
        return saleItemService.findAllBySaleId(saleId);
    }

    @PostMapping
    public SaleItem create(@RequestBody SaleItem saleItem) {
        return saleItemService.save(saleItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        saleItemService.delete(id);
    }
}