package com.example.homeutility.controller.appliance;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.appliance.Appliance;
import com.example.homeutility.service.appliance.ApplianceService;
import com.example.homeutility.service.user.UserService;

@Controller
@RequestMapping("/appliances")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("appliance", new Appliance());
        return "appliance/add";
    }

    @PostMapping("/add")
    public String saveAppliance(@ModelAttribute Appliance appliance, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        appliance.setUser(user);
        applianceService.saveAppliance(appliance);
        return "redirect:/appliances/list";
    }

    @GetMapping("/list")
    public String list(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        List<Appliance> appliances = applianceService.getAppliancesByUser(user);

        // Calculate next due date
        
        Map<Long, LocalDate> nextDueDates = new HashMap<>();
        Map<Long, String> dueDateColors = new HashMap<>();

        for (Appliance appliance : appliances) {
            LocalDate nextDueDate = appliance.getLastServiceDate().plusDays(appliance.getServiceIntervalDays());
            nextDueDates.put(appliance.getId(), nextDueDate);

            // Color logic
            if (nextDueDate.isBefore(LocalDate.now())) {
                dueDateColors.put(appliance.getId(), "red");
            } else if (nextDueDate.isEqual(LocalDate.now())) {
                dueDateColors.put(appliance.getId(), "orange");
            } else {
                dueDateColors.put(appliance.getId(), "green");
            }
        }

        model.addAttribute("appliances", appliances);
        model.addAttribute("nextDueDates", nextDueDates);
        model.addAttribute("dueDateColors", dueDateColors);
        
        return "appliance/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        applianceService.delete(id);
        return "redirect:/appliances/list";
    }
    
    @GetMapping("/edit/{id}")
    public String editAppliance(@PathVariable Long id, Model model) {
        Appliance appliance = applianceService.getById(id);
        model.addAttribute("appliance", appliance);
        return "appliance/edit";
    }

    @PostMapping("/update")
    public String updateAppliance(@ModelAttribute Appliance appliance, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        appliance.setUser(user);
        applianceService.saveAppliance(appliance);
        return "redirect:/appliances/list";
    }


}
