package com.taxiapp.taxiapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long>{

}
