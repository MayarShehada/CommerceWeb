package com.example.Commerce_Web.Model;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int warehouse_id;
    private String location;
    private int capacity;

    public Warehouse() {
    }

    public Warehouse(int warehouse_id, String location, int capacity) {
        this.warehouse_id = warehouse_id;
        this.location = location;
        this.capacity = capacity;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
