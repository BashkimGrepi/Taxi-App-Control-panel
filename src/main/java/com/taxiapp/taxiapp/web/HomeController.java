package com.taxiapp.taxiapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/home")
    public String home() {
        return "home";
    }



    @GetMapping("/admin")
    public String panel() {
        return "admin/panel";
    }

    @GetMapping("/guest/about")
    public String about() {
        return "guest/about";
    }

    
}
