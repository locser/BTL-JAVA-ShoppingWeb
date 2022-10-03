package com.BTL.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BTL.entity.CartItem;
import com.BTL.entity.Product;
import com.BTL.repo.CartItemRepository;
import com.BTL.repo.ShoppingCartRepository;
import com.BTL.service.CartItemService;
import com.BTL.service.ProductService;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired 
	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired 
	CartItemRepository cartItemRepository;

	@Override
	public <S extends CartItem> S save(S entity) {
		return cartItemRepository.save(entity);
	}

	@Override
	public List<CartItem> findAll() {
		return cartItemRepository.findAll();
	}

	@Override
	public Page<CartItem> findAll(Pageable pageable) {
		return cartItemRepository.findAll(pageable);
	}

	@Override
	public List<CartItem> findAll(Sort sort) {
		return cartItemRepository.findAll(sort);
	}

	@Override
	public Optional<CartItem> findById(Integer id) {
		return cartItemRepository.findById(id);
	}

	@Override
	public <S extends CartItem> S saveAndFlush(S entity) {
		return cartItemRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends CartItem> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cartItemRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cartItemRepository.findAll(example, pageable);
	}

	@Override
	public long count() {
		return cartItemRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		cartItemRepository.deleteById(id);
	}

	@Override
	public void delete(CartItem entity) {
		cartItemRepository.delete(entity);
	}

	@Override
	public CartItem getOne(Integer id) {
		return cartItemRepository.getOne(id);
	}

	@Override
	public void deleteAll() {
		cartItemRepository.deleteAll();
	}

	@Override
	public CartItem getById(Integer id) {
		return cartItemRepository.getById(id);
	}

	@Override
	public <S extends CartItem> List<S> findAll(Example<S> example, Sort sort) {
		return cartItemRepository.findAll(example, sort);
	}
	
	
	
}
