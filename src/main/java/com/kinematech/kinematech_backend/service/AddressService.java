package com.kinematech.kinematech_backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.repository.*;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address findByCustomerId(UUID customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void delete(UUID id) {
        addressRepository.deleteById(id);
    }
}