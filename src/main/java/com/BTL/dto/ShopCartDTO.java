package com.BTL.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import com.BTL.entity.CartItem;
import com.BTL.entity.Product;
import com.BTL.service.ProductService;

public class ShopCartDTO implements Serializable{

	int shopCartId;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private Double totalPrice;
	
	private int itemsNumber;
	
	Set<CartItemDTO> cartItemDTOs;
	
	String sessionToken;
	
	@Autowired
	ProductService productService;

	public ShopCartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShopCartDTO(int shopCartId, Date date, Double totalPrice, int itemsNumber, Set<CartItemDTO> cartItems,
			String sessionToken) {
		super();
		this.shopCartId = shopCartId;
		this.date = date;
		this.totalPrice = totalPrice;
		this.itemsNumber = itemsNumber;
		this.cartItemDTOs = cartItems;
		this.sessionToken = sessionToken;
	}

	public int getShopCartId() {
		return shopCartId;
	}

	public void setShopCartId(int shopCartId) {
		this.shopCartId = shopCartId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalPrice() {
		Double sum = 0.0;
		for(CartItemDTO item : this.cartItemDTOs) {
			
			Product p = item.getProduct();
			sum = sum + p. getUnitPrice()*item.getQuantity();
		}
		return sum;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice= totalPrice;
	}

	public int getItemsNumber() {
		return this.cartItemDTOs.size();
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	public Set<CartItemDTO> getCartItemDTOs() {
		return cartItemDTOs;
	}

	public void setCartItemDTOs(Set<CartItemDTO> cartItemDTOs) {
		this.cartItemDTOs = cartItemDTOs;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
	
	
}
