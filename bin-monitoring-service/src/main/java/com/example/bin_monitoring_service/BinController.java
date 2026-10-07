package com.example.bin_monitoring_service;

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
@RequestMapping("/bins")
public class BinController {

    private final BinRepository repository;

    public BinController(BinRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Bin> getAllBins() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Bin getBin(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Bin createBin(@RequestBody Bin bin) {
        return repository.save(bin);
    }

    @PutMapping("/{id}")
    public Bin updateBin(@PathVariable Long id,
                         @RequestBody Bin bin) {

        Bin existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setLocation(bin.getLocation());
            existing.setFillLevel(bin.getFillLevel());
            existing.setLastUpdated(bin.getLastUpdated());

            return repository.save(existing);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteBin(@PathVariable Long id) {

        repository.deleteById(id);

        return "Bin deleted successfully";
    }
}
