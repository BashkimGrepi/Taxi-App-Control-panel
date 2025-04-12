package com.taxiapp.taxiapp;
import com.taxiapp.taxiapp.repository.UserRepository;
import com.taxiapp.taxiapp.repository.VehicleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.taxiapp.taxiapp.domain.Admin;
import com.taxiapp.taxiapp.domain.Driver;
import com.taxiapp.taxiapp.domain.User;
import com.taxiapp.taxiapp.domain.Vehicle;
import com.taxiapp.taxiapp.repository.AdminRepository;
import com.taxiapp.taxiapp.repository.DriverRepository;

@SpringBootApplication
public class TaxiappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiappApplication.class, args);
	}

}