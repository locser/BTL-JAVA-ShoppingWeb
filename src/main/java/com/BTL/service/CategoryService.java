package com.BTL.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.BTL.entity.Category;

public interface CategoryService  {

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	Category getById(int id);

	void deleteAll();

	void delete(Category entity);

	void deleteById(int id);

	int count();

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	Optional<Category> findById(int id);

	List<Category> findAll(Sort sort);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	Page<Category> findAll(Pageable pageable);

	List<Category> findByNameContaining(String name);

}
