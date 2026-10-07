package com.example.route_optimization_service;

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
@RequestMapping("/routes")
public class RouteController {

    private final RouteRepository repository;

    public RouteController(RouteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Route getRoute(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return repository.save(route);
    }

    @PutMapping("/{id}")
    public Route updateRoute(
            @PathVariable Long id,
            @RequestBody Route route) {

        Route existing =
                repository.findById(id).orElse(null);

        if (existing != null) {

            existing.setDriverId(route.getDriverId());
            existing.setAssignedBins(route.getAssignedBins());
            existing.setRouteStatus(route.getRouteStatus());

            return repository.save(existing);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteRoute(@PathVariable Long id) {

        repository.deleteById(id);

        return "Route deleted successfully";
    }
}