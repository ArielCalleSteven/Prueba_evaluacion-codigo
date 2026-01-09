package com.company.vehicles.repository;

import com.company.vehicles.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDeleted(String deleted);
    Optional<Vehicle> findByModel(String model);
    List<Vehicle> findByDeletedAndPriceGreaterThanAndStockLessThan(String deleted, Double price, Integer stock);
}