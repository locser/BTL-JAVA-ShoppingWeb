package com.BTL.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BTL.entity.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	List<Customer> findByNameContaining(String name);
	List<Customer> findByEmailContaining(String name);

}
