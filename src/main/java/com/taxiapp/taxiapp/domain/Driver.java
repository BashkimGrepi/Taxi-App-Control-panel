package com.taxiapp.taxiapp.domain;

import java.util.List;

import com.taxiapp.taxiapp.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long driverId;

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin admin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    private List<Ride> rides;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "driver")
    private Vehicle vehicle;

    public Driver() {
    }

    public Driver(String username, String firstname, String lastname, String email, String password ,String phoneNumber, Admin admin,
            Role role, Vehicle vehicle,
            List<Ride> rides) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.vehicle = vehicle;
        this.rides = rides;
        this.admin = admin;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firtsname) {
        this.firstname = firtsname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Driver [driverId=" + driverId + ", username=" + username + ", firtsname=" + firstname + ", lastname="
                + lastname + ", email=" + email + ", phoneNumber=" + phoneNumber + ", role=" + role + "]";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
