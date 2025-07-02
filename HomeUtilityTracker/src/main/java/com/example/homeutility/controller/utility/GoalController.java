package com.example.homeutility.controller.utility;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Goal;
import com.example.homeutility.service.user.UserService;
import com.example.homeutility.service.utility.GoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String showAddGoalForm(Model model) {
        model.addAttribute("goal", new Goal());
        return "goals/add";
    }

    @PostMapping("/add")
    public String saveGoal(@ModelAttribute Goal goal, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        goal.setUser(user);
        goal.setSavedAmount(0.0);
        goalService.createGoal(goal);
        return "redirect:/goals/list";
    }

    @GetMapping("/list")
    public String viewGoals(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElse(null);
        model.addAttribute("goals", goalService.getGoalsByUser(user));
        return "goals/list";
    }

    @PostMapping("/save/{id}")
    public String addToGoal(@PathVariable Long id, @RequestParam double amount) {
        goalService.updateSavedAmount(id, amount);
        return "redirect:/goals/list";
    }
}
