package com.taxiapp.taxiapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taxiapp.taxiapp.domain.Driver;
import com.taxiapp.taxiapp.domain.User;
import com.taxiapp.taxiapp.repository.AdminRepository;
import com.taxiapp.taxiapp.repository.DriverRepository;
import com.taxiapp.taxiapp.repository.UserRepository;


@Controller
@RequestMapping("/admin")
public class AdminController {



    private final AdminRepository adminRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    public AdminController(AdminRepository adminRepository,
            DriverRepository driverRepository,
            UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    };
    

    @GetMapping("/drivers")
    public String DriverList(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        return "admin/drivers"; 
    };


    @GetMapping("/drivers/add")
    public String addDriver(Model model) {
        model.addAttribute("driver", new Driver());
        return "admin-driver-form";
    };


    @PostMapping("/drivers")
    public String addDriver(@ModelAttribute Driver driver) {
        if (driver.getFirtsname().isEmpty() || driver.getLastname().isEmpty() || driver.getPhoneNumber().isEmpty() || driver.getUsername().isEmpty()) {
            return "redirect:/admin-drivers-form";
            
        }
        driverRepository.save(driver);
        return "redirect:/admin/drivers";
    };

     
    
     @PutMapping("/drivers/edit/{id}")
     public String updateDriver(@PathVariable Long driverId, Model model) {
        Driver driver = driverRepository.findById(driverId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + driverId));
        model.addAttribute("driver", driver);
        return "admin-driver-form";
    };
    
    @GetMapping("/drivers/delete/{id}")
    public String deleteDriver(@PathVariable Long driverId) {
        driverRepository.deleteById(driverId);
        return "redirect:/admin/drivers";
    };

};