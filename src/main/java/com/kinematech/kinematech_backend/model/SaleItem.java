package com.kinematech.kinematech_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@Entity
@Table(name = "sale_items")
public class SaleItem {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    private int quantity;

    private double price;

    public SaleItem() {
    }

    public SaleItem(Product product, Sale sale, int quantity, double price) {
        this.product = product;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
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

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return quantity * price;
    }
}
