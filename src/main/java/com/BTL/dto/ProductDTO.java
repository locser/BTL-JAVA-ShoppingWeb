package com.BTL.dto;

import java.io.Serializable;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class ProductDTO implements Serializable {

	int productId;

	@NotEmpty(message="name is not null")
	String name;
	
	int quantity;
	
	@NotEmpty(message="quantity must be big more than 1")
	double unitPrice;
	

	String image;

	MultipartFile imageFile;
	@NotEmpty(message="description is not null")
	
	String description;
	
	short status;
	
	int categoryId;
	
	Boolean isEdit= false;

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(int productId, String name, int quantity, double unitPrice, String image, String description,
			short status, int categoryId) {
		super();
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.image = image;
		this.description = description;
		this.status = status;
		this.categoryId = categoryId;
	}

	public ProductDTO(int productId, String name, int quantity, double unitPrice, String image, String description,
			short status) {
		super();
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.image = image;
		this.description = description;
		this.status = status;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", name=" + name + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + ", image=" + image + ", description=" + description + ", status=" + status
				+ ", categoryId=" + categoryId + "]";
	}

	public ProductDTO(int productId, String name, int quantity, double unitPrice, String image, MultipartFile imageFile,
			String description, short status, int categoryId) {
		super();
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.image = image;
		this.imageFile = imageFile;
		this.description = description;
		this.status = status;
		this.categoryId = categoryId;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}


	
	
	
}
