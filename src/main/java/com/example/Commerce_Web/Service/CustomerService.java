package com.example.Commerce_Web.Service;

import com.example.Commerce_Web.Model.Customer;
import com.example.Commerce_Web.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public List<Customer> listAll() {
        return repo.findAll();
    }

    public void save(Customer std) {
        repo.save(std);
    }

    public Customer get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
