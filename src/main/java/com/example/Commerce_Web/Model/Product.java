package com.example.Commerce_Web.Model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int product_id;
    private String product_name;
    private float price;
    private int size;
    private String style;
    private String color;
    private int quantity;
    private int warehouse_id;

    public Product() {
    }

    public Product(int product_id, String product_name, float price, int size, String style, String color, int quantity, int warehouse_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.size = size;
        this.style = style;
        this.color = color;
        this.quantity = quantity;
        this.warehouse_id = warehouse_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }
}
