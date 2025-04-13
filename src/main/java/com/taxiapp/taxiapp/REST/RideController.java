package com.taxiapp.taxiapp.REST;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taxiapp.taxiapp.repository.DriverRepository;
import com.taxiapp.taxiapp.repository.RideRepository;
import com.taxiapp.taxiapp.repository.UserRepository;

import com.taxiapp.taxiapp.domain.Driver;
import com.taxiapp.taxiapp.domain.Ride;
import com.taxiapp.taxiapp.domain.User;
import com.taxiapp.taxiapp.enums.Status;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/rides")

public class RideController {

    
    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    public RideController(RideRepository rideRepository, UserRepository userRepository, DriverRepository driverRepository) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    // 1. Create ride (User)
    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ride.setUser(user);
        ride.setStatus(Status.REQUESTED);
        Ride saved = rideRepository.save(ride);

        return ResponseEntity.ok(saved);
    }

    // 2. View all requested rides (Driver)
    @GetMapping("/requested")
    public ResponseEntity<List<Ride>> getRequestedRides() {
        List<Ride> rides = rideRepository.findByStatus(Status.REQUESTED);
        return ResponseEntity.ok(rides);
    }

    // 3. Driver accepts ride
    @PostMapping("/{id}/accept")
    public ResponseEntity<?> acceptRide(@PathVariable Long id, Principal principal) {
        Driver driver = driverRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        ride.setDriver(driver);
        ride.setStatus(Status.ONGOING);
        rideRepository.save(ride);

        return ResponseEntity.ok().build();
    }

    // 4. Driver marks ride completed
    @PostMapping("/{id}/complete")
    public ResponseEntity<?> completeRide(@PathVariable Long id, Principal principal) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (!principal.getName().equals(ride.getDriver().getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ride.setStatus(Status.COMPLETED);
        rideRepository.save(ride);

        return ResponseEntity.ok().build();
    }

    // (Optional) 5. View my rides (user)
    @GetMapping("/user")
    public ResponseEntity<List<Ride>> getMyRides(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow();
        List<Ride> rides = rideRepository.findByUser(user);
        return ResponseEntity.ok(rides);
    }
}
