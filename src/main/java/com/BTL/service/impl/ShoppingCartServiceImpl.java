package com.BTL.service.impl;

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
import org.springframework.transaction.annotation.Transactional;

import com.BTL.entity.CartItem;
import com.BTL.entity.Product;
import com.BTL.entity.ShoppingCart;
import com.BTL.repo.CartItemRepository;
import com.BTL.repo.ShoppingCartRepository;
import com.BTL.service.ProductService;
import com.BTL.service.ShoppingCartService;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired 
	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired 
	CartItemRepository cartItemRepository;

	public ShoppingCartServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	// build shopping cart
	
	
	
	/***
	 * if shoppingCart no have a product
	 * @param productId
	 * @param sessionToken
	 * @param quantity
	 * @return
	 */
	
	
	@Override
	public ShoppingCart addShoppingCartFirstTime(int productId, String sessionToken, int quantity) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCartRepository.save(shoppingCart);
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(quantity);
		cartItem.setDate(new Date());
		cartItem.setProduct(productService.getById(productId));
		
		cartItem.setShoppingCart(shoppingCart);
		
		
		shoppingCart.setSessionToken(sessionToken);
		shoppingCart.setDate(new Date());
		
		
		shoppingCart.getCartItems().add(cartItem);
		cartItemRepository.save(cartItem);
		return shoppingCartRepository.save(shoppingCart);
	}


	/***
	 * if shopcart exists, get all data from it, and continue
	 * @param productId
	 * @param sessionToken
	 * @param quantity
	 * @return
	 */
	
	@Override
	public ShoppingCart addtoExistingShoppingCart(int productId,String sessionToken, int quantity) {
		ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
		
		Product p_check = productService.getById(productId);
		
		//cartitem have product???
		Boolean productDoesExistInTheCart = false;
		
		//if: shopping cart hava cartitem
		if (shoppingCart != null) {
		    Set<CartItem> cartItems = shoppingCart.getCartItems();
			for (CartItem item : cartItems) {
				//if shopping have cartitem which contain the same product code, quantity +=
				//else add new
				if ((item.getProduct().getProductId())== productId ) {
					productDoesExistInTheCart = true;
					//cartItem update quanity for prod
					item.setQuantity(item.getQuantity() + quantity);
					cartItemRepository.save(item);
					
					
					shoppingCart.setCartItems(cartItems);
					//lưu với đầy đủ dữ liệu đã nhớ trong shoppingcarat
					return shoppingCartRepository.saveAndFlush(shoppingCart);  
				}
			}
		}
		//if shopcart have cartitem, and cartitem have no this product
		if(!productDoesExistInTheCart && (shoppingCart != null))
			//if shopcart is null, addto firstitem
		{
			CartItem cartItem1 = new CartItem();
			
			cartItem1.setDate(new Date());
			cartItem1.setQuantity(quantity);
			Product prod = productService.getById(productId);
			cartItem1.setProduct(prod);
			
			cartItem1.setShoppingCart(shoppingCart);
			
			cartItemRepository.save(cartItem1);

			
			shoppingCart.getCartItems().add(cartItem1);
			return shoppingCartRepository.saveAndFlush(shoppingCart);
		}
		//if not, create a shoppingcart new ~ add first
		return this.addShoppingCartFirstTime(productId, sessionToken, quantity);
	}
	
	@Override
	public ShoppingCart getShoppingCartBySessionToken(String sessionToken) {
		
		return  shoppingCartRepository.findBySessionToken(sessionToken);
	}

	@Override
	@Transactional
	public CartItem updateShoppingCartItem(int cartItemId, int quantity) {
		CartItem cartItem = cartItemRepository.getById(cartItemId);
		cartItem.setQuantity(quantity);
		return cartItemRepository.saveAndFlush(cartItem);
	}
	
	@Override
	@Transactional
	public ShoppingCart removeCartIemFromShoppingCart(int cartItemId, String sessionToken) {
		ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
		Set<CartItem> cartItems = shoppingCart.getCartItems();
		
		//cartitem temp
		CartItem cartItem = null;
		
		for(CartItem item : cartItems) {
			//if shopping cart find a cartitem == cartId need remove
			if(item.getCartItemId()==cartItemId) {
				cartItem = item;
			}
		}
		//remove and update shopping cart
		cartItems.remove(cartItem);
		cartItemRepository.delete(cartItem);
	    shoppingCart.setCartItems(cartItems);
	    
	    return shoppingCartRepository.save(shoppingCart);
	}

	@Override
	@Transactional
	public void clearShoppingCart(String sessionToken) {
		ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
		for(CartItem i : (shoppingCart.getCartItems())) {
			cartItemRepository.delete(i);
		}
		shoppingCartRepository.delete(shoppingCart);
		
	}

	@Override
	public ShoppingCart findBySessionToken(String sessionToken) {
		return shoppingCartRepository.findBySessionToken(sessionToken);
	}

	@Override
	public <S extends ShoppingCart> S save(S entity) {
		return shoppingCartRepository.save(entity);
	}

	@Override
	public List<ShoppingCart> findAll() {
		return shoppingCartRepository.findAll();
	}

	@Override
	public Page<ShoppingCart> findAll(Pageable pageable) {
		return shoppingCartRepository.findAll(pageable);
	}

	@Override
	public List<ShoppingCart> findAll(Sort sort) {
		return shoppingCartRepository.findAll(sort);
	}

	@Override
	public Optional<ShoppingCart> findById(Integer id) {
		return shoppingCartRepository.findById(id);
	}

	@Override
	public <S extends ShoppingCart> S saveAndFlush(S entity) {
		return shoppingCartRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends ShoppingCart> List<S> saveAllAndFlush(Iterable<S> entities) {
		return shoppingCartRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ShoppingCart> Page<S> findAll(Example<S> example, Pageable pageable) {
		return shoppingCartRepository.findAll(example, pageable);
	}

	@Override
	public long count() {
		return shoppingCartRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		shoppingCartRepository.deleteById(id);
	}

	@Override
	public void delete(ShoppingCart entity) {
		shoppingCartRepository.delete(entity);
	}

	@Override
	public ShoppingCart getOne(Integer id) {
		return shoppingCartRepository.getOne(id);
	}

	@Override
	public void deleteAll() {
		shoppingCartRepository.deleteAll();
	}

	@Override
	public ShoppingCart getById(Integer id) {
		return shoppingCartRepository.getById(id);
	}
	
	
}
