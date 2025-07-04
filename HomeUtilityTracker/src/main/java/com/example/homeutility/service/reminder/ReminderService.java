package com.example.homeutility.service.reminder;

import com.example.homeutility.core.model.User;
import java.util.List;

public interface ReminderService {
    List<String> getRemindersForUser(User user);
}
