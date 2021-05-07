package com.example.Commerce_Web.Model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int order_id;
    private int product_id;
    private int customer_id;
    private String date;

    public Order() {
    }

    public Order(int order_id, int product_id, int customer_id, String date) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.date = date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
