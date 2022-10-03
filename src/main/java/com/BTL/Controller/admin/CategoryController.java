package com.BTL.Controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BTL.dto.CategoryDTO;
import com.BTL.entity.Category;
import com.BTL.entity.ShoppingCart;
import com.BTL.globaldata.GlobalData;
import com.BTL.service.CategoryService;
import com.BTL.service.ShoppingCartService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
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
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDTO());
		return "admin/categories/addAndEdit2";
	}
	
	@GetMapping("edit/{categoryId}")
	public String edit(@PathVariable("categoryId") int categoryId, Model model) {
		Optional<Category> opt =  categoryService.findById(categoryId);
		CategoryDTO dto = new CategoryDTO();
		
		
			Category entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			
			dto.setIsEdit(true);
			
			model.addAttribute("category", dto);
			return "admin/categories/addAndEdit2";
	}
	
	@PostMapping("saveAndUpdate")
	public String saveAndUpdate(Model model,  @Valid @ModelAttribute("category") CategoryDTO categorydto, BindingResult result) {
		if(result.hasErrors()) {
			return "admin/categories/addAndEdit2";
		}
		Category cate = new Category();
		//get prop form dto to cate
		BeanUtils.copyProperties(categorydto, cate);
		
		categoryService.save(cate);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("")
	public String list(Model model) {
		List<Category> listCate= categoryService.findAll();
		model.addAttribute("categories", listCate);
		
		return "admin/categories/list";
	}
	
	@GetMapping("delete/{categoryId}")
	public String delete(@PathVariable int categoryId) {
		categoryService.deleteById(categoryId);
		return "redirect:/admin/categories";
	}
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="name", required =  false) String name) {
		List<Category> list = null;
		
		if(StringUtils.hasText(name)) {
			list=categoryService.findByNameContaining(name);
			
		}else {
			list=categoryService.findAll();
		}
		System.out.println(list.size());
		model.addAttribute("categories", list);
		return "admin/categories/search";
	}
}
