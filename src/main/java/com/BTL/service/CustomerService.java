package com.BTL.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BTL.entity.Customer;


public interface CustomerService {

	<S extends Customer> List<S> findAll(Example<S> example, Sort sort);

	Customer getById(Integer id);

	Customer getOne(Integer id);

	void delete(Customer entity);

	void deleteById(Integer id);

	long count();

	<S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Customer> S saveAndFlush(S entity);

	List<Customer> findAll();

	<S extends Customer> S save(S entity);


	Optional<Customer> findById(Integer id);

	List<Customer> findByEmailContaining(String name);

	List<Customer> findByNameContaining(String name);

}
