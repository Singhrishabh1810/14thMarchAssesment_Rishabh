package com.abes.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="abes_order")
public class Order {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="order_id", unique=true, nullable=false)
	private Integer orderId;
	
	@Column(name="order_date")
	private LocalDate orderDate;
	
	@Column(name="order_amt")
	private Double orderAmt;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", orderAmt=" + orderAmt
				+ ", customer=" + (customer != null ? customer.getCustomerName() : "null") + "]";
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public Double getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(Double orderAmt) {
		this.orderAmt = orderAmt;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}