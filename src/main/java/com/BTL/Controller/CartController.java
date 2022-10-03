package com.BTL.Controller;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BTL.dto.CustomerDTO;
import com.BTL.entity.CartItem;
import com.BTL.entity.Customer;
import com.BTL.entity.Order;
import com.BTL.entity.OrderDetail;
import com.BTL.entity.Product;
import com.BTL.entity.ShoppingCart;
import com.BTL.globaldata.GlobalData;
import com.BTL.service.CartItemService;
import com.BTL.service.CategoryService;
import com.BTL.service.CustomerService;
import com.BTL.service.OrderDetailService;
import com.BTL.service.OrderService;
import com.BTL.service.ProductService;
import com.BTL.service.ShoppingCartService;
import com.BTL.service.StorageService;

import antlr.Utils;
import ch.qos.logback.core.joran.util.beans.BeanUtil;


@Controller
public class CartController {

	@Autowired 
	ProductService productService;
	
	@Autowired 
	CartItemService cartItemService;

	@Autowired 
	ShoppingCartService shoppingCartService;
	
	@Autowired 
	CategoryService categoryService;
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	CustomerService customerService;
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	
	@ModelAttribute("shoppingCart")
	public ShoppingCart getShoppingCart(HttpServletRequest request){
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		
		if(sessionToken == null) {
				
				return new ShoppingCart();
				
			}
			else {
				 return shoppingCartService.getShoppingCartBySessionToken(sessionToken);
				
			}
	}
	
	@ModelAttribute("currentCustomerId")
	public int getCurrentCustomerId() {
		return GlobalData.customerId;
	}
	//5.13 12h48pm
	@PostMapping("/addToCart")
	public String addToCart(HttpServletRequest request, Model model , @RequestParam("productId") int productId, @RequestParam("quantity") int quantity){
		
			String sessionToken =  (String) request.getSession(true).getAttribute("sessionToken");
			
			if(sessionToken == null){
				//sessiontoken trả về trống, tạo 1 session mới
				//~ đồng nghĩa là tạo giỏ mới
				sessionToken = UUID.randomUUID().toString();
				//random chuỗi
				request.getSession().setAttribute("sessionToken", sessionToken);
				
				shoppingCartService.addShoppingCartFirstTime(productId, sessionToken, quantity);
				
			}else{
				// thêm tiếp vào giỏ hiện tại
				shoppingCartService.addtoExistingShoppingCart(productId, sessionToken, quantity);
				
			}
		return "redirect:/";
	}
	
	//5/13/2022 7h53
	
//	@PostMapping("/addToCart/{productId}")
//	public String addToCartFromHomePAge(HttpServletRequest request, Model model , @PathVariable("productId") int productId, int quantity){
//		quantity=1;
//		addToCart(request, model, productId, quantity);
//		String sessionToken =  (String) request.getSession(true).getAttribute("sessionToken");
//		
//		if(sessionToken == null){
//			//sessiontoken trả về trống, tạo 1 session mới
//			//~ đồng nghĩa là tạo giỏ mới
//			sessionToken = UUID.randomUUID().toString();
//			//random chuỗi
//			request.getSession().setAttribute("sessionToken", sessionToken);
//			shoppingCartService.addShoppingCartFirstTime(productId, sessionToken, quantity);
//		}else{
//			// thêm tiếp vào giỏ hiện tại
//			shoppingCartService.addtoExistingShoppingCart(productId, sessionToken, quantity);
//								
//		}
//
//		return "redirect:/";
//	}
	
	
	@GetMapping("/shoppingCart")
	public String showShoppingCartView(HttpServletRequest request, Model model) {
		
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
		
		if(sessionToken == null) {
				
				model.addAttribute("shoppingCart", new ShoppingCart());
				
			}
			else {
				model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
				
			}
		return "/cart/testShoppingCart";
	}
	
	@PostMapping("/updateShoppingCart")
	public String updateCartItem(@RequestParam("cartItemId") int cartItemId,
			@RequestParam("quantity") int quantity) {
		
		shoppingCartService.updateShoppingCartItem(cartItemId,quantity);
		return "redirect:shoppingCart";
	}
	
	@GetMapping("/removeCartItem/{cartItemId}")
	public String removeItem(@PathVariable("cartItemId") int cartItemId, HttpServletRequest request) {
		//lấy giỏ hiện tại
		String sessionToken = (String) request.getSession(false).getAttribute("sessionToken");
		//remove item hiện tại chọn tron giỏ
		shoppingCartService.removeCartIemFromShoppingCart(cartItemId,sessionToken);
		return "redirect:/shoppingCart";
	}
	
