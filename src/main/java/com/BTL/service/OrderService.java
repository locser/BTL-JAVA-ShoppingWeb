package com.BTL.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.BTL.entity.Customer;
import com.BTL.entity.Order;
import com.BTL.entity.OrderDetail;
import com.BTL.entity.Product;
import com.BTL.entity.ShoppingCart;

public interface OrderService {

	<S extends OrderDetail> S saveAndFlush(S entity);

	void deleteById(Integer id);

	void delete(OrderDetail entity);

	Order viewOrderDetailsAdmin(Integer orderId);

	Order getById(Integer id);

	void deleteAll();

	void delete(Order entity);

	long count();

	Optional<Order> findById(Integer id);

	Page<Order> findAll(Pageable pageable);

	List<Order> findAll();

	<S extends Order> S save(S entity);

	List<Order> findByCustomer(Customer cus);


	<S extends Order> S saveAndFlush(S entity);
	
	Order confirmOrderandSave(String sessionToken, int customerId, String name, String email, String deliveryAddress,
			String phone, int paymentMethod, String description, ShoppingCart shoppingCart);

	Order confirmOrderandSave3(int customerId, String name, String email, String deliveryAddress, String phone, int paymentMethod,
			String description, Product product);

	

}
