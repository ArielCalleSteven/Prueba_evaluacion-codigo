package com.company.vehicles.controller;

import com.company.vehicles.dto.*;
import com.company.vehicles.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllActive());
    }

    @GetMapping("/low-stock-expensive")
    public ResponseEntity<?> getSpecial() {
        return ResponseEntity.ok(service.getSpecialVehicles());
    }

    @PatchMapping("/delete/{model}")
    public ResponseEntity<?> delete(@PathVariable String model) {
        return ResponseEntity.ok(service.deleteByModel(model));
    }

    @PatchMapping("/stock")
    public ResponseEntity<?> updateStock(@RequestBody VehicleStockRequestDto dto) {
        return ResponseEntity.ok(service.updateStock(dto));
    }
}