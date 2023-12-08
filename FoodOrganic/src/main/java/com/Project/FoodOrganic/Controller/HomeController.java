package com.Project.FoodOrganic.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.CartDetailService;
import com.Project.FoodOrganic.Service.CartService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	
	@Autowired 
	CartService cartService;
	@Autowired
	UserService userService ;
	@Autowired
	CartDetailService cartDetailService ;
	
	
	
	

	@GetMapping({"/home", "/",""})
	public String getAllProduct(Model model,Authentication auth) {	
		if(auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
		}
		List<Product> list1 = productService.findTop7ByCategoryOrderByProduct_id(1L);
		List<Product> list2 = productService.findTop7ByCategoryOrderByProduct_id(2L);
		List<Product> list3 = productService.findTop7ByCategoryOrderByProduct_id(3L);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("auth", auth);
		return "Home/home";
	}
	


	
	
	

}
