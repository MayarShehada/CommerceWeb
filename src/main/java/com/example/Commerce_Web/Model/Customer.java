package com.example.Commerce_Web.Model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int customer_id;
    private String customer_name;
    private int age;
    private int phonenumber;
    private String address;
    private int visacard_number;

    public Customer() {
    }

    public Customer(int customer_id, String customer_name, int age, int phonenumber, String address, int visacard_number) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.age = age;
        this.phonenumber = phonenumber;
        this.address = address;
        this.visacard_number = visacard_number;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getVisacard_number() {
        return visacard_number;
    }

    public void setVisacard_number(int visacard_number) {
        this.visacard_number = visacard_number;
    }
}
