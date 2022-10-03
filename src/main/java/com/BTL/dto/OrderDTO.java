package com.BTL.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class OrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3865732107889522453L;
	@NotEmpty
	int orderId;
	@Temporal(TemporalType.DATE)
	Date orderDate;
	@NotEmpty
	double amount;
	@NotEmpty
	short status;
	@NotEmpty
	String deliveryAddress;
	@NotEmpty
	int customerId;

	
	
	
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public OrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDTO(int orderId, Date orderDate, double amount, short status) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.amount = amount;
		this.status = status;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public OrderDTO(int orderId, Date orderDate, double amount, short status, int customerId, int orderDetails) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.amount = amount;
		this.status = status;
		this.customerId = customerId;
	}
	
	
	
	
	
}
