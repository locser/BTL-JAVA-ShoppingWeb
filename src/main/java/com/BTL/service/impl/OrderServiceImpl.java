package com.BTL.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.BTL.entity.CartItem;
import com.BTL.entity.Customer;
import com.BTL.entity.Order;
import com.BTL.entity.OrderDetail;
import com.BTL.entity.Product;
import com.BTL.entity.ShoppingCart;
import com.BTL.repo.CustomerRepository;
import com.BTL.repo.OrderDetailsRepository;
import com.BTL.repo.OrderRepository;
import com.BTL.repo.ShoppingCartRepository;
import com.BTL.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Override
	public Order confirmOrderandSave(String sessionToken , int customerId,  String name, 
			String email, String deliveryAddress,String phone, 
			int paymentMethod, String description, ShoppingCart shoppingCart ) {
		ShoppingCart shoppingCart2 = shoppingCartRepository.findBySessionToken(sessionToken);
		System.out.println(shoppingCart2.getTotalPrice());
		//tao hóa đơn mới
		Order orderNew = new Order();
		
		//set prop ordernew
		orderNew.setCustomer(customerRepository.getById(customerId));
		orderNew.setStatus((short) paymentMethod);
		orderNew.setAddDetail(description);
		orderNew.setAmount(shoppingCart.getItemsNumber());
		orderNew.setOrderDate(new Date());
		orderNew.setDeliveryAddress(deliveryAddress);
		
		orderNew = orderRepository.save(orderNew);
		//tạo các orderdetails mới, 1 order gồm nhiều orderdetail
		
		
		Set<OrderDetail> listOrderDetail= new HashSet<OrderDetail>();
		for(CartItem cartItem : (shoppingCart2.getCartItems())) {

			OrderDetail  orderDetail1 = new OrderDetail();
			orderDetail1.setQuantity(cartItem.getQuantity());
			orderDetail1.setProduct(cartItem.getProduct());
			
			orderDetail1.setOrder(orderRepository.getById(orderNew.getOrderId()));
			
			orderDetailsRepository.save(orderDetail1);
			
			listOrderDetail.add(orderDetail1);
		}
		
		orderNew.setOrderDetails(listOrderDetail);
		return orderRepository.saveAndFlush(orderNew);
	}
	

	@Override
	public Order confirmOrderandSave3( int customerId,  String name, 
			String email, String deliveryAddress,String phone, 
			int paymentMethod, String description,Product product ) {
		Order orderNew = new Order();
		
		//set prop ordernew
		orderNew.setCustomer(customerRepository.getById(customerId));
		orderNew.setStatus((short) paymentMethod);
		orderNew.setAddDetail(description);
		orderNew.setAmount(1);
		orderNew.setOrderDate(new Date());
		orderNew.setDeliveryAddress(deliveryAddress);
		
		orderNew = orderRepository.save(orderNew);
		
		Set<OrderDetail> listOrderDetail= new HashSet<OrderDetail>();

		
		OrderDetail  orderDetail1 = new OrderDetail();
		orderDetail1.setQuantity(1);
		orderDetail1.setProduct(product);
		
		orderDetail1.setOrder(orderRepository.getById(orderNew.getOrderId()));
		
		orderDetailsRepository.save(orderDetail1);
		
		listOrderDetail.add(orderDetail1);
		
		orderNew.setOrderDetails(listOrderDetail);

		return orderRepository.saveAndFlush(orderNew);
	}
	

	public OrderServiceImpl(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	@Override
	public List<Order> findByCustomer(Customer cus){
		return orderRepository.findByCustomer(cus);
		
	}


	@Override
	public <S extends Order> S save(S entity) {
		return orderRepository.save(entity);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	@Override
	public Optional<Order> findById(Integer id) {
		return orderRepository.findById(id);
	}

	@Override
	public long count() {
		return orderRepository.count();
	}

	@Override
	public void delete(Order entity) {
		orderRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		orderRepository.deleteAll();
	}

	@Override
	public Order getById(Integer id) {
		return orderRepository.getById(id);
	}

	@Override
	public Order viewOrderDetailsAdmin(Integer orderId) {
		
		Order order = orderRepository.getById(orderId);
		
		Customer customer = customerRepository.getById(order.getCustomer().getCustomerId());
		
		order.setCustomer(customer);
		
		return order;
	}

	@Override
	public void delete(OrderDetail entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Order> S saveAndFlush(S entity) {
		return orderRepository.saveAndFlush(entity);
	}


	
	
	
	
}
