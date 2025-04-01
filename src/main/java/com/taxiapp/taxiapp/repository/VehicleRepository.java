package com.taxiapp.taxiapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long>{
    Optional<Vehicle> findByLicensePlate(String licensePlate);

   
}
