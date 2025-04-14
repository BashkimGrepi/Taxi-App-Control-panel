package com.taxiapp.taxiapp;
import com.taxiapp.taxiapp.repository.UserRepository;
import com.taxiapp.taxiapp.repository.VehicleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.taxiapp.taxiapp.domain.Admin;
import com.taxiapp.taxiapp.domain.Driver;
import com.taxiapp.taxiapp.domain.Ride;
import com.taxiapp.taxiapp.domain.User;
import com.taxiapp.taxiapp.domain.Vehicle;
import com.taxiapp.taxiapp.enums.Role;
import com.taxiapp.taxiapp.enums.Status;
import com.taxiapp.taxiapp.repository.AdminRepository;
import com.taxiapp.taxiapp.repository.DriverRepository;
import com.taxiapp.taxiapp.repository.RideRepository;

@SpringBootApplication
public class TaxiappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiappApplication.class, args);
	}


	

	@Bean
	public CommandLineRunner demoData(
		AdminRepository adminRepository,
		DriverRepository driverRepository,
		UserRepository userRepository,
		VehicleRepository vehicleRepository,
		RideRepository rideRepository,
		PasswordEncoder passwordEncoder
	) {


		return (args) -> {
			Admin admin = new Admin(
				"Bashkim",
                "Grepi",
                "bashkimg",
                passwordEncoder.encode("password"),
                "bashkim@taxiapp.com",
                Role.ADMIN,
                null,
                null
			);
			adminRepository.save(admin);

			User user1 = new User(
				"user",
				passwordEncoder.encode("user"),
				"user1.gmail.com",
				"+3584432184",
				Role.USER,
				admin,
				null
			);
			userRepository.save(user1);
	
			User user2 = new User(
				"user2",
				passwordEncoder.encode("password2"),
				"user2.uss@gmail.com",
				"+35844321284",
				Role.USER,
				admin,
				null
			);
			userRepository.save(user2);

		Driver driver1 = new Driver(
                "driver",
                "Alice",
                "Smith",
                "dAliceSmith@taxiapp.com",
                passwordEncoder.encode("kuljettaja"),
                "+354847456",
                admin,
                Role.DRIVER,
                null,
                null
        );
        driverRepository.save(driver1);

        Driver driver2 = new Driver(
                "driver2",
                "Bob",
                "Johnson",
                "Bob.johnson@taxiapp.com",
                passwordEncoder.encode("kuljettaja2"),
                "555654321",
                admin,
                Role.DRIVER,
                null,
                null
        );

		driverRepository.save(driver2);

		Vehicle vehicle1 = new Vehicle(
                "Tesla Model S",
                "XLC-149",
                driver1
        );

        Vehicle vehicle2 = new Vehicle(
                "Toyota Prius",
                "XYZ-789",
                driver2
        );

        vehicleRepository.save(vehicle1);
        vehicleRepository.save(vehicle2);
		
		driver1.setVehicle(vehicle1);
		driver2.setVehicle(vehicle2);
		driverRepository.save(driver1);
		driverRepository.save(driver2);

		Ride ride1 = new Ride(
                "Helsinki Central Station",
                "Helsinki-Vantaa Airport",
                Status.COMPLETED,
                user1,
                driver1
        );

        Ride ride2 = new Ride(
                "Itämaankuja 3 C",
                "Malmin kanrtano",
                Status.ACCEPTED,
                user2,
                driver2
        );

        Ride ride3 = new Ride(
                "Tyynylahdenkuja 2",
                "Pukinmäen asema",
                Status.ONGOING,
                user1,
                driver2
        );

        rideRepository.save(ride1);
        rideRepository.save(ride2);
        rideRepository.save(ride3);

        System.out.println("Demo data initialized successfully!");

		};

		
	}
}