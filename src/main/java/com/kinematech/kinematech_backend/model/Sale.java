package com.kinematech.kinematech_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import org.hibernate.annotations.GenericGenerator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItems;

    private double totalAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;

    public Sale() {
    }

    public Sale(UUID id, Customer customer, List<SaleItem> saleItems, double totalAmount, Date saleDate) {
        this.id = id;
        this.customer = customer;
        this.saleItems = saleItems;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
