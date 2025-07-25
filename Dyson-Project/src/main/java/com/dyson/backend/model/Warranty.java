package com.dyson.backend.model;

import java.time.LocalDate;

public class Warranty {

    private Long id;  // Add this field
    private String productName;
    private String customerName;
    private LocalDate purchaseDate;
    private LocalDate warrantyExpiryDate;
    private WarrantyStatus status;  // If you want to keep status

    public Warranty() {
        // default constructor
    }

    public Warranty(Long id, String productName, String customerName, LocalDate purchaseDate, LocalDate warrantyExpiryDate) {
        this.id = id;
        this.productName = productName;
        this.customerName = customerName;
        this.purchaseDate = purchaseDate;
        this.warrantyExpiryDate = warrantyExpiryDate;
    }

    // id getter and setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // other getters and setters

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getWarrantyExpiryDate() {
        return warrantyExpiryDate;
    }
    public void setWarrantyExpiryDate(LocalDate warrantyExpiryDate) {
        this.warrantyExpiryDate = warrantyExpiryDate;
    }

    public WarrantyStatus getStatus() {
        return status;
    }
    public void setStatus(WarrantyStatus status) {
        this.status = status;
    }
}
