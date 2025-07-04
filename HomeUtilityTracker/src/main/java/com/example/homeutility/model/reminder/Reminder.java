package com.example.homeutility.model.reminder;

import com.example.homeutility.core.model.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDate dueDate;

    @ManyToOne
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reminder(Long id, String message, LocalDate dueDate, User user, String type) {
		super();
		this.id = id;
		this.message = message;
		this.dueDate = dueDate;
		this.user = user;
		
	}

	public Reminder() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
