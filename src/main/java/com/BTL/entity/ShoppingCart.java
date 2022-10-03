package com.BTL.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.BTL.service.ProductService;



@Entity
@Table(name="shoppingCart")
public class ShoppingCart implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int shopCartId;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Transient //thuộc tính này không tồn tại trên sq;
	private Double totalPrice;
	
	@Transient
	private int itemsNumber;
	

	@OneToMany( fetch = FetchType.EAGER,mappedBy = "shoppingCart")
	private Set<CartItem> cartItems ;
	
	private String sessionToken;
	
	@Transient
	@Autowired
	ProductService productService;

	public ShoppingCart(int shopCartId, Date date, Double totalPrice, int itemsNumber, Set<CartItem> cartItems,
			String sessionToken) {
		super();
		this.shopCartId = shopCartId;
		this.date = date;
		this.totalPrice = totalPrice;
		this.itemsNumber = itemsNumber;
		this.cartItems = cartItems;
		this.sessionToken = sessionToken;
	} 

	public ShoppingCart() {
		super();
		cartItems =  new HashSet<CartItem>();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCart(int shopCartId, Date date, Set<CartItem> cartItems, String sessionToken) {
		super();
		this.shopCartId = shopCartId;
		this.date = date;
		this.cartItems = cartItems;
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
		for(CartItem item : this.cartItems) {
			Product p = item.getProduct();
			sum = sum + p. getUnitPrice() * item.getQuantity();
		}
		return sum;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getItemsNumber() {
		return this.cartItems.size();
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	} 
	
	
	
	
}
