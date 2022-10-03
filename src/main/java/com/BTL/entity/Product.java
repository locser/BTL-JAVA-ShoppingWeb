package com.BTL.entity;

import java.io.Serializable;
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

import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Entity
@Table(name="Products")
public class Product implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6363136654570597514L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productId;
	
	@Column(columnDefinition = "nvarchar(100) not null")
	String name;
	
	@Column(nullable = false)
	int quantity;
	
	@Column(nullable = false)
	double unitPrice;
	
	@Column(length = 200)
	String image;
	
	@Column(columnDefinition = "nvarchar(500)")
	String description;
	
	@Column(nullable= false)
	short status;
	
	@ManyToOne
	@JoinColumn(name ="categoryId")	
	Category category;
	
	@OneToMany(mappedBy = "product")
	Set<OrderDetail> orderDetails;
	
	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Product(int id, String name, int quantity, double unitPrice, String image, String description, short status) {
		super();
		this.productId = id;
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.image = image;
		this.description = description;
		this.status = status;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + ", image=" + image + ", description=" + description + ", status=" + status + ", category="
				+ category + "]";
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Product(int productId, String name, int quantity, double unitPrice, String image, String description,
			short status, Category category, Set<OrderDetail> orderDetails) {
		super();
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.image = image;
		this.description = description;
		this.status = status;
		this.category = category;
		this.orderDetails = orderDetails;
	}

	
	
}
