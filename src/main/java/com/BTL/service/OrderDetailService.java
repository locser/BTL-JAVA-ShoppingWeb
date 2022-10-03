package com.BTL.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.BTL.entity.Customer;
import com.BTL.entity.Order;
import com.BTL.entity.OrderDetail;

public interface OrderDetailService {

	List<Order> findByCustomer(Customer cus);

	Page<Order> findAll(Pageable pageable);

	void delete(Order entity);

	Order viewOrderDetailsAdmin(Integer orderId);

	OrderDetail getById(Integer id);

	void deleteAll();

	void delete(OrderDetail entity);

	void deleteById(Integer id);

	long count();

	<S extends OrderDetail> S saveAndFlush(S entity);

	Optional<OrderDetail> findById(Integer id);

	List<OrderDetail> findAll();

	<S extends OrderDetail> S save(S entity);

}
