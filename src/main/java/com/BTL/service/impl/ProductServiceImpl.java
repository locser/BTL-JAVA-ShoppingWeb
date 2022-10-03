package com.BTL.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BTL.entity.Category;
import com.BTL.entity.Product;
import com.BTL.repo.CategoryRepository;
import com.BTL.repo.ProductRepository;
import com.BTL.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepository.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		Product product = productRepository.getById(id);
		product.setCategory(new Category());
		productRepository.delete(product);
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public Product getById(Integer id) {
		return productRepository.getById(id);
	}

	@Override
	public List<Product> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByNameContaining(name);
	}
	
	@Override
	public List<Product> findByCategory(Category category){
		return productRepository.findByCategory(category);
	}

	
}
