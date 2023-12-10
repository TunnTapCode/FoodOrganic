package com.Project.FoodOrganic.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String Shop_Product(Model model ,Authentication auth,@RequestParam("page") Optional<Integer> p) {
		if (auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
		}
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		Page<Product> page =  productService.findAll(pageable);
		List<Category> listCate = cService.getAll();
		model.addAttribute("listCate", listCate);
		model.addAttribute("listP", page);
		return "Home/shop" ;
	}
	@GetMapping("/product/{cateId}")
	public String Shop_Product(Model model ,Authentication auth,@PathVariable("cateId") Long Cid,@RequestParam("page") Optional<Integer> p) {
		if (auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
		}
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		Page<Product> page =  productService.findProductByCate(pageable,Cid);
		List<Category> listCate = cService.getAll();
		System.out.println(page.getTotalElements());
		model.addAttribute("listCate", listCate);
		model.addAttribute("id", Cid);
		model.addAttribute("listP", page);
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
				if(p.getProduct_id() == product_id || p.getQuantity() == 0) {
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
