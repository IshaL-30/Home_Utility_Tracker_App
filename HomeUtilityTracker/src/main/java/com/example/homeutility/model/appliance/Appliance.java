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

	public Appliance(Long id, String name, int serviceIntervalDays, LocalDate lastServiceDate, String technicianName,
			String technicianPhone, User user) {
		super();
		this.id = id;
		this.name = name;
		this.serviceIntervalDays = serviceIntervalDays;
		this.lastServiceDate = lastServiceDate;
		this.technicianName = technicianName;
		this.technicianPhone = technicianPhone;
		this.user = user;
	}

	public Appliance() {
		super();
		// TODO Auto-generated constructor stub
	}
}