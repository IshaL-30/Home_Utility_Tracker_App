package com.example.homeutility.service.reminder;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.appliance.Appliance;
import com.example.homeutility.model.utility.UtilityBill;
import com.example.homeutility.service.appliance.ApplianceService;
import com.example.homeutility.service.utility.UtilityBillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private UtilityBillService utilityBillService;

    @Autowired
    private ApplianceService applianceService;

    @Override
    public List<String> getRemindersForUser(User user) {
        List<String> reminders = new ArrayList<>();
        LocalDate today = LocalDate.now();

        // Utility Bill Reminders
        List<UtilityBill> bills = utilityBillService.getBillsByUser(user);
        for (UtilityBill bill : bills) {
            if (!bill.isPaid()) {
                long daysLeft = ChronoUnit.DAYS.between(today, bill.getDueDate());
                if (daysLeft <= 3) {
                    reminders.add("⚠️ Your " + bill.getType() + " bill is due on " + bill.getDueDate());
                }
            }
        }

        // Appliance Service Reminders
        List<Appliance> appliances = applianceService.getAppliancesByUser(user);
        for (Appliance appliance : appliances) {
            LocalDate nextService = appliance.getLastServiceDate().plusDays(appliance.getServiceIntervalDays());
            long daysLeft = ChronoUnit.DAYS.between(today, nextService);
            if (daysLeft <= 5) {
                reminders.add("🔧 " + appliance.getName() + " service is due on " + nextService);
            }
        }

        return reminders;
    }
}