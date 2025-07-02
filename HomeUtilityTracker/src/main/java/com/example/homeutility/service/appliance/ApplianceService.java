package com.example.homeutility.service.appliance;

import java.util.List;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.appliance.Appliance;

public interface ApplianceService {
    void saveAppliance(Appliance appliance);
    List<Appliance> getAppliancesByUser(User user);
    Appliance getById(Long id);
    void delete(Long id);
}