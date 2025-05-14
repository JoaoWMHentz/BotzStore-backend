package com.kinematech.kinematech_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinematech.kinematech_backend.model.*;
import com.kinematech.kinematech_backend.repository.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }

    public Product save(Product product) {
        // Verificar se os campos obrigatórios estão preenchidos
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (product.getPrice() == null) {
            throw new IllegalArgumentException("O preço do produto é obrigatório.");
        }
        if (product.getCategory() == null) {
            throw new IllegalArgumentException("A categoria do produto é obrigatória.");
        }

        // Verificar se já existe um produto com o mesmo nome
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Já existe um produto com o nome fornecido.");
        }

        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}