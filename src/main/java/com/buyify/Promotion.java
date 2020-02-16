package com.buyify;

import javax.persistence.*;
import java.util.List;

@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long productId;
    private int promotion;

    @OneToMany(mappedBy = "promotion")
    private List<Product> products;

    protected Promotion() {
    }

    public Promotion(long productId, int promotion) {
        this.productId = productId;
        this.promotion = promotion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProducts(Product product) {
        this.products.add(product);
    }

    public void removeProducts(Product product) {
        this.products.remove(product);
    }

}
