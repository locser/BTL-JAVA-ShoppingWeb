package com.BTL.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.BTL.entity.CartItem;

public interface CartItemService {

	<S extends CartItem> List<S> findAll(Example<S> example, Sort sort);

	CartItem getById(Integer id);

	void deleteAll();

	CartItem getOne(Integer id);

	void delete(CartItem entity);

	void deleteById(Integer id);

	long count();

	<S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends CartItem> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends CartItem> S saveAndFlush(S entity);

	Optional<CartItem> findById(Integer id);

	List<CartItem> findAll(Sort sort);

	Page<CartItem> findAll(Pageable pageable);

	List<CartItem> findAll();

	<S extends CartItem> S save(S entity);

}
