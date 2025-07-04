package com.example.homeutility.controller.utility;

import com.example.homeutility.core.model.User;
import com.example.homeutility.service.user.UserService;
import com.example.homeutility.service.utility.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @PostMapping("/set-limit")
    public String setMonthlyLimit(@RequestParam double limit, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        walletService.updateMonthlyExpenseLimit(user, limit);
        return "redirect:/dashboard";
    }

    @PostMapping("/refresh-balance")
    public String refreshBalance(Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        walletService.updateBalanceFromGoals(user);
        return "redirect:/dashboard";
    }
}
