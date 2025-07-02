package com.example.homeutility.service.appliance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.appliance.Appliance;
import com.example.homeutility.repository.appliance.ApplianceRepository;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    public void saveAppliance(Appliance appliance) {
        applianceRepository.save(appliance);
    }

    public List<Appliance> getAppliancesByUser(User user) {
        return applianceRepository.findByUser(user);
    }

    public Appliance getById(Long id) {
        return applianceRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        applianceRepository.deleteById(id);
    }
}