package com.example.Commerce_Web.Service;

import com.example.Commerce_Web.Model.Warehouse;
import com.example.Commerce_Web.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository repo;

    public List<Warehouse> listAll() {
        return repo.findAll();
    }

    public void save(Warehouse std) {
        repo.save(std);
    }

    public Warehouse get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
