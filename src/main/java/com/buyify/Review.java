package com.buyify;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private Date date;
    private String text;

    @ManyToOne
    private Product product;

    protected Review() {
    }

    public Review(Product product, long userId, Date date, String text) {
        this.product = product;
        this.userId = userId;
        this.date = date;
        this.text = text;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String review) {
        this.text = review;
    }

}
