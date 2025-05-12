package com.kinematech.kinematech_backend.controller;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/user/{userId}")
    public Address getByUserId(@PathVariable Long customerId) {
        return addressService.findByCustomerId(customerId);
    }

    @PostMapping
    public Address create(@RequestBody Address address) {
        return addressService.save(address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        addressService.delete(id);
    }
}