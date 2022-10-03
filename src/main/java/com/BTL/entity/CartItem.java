package com.BTL.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;
@Entity
@Table(name="cartItem")
public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6084012818459427138L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cartItemId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shopCartId")
	ShoppingCart shoppingCart;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	
	int quantity;

	public CartItem(int cartItemId, Product product, Date date, int quantity) {
		super();
		this.cartItemId = cartItemId;
		this.product = product;
		this.date = date;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
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

	@Override
	public int hashCode() {
		return Objects.hash(cartItemId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		return cartItemId == other.cartItemId;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	
}
