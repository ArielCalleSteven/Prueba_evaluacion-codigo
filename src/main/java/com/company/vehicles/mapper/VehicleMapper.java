package com.company.vehicles.mapper;

import com.company.vehicles.dto.VehicleResponseDto;
import com.company.vehicles.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    public VehicleResponseDto toResponse(Vehicle entity) {
        VehicleResponseDto dto = new VehicleResponseDto();
        dto.id = entity.getId();
        dto.brand = entity.getBrand();
        dto.model = entity.getModel();
        dto.price = entity.getPrice();
        dto.stock = entity.getStock();
        return dto;
    }
}