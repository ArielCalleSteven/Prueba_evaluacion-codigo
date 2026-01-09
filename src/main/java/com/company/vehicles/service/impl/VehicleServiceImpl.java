package com.company.vehicles.service.impl;

import com.company.vehicles.dto.*;
import com.company.vehicles.entity.Vehicle;
import com.company.vehicles.exception.*;
import com.company.vehicles.mapper.VehicleMapper;
import com.company.vehicles.repository.VehicleRepository;
import com.company.vehicles.service.VehicleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;
    private final VehicleMapper mapper;

    public VehicleServiceImpl(VehicleRepository repository, VehicleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<VehicleResponseDto> getAllActive() {
        return repository.findByDeleted("N").stream()
                .map(mapper::toResponse).toList();
    }

    @Override
    public List<VehicleResponseDto> getSpecialVehicles() {
        return repository.findByDeletedAndPriceGreaterThanAndStockLessThan("N", 20000.0, 10)
                .stream().map(mapper::toResponse).toList();
    }

    @Override
    public OperationResponseDto deleteByModel(String model) {
        Vehicle v = repository.findByModel(model)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
        
        if ("S".equals(v.getDeleted())) {
            throw new ConflictException("Vehicle already deleted");
        }
        v.setDeleted("S");
        repository.save(v);
        return new OperationResponseDto("Vehicle deleted successfully");
    }

    @Override
    public VehicleResponseDto updateStock(VehicleStockRequestDto dto) {
        if (dto.stock < 0) throw new RuntimeException("Invalid stock"); // Debería ser BadRequest
        
        Vehicle v = repository.findById(dto.id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
        
        v.setStock(dto.stock);
        return mapper.toResponse(repository.save(v));
    }
}