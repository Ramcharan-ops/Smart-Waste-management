package com.example.collection_service;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionRepository repository;

    public CollectionController(CollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Collection> getAllCollections() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Collection getCollection(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Collection createCollection(
            @RequestBody Collection collection) {

        return repository.save(collection);
    }

    @PutMapping("/{id}")
    public Collection updateCollection(
            @PathVariable Long id,
            @RequestBody Collection collection) {

        Collection existing =
                repository.findById(id).orElse(null);

        if (existing != null) {

            existing.setBinId(collection.getBinId());
            existing.setDriverId(collection.getDriverId());
            existing.setStatus(collection.getStatus());

            return repository.save(existing);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteCollection(@PathVariable Long id) {

        repository.deleteById(id);

        return "Collection deleted successfully";
    }
}