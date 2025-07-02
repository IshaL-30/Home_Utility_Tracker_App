package com.example.homeutility.model.utility;

import com.example.homeutility.core.enums.BillCategory;
import com.example.homeutility.core.enums.FrequencyType;
import com.example.homeutility.core.enums.TransactionType;
import com.example.homeutility.core.model.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "utility_bills")
public class UtilityBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., Electricity, Gas, Rent

    private Double amount;

    private LocalDate dueDate;

    private boolean paid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@Enumerated(EnumType.STRING)
	private FrequencyType frequencyType;
	
	@Enumerated(EnumType.STRING)
	private BillCategory category;

//  Getter/Setter

	public BillCategory getCategory() {
		return category;
	}

	public void setCategory(BillCategory category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public FrequencyType getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(FrequencyType frequencyType) {
		this.frequencyType = frequencyType;
	}

	public UtilityBill(Long id, String type, Double amount, LocalDate dueDate, boolean paid, User user,
			TransactionType transactionType, FrequencyType frequencyType, BillCategory category) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.dueDate = dueDate;
		this.paid = paid;
		this.user = user;
		this.transactionType = transactionType;
		this.frequencyType = frequencyType;
		this.category = category;
	}

	public UtilityBill() {
		super();
	}
	
	
    
}