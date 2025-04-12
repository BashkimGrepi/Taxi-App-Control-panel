package com.taxiapp.taxiapp.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taxiapp.taxiapp.domain.Driver;
import com.taxiapp.taxiapp.enums.Role;
import com.taxiapp.taxiapp.repository.DriverRepository;



@Controller
@RequestMapping("/driver")
public class DriverController {

    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    
     @GetMapping("/profile")
     public String driverProfile(Model model, Principal principal) {
         Driver driver;

         if (principal == null) {
             driver = driverRepository.findByUsername("Driver123").orElse(null);

         } else {
             driver = driverRepository.findByUsername(principal.getName())
                     .orElseThrow(() -> new RuntimeException("Driver not found"));
         }
         
         model.addAttribute("driver", driver);
         
        return "driver/profile";
     }
}
