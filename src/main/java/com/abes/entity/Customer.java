package com.abes.entity;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="abes_customer")
public class Customer {
	
	@Id
	@Column(name="Customer_id")
	private Integer customerId;
	
	@Column(name="Customer_name", nullable=false, length=45)
	private String customerName;
	
	@OneToMany(mappedBy="customer")
	private Set<Order> orderSet;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Set<Order> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}
}