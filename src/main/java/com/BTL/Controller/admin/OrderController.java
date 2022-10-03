package com.BTL.Controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BTL.dto.CategoryDTO;
import com.BTL.dto.OrderDTO;
import com.BTL.dto.ProductDTO;
import com.BTL.entity.Category;
import com.BTL.entity.Customer;
import com.BTL.entity.Order;
import com.BTL.entity.Product;
import com.BTL.entity.ShoppingCart;
import com.BTL.globaldata.GlobalData;
import com.BTL.service.CategoryService;
import com.BTL.service.CustomerService;
import com.BTL.service.OrderService;
import com.BTL.service.ProductService;
import com.BTL.service.ShoppingCartService;
import com.BTL.service.StorageService;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {
	
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	StorageService storageService;
	@Autowired
	ShoppingCartService shoppingCartService;
	

    @Autowired
	CustomerService customerService;
    
	@ModelAttribute("currentCustomerId")
	public int getCurrentCustomerId() {
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
	
	@ModelAttribute("categories")
	public List<CategoryDTO> getCategories(){
		List<CategoryDTO> listCateDTO=null;
		listCateDTO= new ArrayList<CategoryDTO>();
		
		List<Category> listcate = categoryService.findAll();
		for(Category cate : listcate) {
			CategoryDTO dto = new CategoryDTO();
			BeanUtils.copyProperties(cate, dto);
			listCateDTO.add(dto);
		}
		return listCateDTO;
	}

	@ModelAttribute("products")
	public List<Product> getProducts(){
		List<ProductDTO> listProdDTO=null;
		listProdDTO= new ArrayList<ProductDTO>();
		
		List<Product> listprod = productService.findAll();
		for(Product prod : listprod) {
			ProductDTO dto = new ProductDTO();
			BeanUtils.copyProperties(prod, dto);
			listProdDTO.add(dto);
		}
		return listprod;
	}
	
	//list orrder
	@GetMapping("")
	public String list(Model model) {
		List<Order> list= orderService.findAll();
		model.addAttribute("orders", list);
		
		return "admin/orders/list";
	}
		
	@PostMapping("saveAndUpdate")
	public String saveAndUpdate(@ModelAttribute("order") OrderDTO dto ) {
		
//		Category cate = new Category();
//		//get prop form dto to cate
//		BeanUtils.copyProperties(categoryDTO, cate);
		Order ordertemp = orderService.getById(dto.getOrderId());
		
		Order order = new Order();
		BeanUtils.copyProperties(dto, order);
		
		//theem thong tin customer id cho order
		Customer customer = new Customer();
		customer.setCustomerId(dto.getCustomerId());
		
		order.setCustomer(customer);
		
		//theem thong tin orderDetails cho order
		order.setOrderDetails(ordertemp.getOrderDetails());
		
		orderService.save(order);
		return "redirect:/admin/orders";
	}


	
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="name", required =  false) String namesearch) {
		List<Customer> listSearchByName = new ArrayList<Customer>();

		List<Order> listOrder = new ArrayList<Order>();
		System.out.println(namesearch);
		if(StringUtils.hasText(namesearch)) {
			listSearchByName=customerService.findByNameContaining(namesearch);
			
			for(Customer cus: listSearchByName){
				List<Order> listorderTemp = orderService.findByCustomer(cus);
				
				for(Order o : listorderTemp) {
					if(listOrder.contains(o)) {
						
					}else {
						listOrder.add(o);
					}
				}
			}
			
		}else {
			listOrder=orderService.findAll();
		}
		

		model.addAttribute("orders", listOrder);
		return "admin/orders/search";
	}
	
	@GetMapping("unpaidOrder")
	public String unpaidOrder(ModelMap model) {
		List<Order> list= orderService.findAll();
		List<Order> listunpaid= new ArrayList<Order>();

		for(Order o : list) {
			if(o.getStatus() == 0) {
				listunpaid.add(o);
			}
		}
		
		model.addAttribute("orders", listunpaid);
		return "/admin/orders/search";
	}
	
	@GetMapping("paidOrder")
	public String paidOrder(ModelMap model) {
		List<Order> list= orderService.findAll();
		List<Order> listpaid= new ArrayList<Order>();

		for(Order o : list) {
			if(o.getStatus() == 1) {
				listpaid.add(o);
			}
		}
		
		model.addAttribute("orders", listpaid);
		return "/admin/orders/search";
	}
	
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name="name", required =  false) String name) {
//		List<Order> list = null;
//		
//		if(StringUtils.hasText(name)) {
//			list=orderService.findByNameContaining(name);
//			
//		}else {
//			list=orderService.findAll();
//		}
//		System.out.println(list.size());
//		model.addAttribute("orders", list);
//		return "admin/orders/search";
//	}
}
