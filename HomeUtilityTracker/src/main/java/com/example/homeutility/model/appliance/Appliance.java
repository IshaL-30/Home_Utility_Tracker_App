package com.example.homeutility.model.appliance;

import com.example.homeutility.core.model.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String model;

    private int serviceIntervalDays; // e.g., 180

    private LocalDate lastServiceDate;

    private String technicianName;
    private String technicianPhone;

    public LocalDate getNextServiceDate() {
        if (lastServiceDate != null && serviceIntervalDays > 0) {
            return lastServiceDate.plusDays(serviceIntervalDays);
        }
        return null;
    }
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    Getter/Setter
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getServiceIntervalDays() {
		return serviceIntervalDays;
	}

	public void setServiceIntervalDays(int serviceIntervalDays) {
		this.serviceIntervalDays = serviceIntervalDays;
	}

	public LocalDate getLastServiceDate() {
		return lastServiceDate;
	}

	public void setLastServiceDate(LocalDate lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	public String getTechnicianPhone() {
		return technicianPhone;
	}

	public void setTechnicianPhone(String technicianPhone) {
		this.technicianPhone = technicianPhone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}