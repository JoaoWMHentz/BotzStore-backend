package com.kinematech.kinematech_backend.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.repository.*;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment findBySaleId(UUID saleId) {
        return paymentRepository.findBySaleId(saleId);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void delete(UUID id) {
        paymentRepository.deleteById(id);
    }
}
