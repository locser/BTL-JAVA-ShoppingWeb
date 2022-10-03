//package com.BTL.Controller.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.beans.BeanUtils;
//
//import com.BTL.dto.AccountDTO;
//import com.BTL.entity.Account;
//import com.BTL.service.AccountService;
//
//@Controller
//@RequestMapping("/admin/accounts")
//public class AccountController {
//	@Autowired
//	AccountService accountService;
//	
//	@GetMapping("add")
//	public String add(Model model) {
//		model.addAttribute("account", new AccountDTO());
//		return "admin/registerAdmin";
//	}
//	
//	@PostMapping("saveAndUpdate")
//	public String saveAndUpdate(Model model, AccountDTO accountDTO) {
//		
//		Account acc = new Account();
//		//get prop form dto to cate
//		BeanUtils.copyProperties(accountDTO, acc);
//		
//		accountService.save(acc);
//		return "redirect:/";
//	}
//}
