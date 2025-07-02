package com.example.homeutility.controller.utility;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Wallet;
import com.example.homeutility.service.user.UserService;
import com.example.homeutility.service.utility.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @GetMapping("/view")
    public String viewWallet(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        Wallet wallet = walletService.getWalletByUser(user).orElseGet(() -> walletService.createWalletForUser(user));
        model.addAttribute("wallet", wallet);
        return "wallet/view";
    }

    @PostMapping("/transfer")
    public String transferToWallet(@RequestParam double amount, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        walletService.transferToWallet(user, amount);
        return "redirect:/wallet/view";
    }

    @PostMapping("/limit")
    public String setExpenseLimit(@RequestParam double limit, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        walletService.setMonthlyExpenseLimit(user, limit);
        return "redirect:/wallet/view";
    }
}
