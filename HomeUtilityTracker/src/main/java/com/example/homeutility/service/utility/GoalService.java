package com.example.homeutility.service.utility;

import java.util.List;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.utility.Goal;

public interface GoalService {
    void createGoal(Goal goal);
    List<Goal> getGoalsByUser(User user);
    void updateSavedAmount(Long goalId, double amountToAdd);
}
