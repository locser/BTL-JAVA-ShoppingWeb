package com.BTL.Controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

import com.BTL.dto.CategoryDTO;
import com.BTL.dto.ProductDTO;
import com.BTL.entity.Category;
import com.BTL.entity.Post;
import com.BTL.entity.Product;
import com.BTL.entity.ShoppingCart;
import com.BTL.globaldata.GlobalData;
import com.BTL.service.CartItemService;
import com.BTL.service.CategoryService;
import com.BTL.service.PostService;
import com.BTL.service.ProductService;
import com.BTL.service.ShoppingCartService;

@Controller
@RequestMapping("")
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	PostService postService;
	
	@GetMapping("")
	public String showHome(Model model) {
		//get All product, get All category to show homepage
		return "home/homepage2";
	}
	
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
	
	@ModelAttribute("posts")
	public List<Post> getAllPosts(){
		List<Post> listPost=null;
		
		listPost = postService.findAll();

		return listPost;
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
	
	@GetMapping("view/{productId}")
	public String view(@PathVariable("productId") int productId, Model model ) {
		Optional<Product> opt =  productService.findById(productId);
		Product product = opt.get();
		
		model.addAttribute("product", product);
		return "home/view";
	}
	
	@GetMapping("searchProductFromHomepage")
	public String search(ModelMap model, @RequestParam(name="name", required =  false) String name) {
		List<Product> list = null;
		
		if(StringUtils.hasText(name)) {
			list=productService.findByNameContaining(name);
			
		}else {
			list=productService.findAll();
		}
		System.out.println(list.size());
		model.addAttribute("products", list);
		return "/home/homeSearch";
	}
	
	@GetMapping("searchProductByCategory/{categoryId}")
	public String searchProductByCategory(@PathVariable("categoryId") int categoryId, Model model){
		List<Product> list = null;
		
		Category category = new Category();
		category.setCategoryId(categoryId);
		
		list = productService.findByCategory(category);
		model.addAttribute("products", list);
		return "home/homepage2";
	}
	
	@GetMapping("searchProductByPrice")
	public String searchProductByPrice(ModelMap model, @RequestParam(name="minPrice", required =  false) double minPrice,  @RequestParam(name="maxPrice", required =  false) double maxPrice ) {
		if(maxPrice >0) {
			List<Product> list = getProducts();
			List<Product> listProdByPrice = new ArrayList<Product>();

			for(Product prod : list) {
				if(prod.getUnitPrice() <= maxPrice && prod.getUnitPrice() >=minPrice) {
					listProdByPrice.add(prod);
				}
			}
			
			model.addAttribute("products", listProdByPrice);
		}
		
		
		return "home/homepage2";
	}
	
	@GetMapping("sortByNameAsc/{optionId}")
	public String sortbyNameAsc (@PathVariable("optionId") int optionId, Model model) {
		return "";
	}
	
	
	@GetMapping("/loginTest")
	public String login() {
		
		return "login";
	}
	
//	
//	@PostMapping("/addToCart/{productId}")
//	public String addToCartFromHomePAge(HttpServletRequest request, Model model , @PathVariable("productId") int productId){
//		int quantity=1;
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
//		return "home/homepage";
//	}
}
