package com.taxiapp.taxiapp.web;

import java.security.Principal;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.taxiapp.taxiapp.repository.UserRepository;

import com.taxiapp.taxiapp.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {


    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal) {
        User user;
        
        
        if (principal == null) {
            user = userRepository.findByUsername("demoUser").orElse(null);
        }
        else {
            user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
            ;
        }
        model.addAttribute("user", user);
        return "user/profile";
    }
   
}
