package com.example.Commerce_Web.Service;

import com.example.Commerce_Web.Model.Order;
import com.example.Commerce_Web.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public List<Order> listAll() {
        return repo.findAll();
    }

    public void save(Order std) {
        repo.save(std);
    }

    public Order get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
