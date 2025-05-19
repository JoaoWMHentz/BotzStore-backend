package com.kinematech.kinematech_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategoryId(UUID categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }

    public Product create(Product product) {
        if (product.getId() != null) {
            // Verificar se o produto existe antes de atualizar
            Optional<Product> existingProduct = productRepository.findById(product.getId());
            if (!existingProduct.isPresent()) {
                throw new IllegalArgumentException("Produto não encontrado.");
            }

        }else{
           
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
            Optional<Product> existingProduct2 = productRepository.findByName(product.getName());
            if (existingProduct2.isPresent()) {
                throw new IllegalArgumentException("Já existe um produto com o nome fornecido.");
            }

        }    
        return productRepository.save(product);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}