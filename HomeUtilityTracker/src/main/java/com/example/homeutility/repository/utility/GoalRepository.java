package com.example.homeutility.repository.utility;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.homeutility.model.utility.Goal;
import com.example.homeutility.core.model.User;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUser(User user);
}
