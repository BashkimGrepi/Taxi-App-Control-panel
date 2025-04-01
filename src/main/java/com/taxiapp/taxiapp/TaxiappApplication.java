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

	@Bean
	public CommandLineRunner driverDemo(DriverRepository driverRepository, AdminRepository adminRepository,
			VehicleRepository vehicleRepository, UserRepository userRepository) {
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
						null);
				adminRepository.save(admin);
				System.out.println("Admin created: " + admin);

			}
			if (driverRepository.findByFirstname("John").isEmpty()) {

				Driver driver1 = new Driver(
						"Driver123",
						"John",
						"Doe",
						"john.d@gmail.com",
						"+358401245",
						adminRepository.findByUsername("Admin123").get(),
						com.taxiapp.taxiapp.enums.Role.DRIVER,
						null,
						null);
				Driver driver2 = new Driver(
						"Driver2",
						"Pekka",
						"Pekkanen",
						"Pekka@gmail.com",
						"+12345678",
						adminRepository.findByUsername("Admin123").get(),
						com.taxiapp.taxiapp.enums.Role.DRIVER,
						null,
						null);
				Driver driver3 = new Driver(
						"Driver3",
						"Jaana",
						"Jaananen",
						"jaana@gmail.com",
						"+987456231",
						adminRepository.findByUsername("Admin123").get(),
						com.taxiapp.taxiapp.enums.Role.DRIVER,
						null,
						null);
				driverRepository.save(driver1);
				driverRepository.save(driver2);
				driverRepository.save(driver3);

				System.out.println("Admin and default drivers created." + driver1 + driver2 + driver3);
			}

			if (userRepository.count() == 0) {
				User user = new User(
						"demoUser",
						"password",
						"user.gmail.com",
						"+358401245",
						com.taxiapp.taxiapp.enums.Role.USER,
						adminRepository.findByUsername("Admin123").get(),
						null);
				userRepository.save(user);
				System.out.println("User created: " + user);
			}

			if (vehicleRepository.count() == 0) {
				Vehicle vehicle1 = new Vehicle(
						"Tesla S",
						"ABC-123",
						null);

				vehicleRepository.save(vehicle1);
				System.out.println("Vehicle created: " + vehicle1);

			}
		};
	}
}