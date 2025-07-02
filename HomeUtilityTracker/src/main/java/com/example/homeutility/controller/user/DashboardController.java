package com.example.homeutility.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Wallet;
import com.example.homeutility.service.user.UserService;
import com.example.homeutility.service.utility.WalletService;

@Controller
public class DashboardController {
	
	@Autowired
	private WalletService walletService;
	
	@GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email).orElse(null);

        if (user != null) {
            model.addAttribute("user", user);
            
			Wallet wallet = walletService.getWalletByUser(user).orElse(null);
            if (wallet == null) {
                wallet = walletService.createWalletForUser(user);
            }
            model.addAttribute("wallet", wallet);

            switch (user.getRole()) {
                case HOUSEHOLD_USER:
                    return "dashboard/household";
                case PG_OWNER:
                    return "dashboard/pg";
                case SOCIETY_MANAGER:
                    return "dashboard/society";
            }
        }

        return "redirect:/users/login";
    }

    @Autowired
    private UserService userService;
}
