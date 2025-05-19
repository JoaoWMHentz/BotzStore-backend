package com.kinematech.kinematech_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    // Campo para a thumbnail em Base64, sem limite de caracteres
    @Column(columnDefinition = "TEXT")
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
    @Column(columnDefinition = "TEXT")
    private String detailedDescription;

    private Double weight; // Peso em gramas
    private Double length; // Comprimento em cm
    private Double width;  // Largura em cm
    private Double height; // Altura em cm

    @ManyToOne(optional = true)
    @JoinColumn(name = "package_id")
    private Package packageInfo;

    public Product() {
    }

    public Product(String name, String description, Double price, Category category, String thumbnail,
            List<String> photos, boolean showOnHomepage, String detailedDescription, Double weight, Double length, Double width, Double height) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.thumbnail = thumbnail;
        this.photos = photos;
        this.showOnHomepage = showOnHomepage;
        this.detailedDescription = detailedDescription;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Package getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(Package packageInfo) {
        this.packageInfo = packageInfo;
    }
}

@Entity
@Table(name = "packages")
class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double length;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Double maxWeight;

    private String description;

    // Getters e Setters
}
