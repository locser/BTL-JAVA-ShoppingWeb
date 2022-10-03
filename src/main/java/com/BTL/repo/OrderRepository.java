package com.BTL.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BTL.entity.Customer;
import com.BTL.entity.Order;
import com.BTL.entity.Product;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
//	List<Order> findByNameContaining(String name);
	List<Order> findByCustomer(Customer cus);
}
