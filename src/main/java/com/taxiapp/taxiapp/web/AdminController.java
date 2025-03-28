package com.taxiapp.taxiapp.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.taxiapp.taxiapp.domain.Driver;

import com.taxiapp.taxiapp.enums.Role;
import com.taxiapp.taxiapp.repository.AdminRepository;
import com.taxiapp.taxiapp.repository.DriverRepository;
import com.taxiapp.taxiapp.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;
    private final DriverRepository driverRepository;
   

    public AdminController(AdminRepository adminRepository,
            DriverRepository driverRepository,
            UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.driverRepository = driverRepository;
        
    };

    @GetMapping("/drivers")
    public String DriverList(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        return "admin/drivers";
    };

    @GetMapping("/drivers/add")
    public String addDriver(Model model) {
        model.addAttribute("driver", new Driver());

        Role[] roles = Role.values();
        model.addAttribute("roles", roles);

        model.addAttribute("admins", adminRepository.findAll());
        return "admin/driversadd";
    };

    @PostMapping("/drivers/add")
    public String addDriver(@ModelAttribute Driver driver) {
        if (driver.getFirstname().isEmpty() || driver.getLastname().isEmpty() || driver.getPhoneNumber().isEmpty()
                || driver.getUsername().isEmpty()) {
            return "redirect:/admin/driversadd";

        }
        driverRepository.save(driver);
        return "redirect:admin/drivers";
    };

    @GetMapping("/drivers/edit/{id}")
    public String updateDriver(@PathVariable Long driverId, Model model) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + driverId));
        model.addAttribute("driver", driver);
        return "admin/driversadd";
    };

    @GetMapping("/drivers/delete/{id}")
    public String deleteDriver(@PathVariable Long driverId) {
        driverRepository.deleteById(driverId);
        return "redirect:/admin/drivers";
    };

};