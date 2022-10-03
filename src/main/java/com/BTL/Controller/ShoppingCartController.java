//package com.BTL.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import com.BTL.dto.CartItemDTO;
//import com.BTL.entity.ShoppingCart;
//import com.BTL.service.CartItemService;
//import com.BTL.service.ProductService;
//import com.BTL.service.ShoppingCartService;
//
//
//@ControllerAdvice
//public class ShoppingCartController {
//	@Autowired
//	private ShoppingCartService shoppingCartService;
//
//	@Autowired
//	private ProductService productService;
//	
//	@Autowired
//	private CartItemService cartItemService;
//	
//	@ModelAttribute
//	public void populateModel(Model model, HttpServletRequest request) {
//		String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
//	  //String sessionTokenwishList = (String) request.getSession(true).getAttribute("sessiontTokenWishList");
//		if(sessionToken == null) {
//			model.addAttribute("shoppingCart", new ShoppingCart());
//			
//		}
//		else {
//			model.addAttribute("shoppingCart", shoppingCartService.getShoppingCartBySessionToken(sessionToken));
//			
//		}
//	}
//	
//}
