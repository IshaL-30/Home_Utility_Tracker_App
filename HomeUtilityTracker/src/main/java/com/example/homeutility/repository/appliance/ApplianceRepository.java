package com.example.homeutility.repository.appliance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.homeutility.core.model.User;
import com.example.homeutility.model.appliance.Appliance;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    List<Appliance> findByUser(User user);
}