	@GetMapping("/clearShoppingCart")
	public String clearShoopiString(HttpServletRequest request) {
		String sessionToken = (String) request.getSession(false).getAttribute("sessionToken");
		request.getSession(false).removeAttribute("sessionToken");
		shoppingCartService.clearShoppingCart(sessionToken);
		return "redirect:/shoppingCart";
	}
	
	//Check out and save order
	@GetMapping("/shoppingCart/checkout")
	public String goToCheckout(HttpServletRequest request , Model model) {
		int customerId= GlobalData.customerId;
		Optional<Customer> opt = customerService.findById(customerId);
		Customer customer = opt.get();
		
//		customerDTO.setCustomerId(customerId);
//		customerDTO.setEmail(customer.getEmail());
//		customerDTO.setPhone(customer.getPhone());
//		BeanUtils.copyProperties(customer, customerDTO);
		
		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
				
				if(sessionToken == null) {
						
						model.addAttribute("shoppingCart", new ShoppingCart());
						model.addAttribute("customer", customer);
					}
					else {
						model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
						model.addAttribute("customer", customer);

					}
		return "checkout/checkout";
		
	}
	
	//confirm order and save database
	@PostMapping("shoppingCart/checkout/confirmOrderandSave")
	public String confirmOrderandSave(HttpServletRequest request, @RequestParam("customerId") int customerId, @RequestParam("name") String name, 
				@RequestParam("email") String email, @RequestParam("deliveryAddress") String deliveryAddress, @RequestParam("phone") String phone, 
				@RequestParam("paymentMethod")int paymentMethod,  @RequestParam("description")String description,
				@ModelAttribute("shoppingCart") ShoppingCart shoppingCart ) {
		String sessionToken =  (String) request.getSession(true).getAttribute("sessionToken");

		if(sessionToken == null) {
			return "shoppingCart";
		}else {
			orderService.confirmOrderandSave(sessionToken, customerId, name, email, deliveryAddress, phone, paymentMethod, description, shoppingCart);
			
			return "redirect:/";
		}
		
	}
	
	//confirm order and save database form buynow page
	@PostMapping("shoppingCart/checkout/confirmOrderandSave3")
	public String confirmOrderandSave3( @RequestParam("customerId") int customerId, @RequestParam("name") String name, 
				@RequestParam("email") String email, @RequestParam("deliveryAddress") String deliveryAddress, @RequestParam("phone") String phone, 
				@RequestParam("paymentMethod")int paymentMethod,  @RequestParam("description") String description,
				@ModelAttribute("product") Product product ) {

		
			orderService.confirmOrderandSave3( customerId, name, email, deliveryAddress, phone, paymentMethod, description, product);
			
			return "redirect:/";
	}
	
	// view orderdetails
	@GetMapping("/admin/orders/view/{orderId}")
	public String viewOrderDetailsAdmin(Model model, @PathVariable("orderId") int orderId) {
		Order order = orderService.viewOrderDetailsAdmin(orderId);
		
		model.addAttribute("order", order);
		
		return "checkout/viewOrder";
	}
	
	//buynow\ not yet
	@GetMapping("/shoppingCart/buynow/checkout/{productId}")
	public String goToBuyNowProd(ModelMap model, @PathVariable("productId") int productId) {
		int quantity =1;
		Product prod = productService.getById(productId);
		
		int customerId= GlobalData.customerId;
		Optional<Customer> opt = customerService.findById(customerId);
		Customer customer = opt.get();
		
		model.addAttribute("customer", customer);
		model.addAttribute("product", prod);
		
		return "checkout/buynow";
		
	}
	
	
	
	//12:10Pm
	
//	@ModelAttribute
//	public void populateModel(Model model, HttpServletRequest request) {
//		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
//
//		if(sessionToken == null) {
//			ShoppingCart shoppingCart = new ShoppingCart();
//			model.addAttribute("shoppingCart", shoppingCart);
//			
//		}
//		else {
//			ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
//
//			model.addAttribute("shoppingCart", shoppingCart);
//			
//		}
//	}





	//5/12 11h04


	
	//custructor
	
