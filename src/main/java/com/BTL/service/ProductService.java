package com.BTL.service;

import java.util.List;
import java.util.Optional;

import com.BTL.entity.Category;
import com.BTL.entity.Product;

public interface ProductService {

	Product getById(Integer id);

	void deleteAll();


	void delete(Product entity);

	void deleteById(Integer id);

	long count();

	Optional<Product> findById(Integer id);

	List<Product> findAll();

	<S extends Product> S save(S entity);

	List<Product> findByNameContaining(String name);

	List<Product> findByCategory(Category category);

}
