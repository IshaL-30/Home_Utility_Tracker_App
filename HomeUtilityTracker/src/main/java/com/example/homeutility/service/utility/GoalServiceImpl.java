package com.example.homeutility.service.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Goal;
import com.example.homeutility.repository.utility.GoalRepository;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public void createGoal(Goal goal) {
        goalRepository.save(goal);
    }

    @Override
    public List<Goal> getGoalsByUser(User user) {
        return goalRepository.findByUser(user);
    }

    @Override
    public void updateSavedAmount(Long goalId, double amountToAdd) {
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new RuntimeException("Goal not found"));
        goal.setSavedAmount(goal.getSavedAmount() + amountToAdd);
        goalRepository.save(goal);
    }
}
