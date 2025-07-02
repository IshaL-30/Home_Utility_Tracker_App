package com.example.homeutility.controller.utility;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.homeutility.core.enums.BillCategory;
import com.example.homeutility.core.enums.FrequencyType;
import com.example.homeutility.core.enums.TransactionType;
import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.UtilityBill;
import com.example.homeutility.service.user.UserService;
import com.example.homeutility.service.utility.UtilityBillService;

@Controller
@RequestMapping("/utilities")
public class UtilityBillController {

    @Autowired
    private UtilityBillService utilityBillService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("bill", new UtilityBill());
        return "utility/add";
    }

    @PostMapping("/add")
    public String saveBill(@ModelAttribute UtilityBill bill, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        bill.setUser(user);
        utilityBillService.saveBill(bill);
        return "redirect:/utilities/list";
    }

    @GetMapping("/list")
    public String viewBills(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        List<UtilityBill> bills = utilityBillService.getBillsByUser(user);
        
        long fixedCount = bills.stream()
            .filter(b -> b.getCategory() == BillCategory.FIXED)
            .count();

        long recurringCount = bills.stream()
            .filter(b -> b.getCategory() == BillCategory.RECURRING)
            .count();


        double credited = bills.stream()
            .filter(b -> b.getTransactionType() == TransactionType.CREDITED)
            .mapToDouble(UtilityBill::getAmount)
            .sum();

        double debited = bills.stream()
            .filter(b -> b.getTransactionType() == TransactionType.DEBITED)
            .mapToDouble(UtilityBill::getAmount)
            .sum();

        double savings = credited - debited;
        
        Map<String, Double> monthlyTotals = bills.stream()
        	.filter(b -> b.getFrequencyType() == FrequencyType.MONTHLY)
        	.collect(Collectors.groupingBy(
        		b -> b.getDueDate().getMonth().toString(),
        		Collectors.summingDouble(UtilityBill::getAmount)
            ));

        Map<String, Double> yearlyTotals = bills.stream()
        	.filter(b -> b.getFrequencyType() == FrequencyType.YEARLY)
        	.collect(Collectors.groupingBy(
        	    b -> String.valueOf(b.getDueDate().getYear()),
        	    Collectors.summingDouble(UtilityBill::getAmount)
        	));
        
        Map<Long, String> dueDateColors = new HashMap<>();

        for (UtilityBill bill : bills) {
            if (bill.getDueDate().isBefore(LocalDate.now())) {
                dueDateColors.put(bill.getId(), "red");
            } else if (bill.getDueDate().isEqual(LocalDate.now())) {
                dueDateColors.put(bill.getId(), "orange");
            } else {
                dueDateColors.put(bill.getId(), "green");
            }
        }
            
            
        model.addAttribute("bills", bills);
        model.addAttribute("fixedCount", fixedCount);
        model.addAttribute("recurringCount", recurringCount);
        model.addAttribute("creditedTotal", credited);
        model.addAttribute("debitedTotal", debited);
        model.addAttribute("savings", savings);
        model.addAttribute("monthlyTotals", monthlyTotals);
        model.addAttribute("yearlyTotals", yearlyTotals);
        model.addAttribute("dueDateColors", dueDateColors);

        return "utility/list";
    }

    @GetMapping("/edit/{id}")
    public String editBill(@PathVariable Long id, Model model) {
        UtilityBill bill = utilityBillService.getBillById(id);
        model.addAttribute("bill", bill);
        return "utility/edit";
    }

    @PostMapping("/update")
    public String updateBill(@ModelAttribute("bill") UtilityBill bill, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        bill.setUser(user);
        utilityBillService.saveBill(bill);
        return "redirect:/utilities/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable Long id) {
        utilityBillService.deleteBill(id);
        return "redirect:/utilities/list";
    }
    
    @GetMapping("/filter")
    public String filterByCategory(@RequestParam("category") BillCategory category,
                                   Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        List<UtilityBill> filteredBills = utilityBillService.getBillsByUser(user).stream()
            .filter(b -> b.getCategory() == category)
            .toList();

        long fixedCount = filteredBills.stream().filter(b -> b.getCategory() == BillCategory.FIXED).count();
        long recurringCount = filteredBills.stream().filter(b -> b.getCategory() == BillCategory.RECURRING).count();

        double credited = filteredBills.stream()
            .filter(b -> b.getTransactionType() == TransactionType.CREDITED)
            .mapToDouble(UtilityBill::getAmount)
            .sum();

        double debited = filteredBills.stream()
            .filter(b -> b.getTransactionType() == TransactionType.DEBITED)
            .mapToDouble(UtilityBill::getAmount)
            .sum();

        double savings = credited - debited;

        Map<String, Double> monthlyTotals = filteredBills.stream()
            .filter(b -> b.getFrequencyType() == FrequencyType.MONTHLY)
            .collect(Collectors.groupingBy(
                b -> b.getDueDate().getMonth().toString(),
                Collectors.summingDouble(UtilityBill::getAmount)
            ));

        Map<String, Double> yearlyTotals = filteredBills.stream()
            .filter(b -> b.getFrequencyType() == FrequencyType.YEARLY)
            .collect(Collectors.groupingBy(
                b -> String.valueOf(b.getDueDate().getYear()),
                Collectors.summingDouble(UtilityBill::getAmount)
            ));

        Map<Long, String> dueDateColors = new HashMap<>();
        for (UtilityBill bill : filteredBills) {
            if (bill.getDueDate().isBefore(LocalDate.now())) {
                dueDateColors.put(bill.getId(), "red");
            } else if (bill.getDueDate().isEqual(LocalDate.now())) {
                dueDateColors.put(bill.getId(), "orange");
            } else {
                dueDateColors.put(bill.getId(), "green");
            }
        }

        model.addAttribute("bills", filteredBills);
        model.addAttribute("fixedCount", fixedCount);
        model.addAttribute("recurringCount", recurringCount);
        model.addAttribute("creditedTotal", credited);
        model.addAttribute("debitedTotal", debited);
        model.addAttribute("savings", savings);
        model.addAttribute("monthlyTotals", monthlyTotals);
        model.addAttribute("yearlyTotals", yearlyTotals);
        model.addAttribute("dueDateColors", dueDateColors);
        model.addAttribute("filterCategory", category);

        return "utility/list";
    }
    
    @GetMapping("/toggle-status/{id}")
    public String togglePaidStatus(@PathVariable Long id) {
        UtilityBill bill = utilityBillService.getBillById(id);
        bill.setPaid(!bill.isPaid()); // Toggle paid status
        utilityBillService.saveBill(bill);
        return "redirect:/utilities/list";
    }
}

