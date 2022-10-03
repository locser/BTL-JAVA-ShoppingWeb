package com.BTL.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderId;
	@Temporal(TemporalType.DATE)
	Date orderDate;
//	@Column(nullable = false)
//	int customerId;
	@Column(nullable = false)
	double amount;
	@Column(nullable = false)
	short status;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customerId")
	Customer customer;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	Set<OrderDetail> orderDetails;
	
	
	@Column(nullable = false, columnDefinition = "nvarchar(500)")
	String deliveryAddress;
	
//	config .properties to create 
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	@Column(columnDefinition = "text")
	String addDetail;
		
	public String getAddDetail() {
		return addDetail;
	}
	public void setAddDetail(String addDetail) {
		this.addDetail = addDetail;
	}
	
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	@Transient
	double totalPrice;
	
	@Transient
	private int itemsNumber;
	
	public Double getTotalPrice() {
		Double sum = 0.0;
		for(OrderDetail item : this.orderDetails) {
			Product p = item.getProduct();
			sum = sum + p. getUnitPrice() * item.getQuantity();
		}
		return sum;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getItemsNumber() {
		return this.orderDetails.size();
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}



	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderId, Date orderDate, double amount, short status) {
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
	public Order(int orderId, Date orderDate, double amount, short status, Customer customer,
			Set<OrderDetail> orderDetails) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.amount = amount;
		this.status = status;
		this.customer = customer;
		this.orderDetails = orderDetails;
	}
	public Order(int orderId, Date orderDate, double amount, short status, String deliveryAddress, String addDetail,
			Customer customer, Set<OrderDetail> orderDetails) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.amount = amount;
		this.status = status;
		this.deliveryAddress = deliveryAddress;
		this.addDetail = addDetail;
		this.customer = customer;
		this.orderDetails = orderDetails;
	}
	
	
	
	
	
}
