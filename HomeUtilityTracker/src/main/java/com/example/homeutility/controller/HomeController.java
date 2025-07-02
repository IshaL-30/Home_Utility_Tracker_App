package com.example.homeutility.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage() {
        return "home";  // This resolves to templates/home.html
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/home"; // Redirect "/" to "/home"
    }
    
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    
}
