package com.kinematech.kinematech_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "UUID")
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id", unique = true)
    private Product product;

    private int quantityAvailable;

    public Stock() {
    }

    public Stock(Product product, int quantityAvailable) {
        this.product = product;
        this.quantityAvailable = quantityAvailable;
    }

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void updateStock(int quantity) {
        this.quantityAvailable += quantity;
    }

    public void reduceStock(int quantity) {
        if (quantityAvailable >= quantity) {
            this.quantityAvailable -= quantity;
        } else {
            throw new IllegalArgumentException("Insufficient stock available");
        }
    }
}
