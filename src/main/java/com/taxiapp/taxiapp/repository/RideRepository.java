package com.taxiapp.taxiapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.Driver;
import com.taxiapp.taxiapp.domain.Ride;
import com.taxiapp.taxiapp.domain.User;

import java.util.List;
import com.taxiapp.taxiapp.enums.Status;


public interface RideRepository extends CrudRepository<Ride, Long> {
    List<Ride> findByRideId(Long rideId);

    List<Ride> findByDriver(Driver driver);

    List<Ride> findByStatus(Status status);
    List<Ride> findByUser(User user); 

}
