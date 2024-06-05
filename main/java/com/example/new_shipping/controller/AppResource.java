package com.example.new_shipping.controller;

import com.example.new_shipping.entity.Cargo;
import com.example.new_shipping.service.CargoService;
import com.vaadin.flow.router.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cargo")
public class AppResource {

    private CargoService cargoService;

    public AppResource(CargoService cargoService) {
        this.cargoService = cargoService;
    }


    @GetMapping("/{id}")
    public Cargo findById(@PathVariable long id) {
        return cargoService.findById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    @PostMapping
    public Cargo create(@RequestBody Cargo cargo) {
        return cargoService.save(cargo);
    }
}