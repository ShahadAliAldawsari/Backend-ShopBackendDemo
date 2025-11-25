package org.example.models;

import java.util.UUID;

public class Product {
    private final UUID productId;
    private String productName;
    private double productPrice;
    final int maxNameLength = 100;

    public Product(String productName, double productPrice){
        productId=UUID.randomUUID();
        setProductName(productName);
        setProductPrice(productPrice);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+
                "{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if(productName==null || productName.isBlank())
            throw new IllegalArgumentException("The name should not be empty");
        if(productName.length()>maxNameLength)
            throw new IllegalArgumentException("The name should not be more than "+maxNameLength+" chars");
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        if(productPrice<=0.0)
            throw new IllegalArgumentException("The price should be more than 0");
        this.productPrice = productPrice;
    }
}
