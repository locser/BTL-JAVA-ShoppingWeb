package com.BTL.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.BTL.entity.Product;

public class CartItemDTO implements Serializable{
	/**
	 * 
	 */

	int cartItemId;
	
	Product product;
	@Temporal(TemporalType.DATE)
	Date date;
	
	int quantity;
	double unitPrice;
	public CartItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItemDTO(int cartItemId, Product product, Date date, int quantity, double unitPrice) {
		super();
		this.cartItemId = cartItemId;
		this.product = product;
		this.date = date;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	
}
