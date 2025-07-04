package com.example.homeutility.repository.reminder;

import com.example.homeutility.model.reminder.Reminder;
import com.example.homeutility.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByUser(User user);
}
