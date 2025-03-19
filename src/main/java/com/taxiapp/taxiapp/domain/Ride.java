package com.taxiapp.taxiapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import com.taxiapp.taxiapp.enums.Status;;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideId;

    private String startLocation;
    private String endLocation;
    private Status status;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne
    @JoinColumn(name = "driverId")
    private Driver driver;


    public Ride(String startLocation, String endLocation, Status status, User user, Driver driver) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.status = status;
        this.user = user;
        this.driver = driver;
    }


    public Ride() {
    }


    public Long getRideId() {
        return rideId;
    }


    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }


    public String getStartLocation() {
        return startLocation;
    }


    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }


    public String getEndLocation() {
        return endLocation;
    }


    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Driver getDriver() {
        return driver;
    }


    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    @Override
    public String toString() {
        return "Ride [rideId=" + rideId + ", startLocation=" + startLocation + ", endLocation=" + endLocation
                + ", status=" + status + ", user=" + user + ", driver=" + driver + "]";
    }

    
    
}
