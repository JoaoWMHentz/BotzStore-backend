package com.kinematech.kinematech_backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    // Campo para a thumbnail em Base64, sem limite de caracteres
    @Lob
    private String thumbnail;

    // Lista de fotos adicionais em Base64
    @ElementCollection
    @CollectionTable(name = "product_photos", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "photo", columnDefinition = "TEXT")
    private List<String> photos;

    // Indica se o produto deve aparecer na página inicial
    @Column(nullable = false)
    private boolean showOnHomepage;

    // Descrição detalhada em HTML, sem limite de caracteres
    @Lob
    private String detailedDescription;

    public Product() {}

    public Product(String name, String description, Double price, Category category, String thumbnail, List<String> photos, boolean showOnHomepage, String detailedDescription) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.thumbnail = thumbnail;
        this.photos = photos;
        this.showOnHomepage = showOnHomepage;
        this.detailedDescription = detailedDescription;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public boolean isShowOnHomepage() {
        return showOnHomepage;
    }

    public void setShowOnHomepage(boolean showOnHomepage) {
        this.showOnHomepage = showOnHomepage;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }
}
