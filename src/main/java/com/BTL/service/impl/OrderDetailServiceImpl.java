package com.BTL.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.BTL.entity.Customer;
import com.BTL.entity.Order;
import com.BTL.entity.OrderDetail;
import com.BTL.repo.OrderDetailsRepository;
import com.BTL.service.OrderDetailService;
import com.BTL.service.OrderService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailsRepository detailsRepository;

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return detailsRepository.save(entity);
	}

	@Override
	public List<OrderDetail> findAll() {
		return detailsRepository.findAll();
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return detailsRepository.findById(id);
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return detailsRepository.saveAndFlush(entity);
	}

	@Override
	public long count() {
		return detailsRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		detailsRepository.deleteById(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		detailsRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		detailsRepository.deleteAll();
	}

	@Override
	public OrderDetail getById(Integer id) {
		return detailsRepository.getById(id);
	}

	@Override
	public Order viewOrderDetailsAdmin(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Order entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findByCustomer(Customer cus) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
