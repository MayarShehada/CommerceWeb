package com.example.Commerce_Web.Service;

import com.example.Commerce_Web.Model.Delivery;
import com.example.Commerce_Web.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository repo;

    public List<Delivery> listAll() {
        return repo.findAll();
    }

    public void save(Delivery std) {
        repo.save(std);
    }

    public Delivery get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
