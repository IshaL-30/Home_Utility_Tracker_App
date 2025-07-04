package com.example.homeutility.controller.user;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.homeutility.core.enums.TransactionType;
import com.example.homeutility.core.enums.UserRole;
import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.UtilityBill;
import com.example.homeutility.model.utility.Wallet;
import com.example.homeutility.service.reminder.ReminderService;
import com.example.homeutility.service.user.UserService;
import com.example.homeutility.service.utility.GoalService;
import com.example.homeutility.service.utility.UtilityBillService;
import com.example.homeutility.service.utility.WalletService;

@Controller
public class DashboardController {
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private ReminderService reminderService;
	
	@Autowired
	private GoalService goalService;
	
	@Autowired
	private UtilityBillService utilityBillService;

	
	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
	    String email = principal.getName();
	    User user = userService.findByEmail(email).orElse(null);

	    List<UtilityBill> bills = utilityBillService.getBillsByUser(user);

	    Map<String, Double> monthlyDebited = new LinkedHashMap<>();
	    Map<String, Double> monthlyCredited = new LinkedHashMap<>();

	    for (UtilityBill bill : bills) {
	        String month = bill.getDueDate().getMonth().toString(); // or use DateTimeFormatter for better labels
	        double amount = bill.getAmount();

	        if (bill.getTransactionType() == TransactionType.DEBITED) {
	            monthlyDebited.put(month, monthlyDebited.getOrDefault(month, 0.0) + amount);
	        } else if (bill.getTransactionType() == TransactionType.CREDITED) {
	            monthlyCredited.put(month, monthlyCredited.getOrDefault(month, 0.0) + amount);
	        }
	    }
	    
	    Wallet wallet = walletService.getWalletByUser(user)
        	    .orElseGet(() -> walletService.createWalletForUser(user));


	    if (user != null) {

	        switch (user.getRole()) {
            case HOUSEHOLD_USER:
            	model.addAttribute("wallet", wallet);
                model.addAttribute("reminders", reminderService.getRemindersForUser(user));
                model.addAttribute("goals", goalService.getGoalsByUser(user));
                model.addAttribute("user", user);
                model.addAttribute("bill", new UtilityBill());
                model.addAttribute("chartLabels", new ArrayList<>(monthlyDebited.keySet()));
        	    model.addAttribute("chartExpenses", new ArrayList<>(monthlyDebited.values()));
        	    model.addAttribute("chartIncomes", new ArrayList<>(monthlyCredited.values()));
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
