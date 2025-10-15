package com.example.flowers.service;

import com.example.flowers.model.Flower;
import com.example.flowers.repository.FlowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerService {
    private final FlowerRepository repo;

    public FlowerService(FlowerRepository repo) {
        this.repo = repo;
    }

    public List<Flower> all() { return repo.findAll(); }

    public Flower get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Flower " + id + " not found"));
    }

    public Flower create(Flower f) { return repo.save(f); }

    public Flower update(Long id, Flower data) {
        Flower f = get(id);
        f.setName(data.getName());
        f.setColor(data.getColor());
        f.setPrice(data.getPrice());
        f.setAvailableQuantity(data.getAvailableQuantity());
        return repo.save(f);
    }

    public void delete(Long id) { repo.deleteById(id); }
}
