package com.BTL.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="Categories")
public class Category implements Serializable {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int categoryId;
	
	@Column(length=50,columnDefinition = "nvarchar(100) not null")
	String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	Set<Product> products;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Category(int categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "Category [id=" + categoryId + ", name=" + name + "]";
	}
	
	
}
