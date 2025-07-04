package com.example.homeutility.model.utility;

import jakarta.persistence.*;
import com.example.homeutility.core.model.User;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private double monthlyExpenseLimit;

 // Constructors, getters, setters
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getMonthlyExpenseLimit() {
		return monthlyExpenseLimit;
	}

	public void setMonthlyExpenseLimit(double monthlyExpenseLimit) {
		this.monthlyExpenseLimit = monthlyExpenseLimit;
	}

	public Wallet(Long id, double balance, User user, double monthlyExpenseLimit) {
		super();
		this.id = id;
		this.balance = balance;
		this.user = user;
		this.monthlyExpenseLimit = monthlyExpenseLimit;
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
