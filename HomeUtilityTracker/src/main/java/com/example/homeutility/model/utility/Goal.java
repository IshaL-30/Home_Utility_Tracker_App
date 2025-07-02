package com.example.homeutility.model.utility;

import jakarta.persistence.*;
import com.example.homeutility.core.model.User;

import java.time.LocalDate;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double targetAmount;
    private double savedAmount;

    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

 // Constructors, getters, setters
    
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

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public double getSavedAmount() {
		return savedAmount;
	}

	public void setSavedAmount(double savedAmount) {
		this.savedAmount = savedAmount;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Goal(Long id, String name, double targetAmount, double savedAmount, LocalDate deadline, User user) {
		super();
		this.id = id;
		this.name = name;
		this.targetAmount = targetAmount;
		this.savedAmount = savedAmount;
		this.deadline = deadline;
		this.user = user;
	}

	public Goal() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
