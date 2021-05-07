package com.example.Commerce_Web.Model;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int delivery_id;
    private String delivery_name;
    private int customer_id;

    public Delivery() {
    }

    public Delivery(int delivery_id, String delivery_name, int customer_id) {
        this.delivery_id = delivery_id;
        this.delivery_name = delivery_name;
        this.customer_id = customer_id;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getDelivery_name() {
        return delivery_name;
    }

    public void setDelivery_name(String delivery_name) {
        this.delivery_name = delivery_name;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
