package com.buyify;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(1)
    @NotNull(message = "Promotion value may not be null")
    private int promotion;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    protected Promotion() {
    }

    public Promotion(int promotion) {
        this.promotion = promotion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
