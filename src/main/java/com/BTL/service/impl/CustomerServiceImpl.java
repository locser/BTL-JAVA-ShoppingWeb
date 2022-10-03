package com.BTL.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.BTL.entity.Account;
import com.BTL.entity.Customer;
import com.BTL.repo.CustomerRepository;
import com.BTL.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepo;
	
	public CustomerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerServiceImpl(CustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
	}

	@Override
	public <S extends Customer> S save(S entity) {
		return customerRepo.save(entity);
	}


	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	

	@Override
	public Optional<Customer> findById(Integer id) {
		return customerRepo.findById(id);
	}

	

	@Override
	public <S extends Customer> S saveAndFlush(S entity) {
		return customerRepo.saveAndFlush(entity);
	}

	

	@Override
	public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
		return customerRepo.findAll(example, pageable);
	}

	
	@Override
	public long count() {
		return customerRepo.count();
	}

	
	@Override
	public void deleteById(Integer id) {
		customerRepo.deleteById(id);
	}



	@Override
	public void delete(Customer entity) {
		customerRepo.delete(entity);
	}

	

	@Override
	public Customer getOne(Integer id) {
		return customerRepo.getOne(id);
	}

	

	@Override
	public Customer getById(Integer id) {
		return customerRepo.getById(id);
	}


	@Override
	public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
		return customerRepo.findAll(example, sort);
	}


	@Override
	public List<Customer> findByEmailContaining(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
