package com.BTL.Controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.catalina.loader.ResourceEntry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.BTL.dto.CategoryDTO;
import com.BTL.dto.ProductDTO;
import com.BTL.entity.Category;
import com.BTL.entity.Product;
import com.BTL.entity.ShoppingCart;
import com.BTL.globaldata.GlobalData;
import com.BTL.service.CategoryService;
import com.BTL.service.ProductService;
import com.BTL.service.ShoppingCartService;
import com.BTL.service.StorageService;

@Controller
@RequestMapping("admin/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	StorageService storageService;
	
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
	
	@ModelAttribute("currentCustomerId")
	public int getCurrentCustomerId() {
		return GlobalData.customerId;
	}
	
	/***
	 * isEdit set with true when admin is editing
	 * @param model
	 * @return
	 */
	@GetMapping("add")
	public String add(Model model) {
		ProductDTO dto= new ProductDTO();
		
		dto.setIsEdit(false);
		
		model.addAttribute("product",dto );
		return "admin/products/addAndEdit2";
	}
	
	@GetMapping("goSearch")
	public String goSearch(Model model) {
		
		return "admin/products/search";
	}
	
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
	
	
	@GetMapping("edit/{productId}")
	public String edit(@PathVariable("productId") int productId, Model model) {
		Optional<Product> opt =  productService.findById(productId);
		ProductDTO dto = new ProductDTO();
		
			Product entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setCategoryId(entity.getCategory().getCategoryId());
			
			dto.setIsEdit(true);
			
			model.addAttribute("product", dto);
			return "admin/products/addAndEdit2";
	}
	
	@PostMapping("saveAndUpdate")
	public String saveAndUpdate(Model model,@ModelAttribute("product") ProductDTO dto ) {
		
//		Category cate = new Category();
//		//get prop form dto to cate
//		BeanUtils.copyProperties(categoryDTO, cate);
		

		Product product = new Product();
		BeanUtils.copyProperties(dto, product);
		
		//theem thong tin category cho product
		Category category = new Category();
		category.setCategoryId(dto.getCategoryId());
		
		product.setCategory(category);
		
		//check anrh ton tai hay khong, luwu hinh 
		if(!dto.getImageFile().isEmpty()) {
			UUID uuid =  UUID.randomUUID();//cho phep sinh ra 1 day ki tu nhan dang
			String uuString = uuid.toString();
			
			product.setImage(storageService.getStoredFilename(dto.getImageFile(), uuString));
			storageService.store(dto.getImageFile(), product.getImage());
		}
		productService.save(product);
		return "redirect:/admin/products";
	}
	
	@GetMapping("")
	public String list(Model model) {
		List<Product> list= productService.findAll();
		model.addAttribute("products", list);
		for(Product cate : list) {
			System.out.println(cate.toString());
		}
		return "admin/products/list";
	}
	
	@GetMapping("delete/{productId}")
	public String delete(@PathVariable int productId) {
		productService.deleteById(productId);
		return "redirect:/admin/products";
	}
	
//	@GetMapping("search")
//	public String search(Model model, String name) {
//		List<Category> list = null;
//		
//		if(StringUtils.hasText(name)) {
//			list=categoryService.findByNameContaining(name);
//			
//		}else {
//			list=categoryService.findAll();
//		}
//		System.out.println(list.size());
//		model.addAttribute("categories", list);
//		return "admin/categories/search";
//	}
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="name", required =  false) String name) {
		List<Product> list = null;
		
		if(StringUtils.hasText(name)) {
			list=productService.findByNameContaining(name);
			
		}else {
			list=productService.findAll();
		}
		System.out.println(list.size());
		model.addAttribute("products", list);
		return "admin/products/search";
	}
	
	/***
	 * read received file name and return body of file name
	 * đọc nội dung tên tệp đã nhận và trả về nội dung của tên tệp 
	 * sử dụng storage gọi load as Res
	 * body(file) xác định nội dung của file truyền vào
	 * @ResponseBody : không trả về teamplate view ma sử dụng trự ctieesp 
	 * @param filename
	 * @return
	 */
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, 
					"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
}
