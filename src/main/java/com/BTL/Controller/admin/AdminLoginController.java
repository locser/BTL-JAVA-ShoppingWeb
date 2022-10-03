//package com.BTL.Controller.admin;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.BTL.dto.AdminLoginDTO;
//import com.BTL.entity.Account;
//import com.BTL.service.AccountService;
//
//@Controller
//public class AdminLoginController {
//	@Autowired
//	private AccountService accountService;
//	
//	@Autowired
//	private HttpSession httpSession;
//	
//	@GetMapping("alogin")
//	public String login(ModelMap model) {
//		model.addAttribute("account", new AdminLoginDTO());
//		return "/admin/accounts/login";
//	}
//	
//	@GetMapping("aloginC")
//	public ModelAndView login(ModelMap model,@ModelAttribute("account") AdminLoginDTO dto, BindingResult result) {
//		if(result.hasErrors()) {
//			return new ModelAndView("/admin/accounts/login", model);
//		}
//		
//		Account account = accountService.login(dto.getUsername(), dto.getPassword());
//		
//		if(account == null) {
//			model.addAttribute("message", "Invalid username or password");
//			return new ModelAndView("/admin/accounts/login", model);
//		}
//		
//		Object ruri = httpSession.getAttribute("redirect-url");
//		
//		if(ruri != null) {
//			httpSession.removeAttribute("redirect-uri");
//			return new ModelAndView("redirect:" + ruri);
//		}
//		
//		httpSession.setAttribute("username", account.getUsername());
//		return new ModelAndView("forward:/", model);
//	}
//}