	/**
	 * addto cart when user viewing product
	 * @param request
	 * @param model
	 * @param productId
	 * @param quantity
	 * @return
	 */
	//not
//	@GetMapping("/addToCart")
//	public String addtoCartFromViewPorduct(HttpServletRequest request, Model model, int productId, int quantity) {
//		
//		productId=1;
//		quantity=1;
//		//getSession
//		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
//		if(sessionToken == null) {
//			//tao session moi
//			sessionToken = UUID.randomUUID().toString();
//			request.getSession().setAttribute("sessionToken", sessionToken);
//			shoppingCartService.addShoppingCartFirstTime(productId, sessionToken, quantity);
//		}else {
//			shoppingCartService.addtoExistingShoppingCart(productId, sessionToken, quantity);
//		}
//		return "redirect:/";
//	}
	
//	@GetMapping("/addToCart")
//	public String addtoCartFromViewPorduct(HttpServletRequest request, Model model) {
//		
//
//		//getSession
//		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
//		if(sessionToken == null) {
//			//tao session moi
//			sessionToken = UUID.randomUUID().toString();
//			request.getSession().setAttribute("sessionToken", sessionToken);
//			shoppingCartService.addShoppingCartFirstTime(1, sessionToken, 1);
//		}else {
//			shoppingCartService.addtoExistingShoppingCart(1, sessionToken, 1);
//		}
//		return "redirect:/";
//	}
//	
//	@GetMapping("/addToCart/{productId}")
//	public String addtoCartFromHomePage(HttpServletRequest request, Model model,@PathVariable("productId") int productId, int quantity) {
//		quantity=1;
//		
//		//getSession
//		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
//		if(sessionToken == null) {
//			//tao session moi
//			sessionToken = UUID.randomUUID().toString();
//			request.getSession().setAttribute("sessionToken", sessionToken);
//			shoppingCartService.addShoppingCartFirstTime(productId, sessionToken, quantity);
//		}else {
//			shoppingCartService.addtoExistingShoppingCart(productId, sessionToken, quantity);
//		}
//		return "redirect:/";
//	}
	
//	@GetMapping("/shoppingCart")
//	public String showShoppingCartView(HttpServletRequest request, Model model) {
//		//		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
//		//pull data shopping cart to shoppingcart page
//		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
//		
//		
//		
//		if(sessionToken == null) {
//			model.addAttribute("shoppingCart", new ShopCartDTO());
//		}
//		else {
//			
//			// with productId, get set<Product> and set into cartitem and shopping cart
//			ShoppingCart shoppingCart = new ShoppingCart();
//			ShopCartDTO shopCartDTO = new ShopCartDTO();
//			
//			//copy prop from shoppingCart to dto
//			BeanUtils.copyProperties(shoppingCart, shopCartDTO);
//			
//			//get shopcart
//			shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
//			
//			//getcaritem and prod
//			
//			CartItemDTO cartItemDTO_temp= new CartItemDTO();
//			Set<CartItemDTO> setCartItemDTO = new HashSet<CartItemDTO>();
//			
//			//get :get each element in set
//			for( CartItem cartItem : (shoppingCart.getCartItems())) {
//				cartItemDTO_temp.setProduct(productService.getById(cartItem.getProductId()));
//				setCartItemDTO.add(cartItemDTO_temp);
//			}
//			shopCartDTO.setCartItemDTOs(setCartItemDTO);
//			
//			model.addAttribute("shoppingCart", shopCartDTO);
//			
//		}	
//		return "cart/shoppingCart";
//	}
//	
//	@GetMapping("/updateShoppingCart")
//	public String updateCartItem(@RequestParam("cartItemId") int id,
//			@RequestParam("quantity") int quantity) {
//		
//		shoppingCartService.updateShoppingCartItem(id,quantity);
//		return "redirect:shoppingCart";
//	}
//	
//	@GetMapping("/removeCartItem/{cartItemId}")
//	public String removeItem(@PathVariable("cartItemId") int id, HttpServletRequest request) {
//		//get Session
//		String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
//		System.out.println("sessionToken: " + sessionToken);
//		shoppingCartService.removeCartIemFromShoppingCart(id,sessionToken);
//		return "redirect:/shoppingCart";
//	}
//	
//	@GetMapping("/clearShoppingCart")
//	public String clearShoopiString(HttpServletRequest request) {
//		String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
//		request.getSession(false).removeAttribute("sessiontToken");
//		shoppingCartService.clearShoppingCart(sessionToken);
//		return "redirect:/shoppingCart";
//	}
//	
//	@ModelAttribute
//	public void populateModel(Model model, HttpServletRequest request) {
//		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
//		if(sessionToken == null) {
//			model.addAttribute("shoppingCart", new ShoppingCart());
//			
//		}
//		else {
//			model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
//			
//		}		
//		
//	}
	
	
//	//TODO:5h25
//	
//	@ModelAttribute("shoppingCart")
//	public ShopCartDTO getShopCart(){
//		List<ShopCartDTO> =null;
//		listCateDTO= new ArrayList<CategoryDTO>();
//		
//		List<Category> listcate = categoryService.findAll();
//		for(Category cate : listcate) {
//			CategoryDTO dto = new CategoryDTO();
//			BeanUtils.copyProperties(cate, dto);
//			listCateDTO.add(dto);
//		}
//		return listCateDTO;
//	}
	
	
	//5h:24
	
	
//	/**
//	 * 1: get shopcart/2 : set id, quantity,date, productId forcartItem
//	 * 3: add cartitem 
//	 * 4: save shoppingcart with shoppping cart service
//	 * @param request
//	 * @param model
//	 * @param productId
//	 * @param quantity
//	 * @return
//	 */
//	@GetMapping("addToCart")
//	public String addtoCartFromViewPorduct(HttpServletRequest request, Model model, int productId, int quantity) {
//		
//	//getSession
//	String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
//	if(sessionToken == null) {
//		//tao session moi
//		sessionToken = UUID.randomUUID().toString();
//		request.getSession().setAttribute("sessionToken", sessionToken);
//		
//		
//		ShoppingCart shoppingCart = new ShoppingCart();
//
//		//build cartitem
//		CartItem cartItem = new CartItem();
//		
//		//set prop cartItem
//		cartItem.setQuantity(quantity);
//		cartItem.setProductId(productId);
//		cartItem.setDate(new Date());
//		
//		Optional<Product> opt =  productService.findById(productId);
//
//		Product p = opt.get();
//		cartItem.setUnitPrice(p.getUnitPrice());
//		
//		cartItemService.save(cartItem);
//		
//		//set prop Shopping cart
//		shoppingCart.getCartItems().add(cartItem);
//		shoppingCart.setSessionToken(sessionToken);
//		shoppingCart.setDate(new Date());
//		
//		//save truc tiep
//		//shoppingCartService.addShoppingCartFirstTime(productId, sessionToken, quantity);
//		shoppingCartService.save(shoppingCart);
//	}else {
//		ShoppingCart shoppingCart = shoppingCartService.findBySessionToken(sessionToken);
//		//Product p_check = productService.getById(productId);
//		
//				//cartitem have product???
//				Boolean productDoesExistInTheCart = false;
//				
//				//if: shopping cart hava cartitem
//				if (shoppingCart != null) {
//				    Set<CartItem> items = shoppingCart.getCartItems();
//					for (CartItem item : items) {
//						//if shopping have cartitem which contain the same product code, quantity +=
//						//else add new
//						if ((item.getProductId()) == productId ) {
//							productDoesExistInTheCart = true;
//							item.setQuantity(item.getQuantity() + quantity);
//							cartItemService.saveAndFlush(item);
//							shoppingCart.setCartItems(items);
//							//lưu với đầy đủ dữ liệu đã nhớ trong shoppingcarat
//							shoppingCartService.saveAndFlush(shoppingCart);  
//							return "redirect:/";
//						}
//					}
//				}
//				//if shopcart have cartitem, and cartitem have no this product
//				if(!productDoesExistInTheCart && (shoppingCart != null))
//				{
//					CartItem cartItem1 = new CartItem();
//					cartItem1.setDate(new Date());
//					cartItem1.setQuantity(quantity);
//					cartItem1.setProductId(productId);
//					cartItemService.save(cartItem1);
//					shoppingCart.getCartItems().add(cartItem1);
//					shoppingCartService.saveAndFlush(shoppingCart);
//					return "redirect:/";
//				}
//	}
//	return "redirect:/";
//}
//
//	@GetMapping("/addToCart/{productId}")
//	public String addtoCartFromHomePage(HttpServletRequest request, Model model,@PathVariable("productId") int productId, int quantity) {
//		
//		//getSession
//		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
//		if(sessionToken == null) {
//			//tao session moi
//			sessionToken = UUID.randomUUID().toString();
//			request.getSession().setAttribute("sessionToken", sessionToken);
//			
//			
//			ShoppingCart shoppingCart = new ShoppingCart();
//
//			//build cartitem
//			CartItem cartItem = new CartItem();
//			
//			//set prop cartItem
//			cartItem.setQuantity(quantity);
//			cartItem.setProductId(productId);
//			cartItem.setDate(new Date());
//			
//			Optional<Product> opt =  productService.findById(productId);
//
//			Product p = opt.get();
//			cartItem.setUnitPrice(p.getUnitPrice());
//			
//			cartItemService.save(cartItem);
//			
//			//set prop Shopping cart
//			shoppingCart.getCartItems().add(cartItem);
//			shoppingCart.setSessionToken(sessionToken);
//			shoppingCart.setDate(new Date());
//			
//			//save truc tiep
//			//shoppingCartService.addShoppingCartFirstTime(productId, sessionToken, quantity);
//			shoppingCartService.save(shoppingCart);
//		}else {
//			ShoppingCart shoppingCart = shoppingCartService.findBySessionToken(sessionToken);
//			//Product p_check = productService.getById(productId);
//			
//					//cartitem have product???
//					Boolean productDoesExistInTheCart = false;
//					
//					//if: shopping cart hava cartitem
//					if (shoppingCart != null) {
//					    Set<CartItem> items = shoppingCart.getCartItems();
//						for (CartItem item : items) {
//							//if shopping have cartitem which contain the same product code, quantity +=
//							//else add new
//							if ((item.getProductId()) == productId ) {
//								productDoesExistInTheCart = true;
//								item.setQuantity(item.getQuantity() + quantity);
//								cartItemService.saveAndFlush(item);
//								shoppingCart.setCartItems(items);
//								//lưu với đầy đủ dữ liệu đã nhớ trong shoppingcarat
//								shoppingCartService.saveAndFlush(shoppingCart);  
//								return "redirect:/";
//							}
//						}
//					}
//					//if shopcart have cartitem, and cartitem have no this product
//					if(!productDoesExistInTheCart && (shoppingCart != null))
//					{
//						CartItem cartItem1 = new CartItem();
//						cartItem1.setDate(new Date());
//						cartItem1.setQuantity(quantity);
//						cartItem1.setProductId(productId);
//						cartItemService.save(cartItem1);
//						shoppingCart.getCartItems().add(cartItem1);
//						shoppingCartService.saveAndFlush(shoppingCart);
//						return "redirect:/";
//					}
//			shoppingCartService.addtoExistingShoppingCart(1, sessionToken, 1);
//		}
//		return "redirect:/";
//	}
//	
//	@GetMapping("/shoppingCart")
//	public String showShoppingCartView(HttpServletRequest request, Model model) {
//		//		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
//		//pull data shopping cart to shoppingcart page
//		String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
//		
//		
//		if(sessionToken == null) {
//			model.addAttribute("shoppingCart", new ShopCartDTO());
//		}
//		else {
//			// with productId, get set<Product> and set into cartitem and shopping cart
//			ShoppingCart shoppingCart = new ShoppingCart();
//			ShopCartDTO shoppingCartDTO = new ShopCartDTO();
//			
//			//copy prop from shoppingCart to dto
//			BeanUtils.copyProperties(shoppingCart, shoppingCartDTO);
//			
//			//get shopcart
//			shoppingCart = shoppingCartService.getShoppingCartBySessionToken(sessionToken);
//			
//			//getcaritem and prod
//			
//			CartItemDTO cartItemDTO_temp= new CartItemDTO();
//			Set<CartItemDTO> setCartItemDTO = new HashSet<CartItemDTO>();
//			
//			//get :get each element in set
//			for( CartItem cartItem : (shoppingCart.getCartItems())) {
//				cartItemDTO_temp.setProduct(productService.getById(cartItem.getProductId()));
//				setCartItemDTO.add(cartItemDTO_temp);
//			}
//			shoppingCartDTO.setCartItemDTOs(setCartItemDTO);
//
//			
//			model.addAttribute("textShopCart", ("shoppingcart" +shoppingCart.toString()));
//			
//			model.addAttribute("textShopCartDTO", ("shoppingcartDTO" +shoppingCartDTO.toString()));
//
//			
//			model.addAttribute("shoppingCart", shoppingCartDTO);
//			
//		}	
//		return "cart/shoppingCart";
//	}
//	
	
}
