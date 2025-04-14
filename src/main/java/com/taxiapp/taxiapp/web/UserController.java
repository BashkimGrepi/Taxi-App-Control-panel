package com.taxiapp.taxiapp.web;

import java.lang.StackWalker.Option;
import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taxiapp.taxiapp.domain.User;
import com.taxiapp.taxiapp.repository.AdminRepository;
import com.taxiapp.taxiapp.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    

    public UserController(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        
        Optional<User> user = userRepository.findByUsername(username);
        Optional<User> usersAdmin = userRepository.findByUsername(username);

        if (user.isPresent() && usersAdmin.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("usersAdmin", usersAdmin.get());
        }
        else {
            return "redirect:/login?error";
        }

        return "user/profile";
    }
}
