package com.kinematech.kinematech_backend.controller;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/sale/{saleId}")
    public Payment getBySaleId(@PathVariable Long saleId) {
        return paymentService.findBySaleId(saleId);
    }

    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return paymentService.save(payment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentService.delete(id);
    }
}
