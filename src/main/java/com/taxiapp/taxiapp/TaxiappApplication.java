package com.taxiapp.taxiapp;

import javax.management.relation.Role;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.taxiapp.taxiapp.domain.Admin;
import com.taxiapp.taxiapp.domain.Driver;
import com.taxiapp.taxiapp.repository.AdminRepository;
import com.taxiapp.taxiapp.repository.DriverRepository;

@SpringBootApplication
public class TaxiappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiappApplication.class, args);
	}

	@Bean
	public CommandLineRunner driverDemo(DriverRepository driverRepository, AdminRepository adminRepository) {
		return (args) -> {
			
			if (adminRepository.findByEmail("admin@gmail.com").isEmpty()) {
				
				Admin admin = new Admin(
					"Adam",
					"Smith",
					"Admin123",
					"password",
					"admin@gmail.com",
					com.taxiapp.taxiapp.enums.Role.ADMIN,
					null,
					null
					);
					adminRepository.save(admin);
					Driver driver1 = new Driver(
						"Driver123",
						"John",
						"Doe",
						"john.d@gmail.com",
						"+358401245",
						admin,
						com.taxiapp.taxiapp.enums.Role.DRIVER,
						null,
						null
						);
					Driver driver2 = new Driver(
						"Driver2",
						"Pekka",
						"Pekkanen",
						"Pekka@gmail.com",
						"+12345678",
						admin,
						com.taxiapp.taxiapp.enums.Role.DRIVER,
						null,
						null
						);
					Driver driver3 = new Driver(
						"Driver3",
						"Jaana",
						"Jaananen",
						"jaana@gmail.com",
						"+987456231",
						admin,
						com.taxiapp.taxiapp.enums.Role.DRIVER,
						null,
						null
						);
				driverRepository.save(driver1);
				driverRepository.save(driver2);
				driverRepository.save(driver3);
					
				System.out.println("Admin and default drivers created.");

			}
				

		};

	}
}
