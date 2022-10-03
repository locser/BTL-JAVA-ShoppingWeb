package com.BTL.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BTL.entity.CartItem;
import com.BTL.entity.Product;
import com.BTL.entity.ShoppingCart;
import com.BTL.repo.CartItemRepository;
import com.BTL.repo.ShoppingCartRepository;


import antlr.Utils;



public interface ShoppingCartService {

	void clearShoppingCart(String sessionToken);

	ShoppingCart removeCartIemFromShoppingCart(int cartId, String sessionToken);

	CartItem updateShoppingCartItem(int cartId, int quantity);

	ShoppingCart getShoppingCartBySessionToken(String sessionToken);

	/***
	 * if shopcart exists, get all data from it, and continue
	 * @param productId
	 * @param sessionToken
	 * @param quantity
	 * @return
	 */
	ShoppingCart addtoExistingShoppingCart(int productId, String sessionToken, int quantity);

	/***
	 * if shoppingCart no have a product
	 * @param productId
	 * @param sessionToken
	 * @param quantity
	 * @return
	 */
	ShoppingCart addShoppingCartFirstTime(int productId, String sessionToken, int quantity);

	ShoppingCart getById(Integer id);

	void deleteAll();

	ShoppingCart getOne(Integer id);

	void delete(ShoppingCart entity);

	void deleteById(Integer id);

	long count();

	<S extends ShoppingCart> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends ShoppingCart> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ShoppingCart> S saveAndFlush(S entity);

	Optional<ShoppingCart> findById(Integer id);

	List<ShoppingCart> findAll(Sort sort);

	Page<ShoppingCart> findAll(Pageable pageable);

	List<ShoppingCart> findAll();

	<S extends ShoppingCart> S save(S entity);

	ShoppingCart findBySessionToken(String sessionToken);

	
}
