package com.BTL.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BTL.entity.Customer;
import com.BTL.entity.ShoppingCart;
import com.BTL.globaldata.GlobalData;
import com.BTL.service.CustomerService;
import com.BTL.service.ShoppingCartService;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
	CustomerService customerService;
    
    @Autowired
    ShoppingCartService shoppingCartService;
    
	@ModelAttribute("currentCustomerId")
	public int getCurrentCustomerId1() {
		return GlobalData.customerId;
	}
    
	
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
	
	@ModelAttribute("customer")
	public Customer getCurrentCustomerId() {
		Customer cus= customerService.findById(GlobalData.customerId).get();
		return cus;
	}
	
	@GetMapping("")
	public String getProfile(Model model) {
		return "profile/editProfile";
	}
	
	@PostMapping("/editProfile")
	public String editProfile(@RequestParam("customerId") int customerId, @RequestParam("name") String name, @RequestParam("phone") String phone,@RequestParam("email") String email ) {
		Short one =(short) GlobalData.customerId;
		Customer cus = new Customer(customerId, name, email, "", phone,one);
		customerService.save(cus);
		return "redirect:/customer/editProfile";
	}
}
