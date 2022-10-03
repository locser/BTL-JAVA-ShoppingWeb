//package com.BTL.Controller;
//
//import java.util.Optional;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.BTL.dto.AccountCustomerDTO;
//import com.BTL.dto.AccountDTO;
//import com.BTL.dto.CategoryDTO;
//import com.BTL.entity.Account;
//import com.BTL.entity.AccountCustomer;
//import com.BTL.entity.Customer;
//import com.BTL.service.AccountCustomerService;
//import com.BTL.service.AccountService;
//import com.BTL.service.CustomerService;
//
//@Controller
//public class AccountCustomerController {
//	
//	@Autowired
//	AccountCustomerService accountService;
//	
//	@Autowired
//	CustomerService customerService;
//	
//	@Autowired
//	AccountService accountTest;
//	
//	@GetMapping("addCustomer")
//	public String add(Model model) {
//		model.addAttribute("accountCustomer", new AccountCustomerDTO());
//		return "customer/addAndEdit";
//	}
//	
//	@GetMapping("editCustomer/{accountId}")
//	public String edit(Model model,@PathVariable("accountId") String accountId) {
//		Optional<AccountCustomer> optional =  accountService.findById(accountId);
//		AccountCustomerDTO dto = new AccountCustomerDTO();
//		
//		if(optional.isPresent()) {
//			AccountCustomer entity = optional.get();
//			BeanUtils.copyProperties(entity, dto);
//			
//			dto.setIsEdit(true);
//			
//			model.addAttribute("accountCustomer", dto);
//			return "customer/addAndEdit";
//
//		}
//		model.addAttribute("accountCustomer", "not existed");
//		
//		return "/";
//	}
//	
//	@PostMapping("saveAndUpdateCustomer")
//	public String saveAndUpdate(Model model, AccountCustomerDTO accountDTO) {
//		
//		AccountCustomer acc = new AccountCustomer();
//		//get prop form dto to cate
//		BeanUtils.copyProperties(accountDTO, acc);
//		
//		accountService.save(acc);
//		return "redirect:/";
//	}
//	
//	@GetMapping("addCustomerTest")
//	public String addTest(Model model) {
//		model.addAttribute("accountCustomer", new Customer());
//		return "customer/addAndEditTest";
//	}
//	
//	@PostMapping("saveAndUpdateCustomerTest")
//	public String saveAndUpdateTest(Model model, 
//			@RequestParam(name = "usernameCustomer") String userName,
//			@RequestParam(name = "passwordCustomer") String password,
//			@RequestParam(name = "nameCustomer") String name,
//			@RequestParam(name = "phoneCustomer") String phone) {
//		
//		Account acc = new Account();
//		acc.setUsername(userName);
//		acc.setPassword(password);
//		acc.setRole("ROLE_CUSTOMER");
//		accountTest.saveAccountUser(userName, password);
//		
//		Account account = accountTest.getByUsernameTest(userName);
//		
////		Customer customer = new Customer();
////		customer.setCustomeId(1000);
////		customer.setAccount(acc);
////		customer.setEmail(userName);
////		customer.setName(name);
//		
//		customerService.saveCustomerAccount(name, account, userName, phone);
//		return "redirect:/";
//	}
//}
