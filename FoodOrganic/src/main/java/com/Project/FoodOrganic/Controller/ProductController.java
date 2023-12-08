package com.Project.FoodOrganic.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.Category;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.CartDetailService;
import com.Project.FoodOrganic.Service.CartService;
import com.Project.FoodOrganic.Service.CategoryService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService ;
	@Autowired
	CartDetailService cartDetailService ;   
	@Autowired
	CategoryService cService ;
	@Autowired
	CategoryService categoryService ;

//	@GetMapping("/shop")
//	public String product(Model model ,Authentication auth) {
//		if (auth != null) {
//			User u = userService.findByUsername(auth.getName());
//			Cart cart = cartService.getByUser(u);
//			Long count = cartDetailService.coutByCart(cart);
//			model.addAttribute("count", count);
//		}
//		List<Product> list1 = service.findTop7ByCategoryOrderByProduct_id(1L);
//		List<Product> list2 = service.findTop7ByCategoryOrderByProduct_id(2L);
//		List<Product> list3 = service.findTop7ByCategoryOrderByProduct_id(3L);
//		model.addAttribute("list1", list1);
//		model.addAttribute("list2", list2);
//		model.addAttribute("list3", list3);
//		return "Home/product" ;
//	}
	@GetMapping("/product")
	public String Shop_Product(Model model ,Authentication auth) {
		if (auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
		}
		List<Category> listCate = categoryService.getAll();
		List<Product> listP = productService.getAllProduct();
		model.addAttribute("listCate", listCate);
		model.addAttribute("listP", listP);
		return "Home/shop" ;
	}
	@GetMapping("/product/{cateId}")
	public String Shop_Product(Model model ,Authentication auth,@PathVariable("cateId") Long Cid) {
		if (auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
		}
		Optional<Category> cate = categoryService.findById(Cid);
		List<Category> listCate = categoryService.getAll();
		List<Product> listP = productService.findByCategory(cate.get());
		model.addAttribute("listCate", listCate);
		
		model.addAttribute("listP", listP);
		return "Home/shop"  ;
	}
	@GetMapping("/product/detail/{product_id}")
	public String detail_Product(@PathVariable Long product_id, Model model,Authentication auth) {
		if(auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
		}
		Optional<Product> product = productService.getProductById(product_id);
		List<Category> listC = cService.getAll();
	     if(product.isEmpty()) {
	    	 return"redirect:/home" ;
	    }else {
	    	
			List<Product> list = productService.getAllProductByCategoryId(product.get().getCategory().getId());
			for(Product p : list) {
				if(p.getProduct_id() == product_id) {
					list.remove(p);
					break ;
				}
			}
			
			model.addAttribute("p", product);
			model.addAttribute("listC", listC);
			model.addAttribute("listP", list);

			return "Home/detail-product";
	    }
		

	}
}
