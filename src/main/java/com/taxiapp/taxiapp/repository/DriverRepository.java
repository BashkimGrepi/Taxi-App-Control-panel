package com.taxiapp.taxiapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
    List<Driver> findByDriverId(Long driverId);
    List<Driver> findByFirstname(String firstname);

    List<Driver> findByLastname(String lastname);
    Optional<Driver> findByUsername(String username);
}
