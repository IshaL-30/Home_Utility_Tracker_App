package com.example.homeutility.controller.reminder;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.homeutility.core.model.User;
import com.example.homeutility.service.reminder.ReminderService;
import com.example.homeutility.service.user.UserService;

@Controller
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showReminders(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        List<String> reminders = reminderService.getRemindersForUser(user);
        model.addAttribute("reminders", reminders);
        return "reminder/list"; // You can change this to your desired Thymeleaf page
    }
}
