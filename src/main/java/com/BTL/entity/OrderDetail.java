package com.BTL.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderdetails")
public class OrderDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7850817751526423651L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderDetailId;
	
	@Column(nullable = false)
	int quantity;
	
	@Column(nullable = false)
	double unitPrice;
	
	@ManyToOne
	@JoinColumn(name="productId")
	Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@ManyToOne
	@JoinColumn(name="orderId")
	private Order order;
	
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetail(int orderDetailId,  int quantity, double unitPrice) {
		super();
		this.orderDetailId = orderDetailId;
//		this.orderId = orderId;
//		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	public OrderDetail(int orderDetailId, int quantity, double unitPrice, Product product, Order order) {
		super();
		this.orderDetailId = orderDetailId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.product = product;
		this.order = order;
	}
	public int getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
//	public int getOrderId() {
//		return orderId;
//	}
//	public void setOrderId(int orderId) {
//		this.orderId = orderId;
//	}
//	public int getProductId() {
//		return productId;
//	}
//	public void setProductId(int productId) {
//		this.productId = productId;
//	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice() {
		this.unitPrice = this.quantity * this.product.getUnitPrice();
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}
