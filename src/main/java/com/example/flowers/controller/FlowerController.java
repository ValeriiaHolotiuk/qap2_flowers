package com.example.flowers.controller;

import com.example.flowers.model.Flower;
import com.example.flowers.service.FlowerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {

    private final FlowerService service;

    public FlowerController(FlowerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Flower> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Flower one(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flower create(@Valid @RequestBody Flower flower) {
        return service.create(flower);
    }

    @PutMapping("/{id}")
    public Flower update(@PathVariable Long id, @Valid @RequestBody Flower flower) {
        return service.update(id, flower);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notFound(RuntimeException ex) {
        return Map.of("error", ex.getMessage());
    }
}
