package com.taxiapp.taxiapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
    List<Driver> findByDriverId(Long driverId);
    List<Driver> findByFirtsname(String firtsname);
    List<Driver> findByLastname(String lastname);
}
