package com.company.vehicles.service;
import com.company.vehicles.dto.*;
import java.util.List;

public interface VehicleService {
    List<VehicleResponseDto> getAllActive();
    List<VehicleResponseDto> getSpecialVehicles();
    OperationResponseDto deleteByModel(String model);
    VehicleResponseDto updateStock(VehicleStockRequestDto dto);
}