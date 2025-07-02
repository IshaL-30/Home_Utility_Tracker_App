package com.example.homeutility.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.homeutility.core.dto.RegisterRequest;
import com.example.homeutility.core.enums.UserRole;
import com.example.homeutility.core.model.User;
import com.example.homeutility.service.user.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
    private UserService userService;

	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        model.addAttribute("roles", UserRole.values());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerRequest") RegisterRequest request, Model model) {
    	try {
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setRole(request.getRole());

            userService.registerUser(user);
            return "redirect:/users/login";

        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("roles", UserRole.values());
            return "user/register";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }
}
