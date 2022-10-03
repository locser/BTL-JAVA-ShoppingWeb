package com.BTL.Controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.BTL.dto.CategoryDTO;
import com.BTL.dto.CustomerDTO;
import com.BTL.entity.Category;
import com.BTL.entity.Customer;
import com.BTL.entity.ShoppingCart;
import com.BTL.globaldata.GlobalData;
import com.BTL.service.CategoryService;
import com.BTL.service.CustomerService;
import com.BTL.service.ProductService;
import com.BTL.service.ShoppingCartService;
import com.BTL.service.StorageService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("admin/customers")
public class CustomerAdController {
    @Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	StorageService storageService;

    @Autowired
	CustomerService customerService;
    
	@Autowired
	ShoppingCartService shoppingCartService;
	
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
	
    @GetMapping("add")
	public String add(Model model) {
		model.addAttribute("customer", new Customer());
		return "admin/customers/addAndEdit2";
	}

    @GetMapping("edit/{customerId}")
	public String edit(@PathVariable("customerId") int customerId, Model model) {
		Optional<Customer> opt =  customerService.findById(customerId);
		CustomerDTO dto = new CustomerDTO();
		
			Customer entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			
			dto.setIsEdit(true);
			
			model.addAttribute("customer", dto);
			return "admin/customers/addAndEdit2";
	}

    @PostMapping("saveAndUpdate")
	public String saveAndUpdate(@ModelAttribute("customer") Customer customer) {
		
//		Category cate = new Category();
//		//get prop form dto to cate
//		BeanUtils.copyProperties(categoryDTO, cate);		

		customerService.save(customer);
		return "redirect:/admin/customers";
	}
	
	@GetMapping("")
	public String list(Model model) {
		List<Customer> listCus= customerService.findAll();
		model.addAttribute("customers", listCus);
		

		return "admin/customers/list";
	}
	
	@GetMapping("delete/{customerId}")
	public String delete(@PathVariable int customerId) {
		customerService.deleteById(customerId);
		return "redirect:/admin/customers";
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="name", required =  false) String namesearch) {
		List<Customer> listSearchByName = new ArrayList<Customer>();
		List<Customer> listSearchByEmail = new ArrayList<Customer>();
		List<Customer> listCus = new ArrayList<Customer>();

		System.out.println("search");
		if(StringUtils.hasText(namesearch)) {
			listSearchByName=customerService.findByNameContaining(namesearch);
			listSearchByEmail=customerService.findByEmailContaining(namesearch);
			for(Customer cus: listSearchByName) {
				listCus.add(cus);
			}
			for(Customer cus: listSearchByEmail){
				if(listCus.contains(cus)) {
					
				}else {
					listCus.add(cus);
				}
			}
		}else {
			listCus=customerService.findAll();
		}
		
		for(Customer cus: listCus) {
			System.out.println(cus.toString());
		}

		model.addAttribute("customers", listCus);
		return "admin/customers/search";
	}

}
