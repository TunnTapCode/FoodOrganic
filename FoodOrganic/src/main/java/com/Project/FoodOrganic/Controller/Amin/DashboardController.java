package com.Project.FoodOrganic.Controller.Amin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.BlogsService;
import com.Project.FoodOrganic.Service.CategoryService;
import com.Project.FoodOrganic.Service.OrderDetailService;
import com.Project.FoodOrganic.Service.OrderService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.StatusProService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
@RequestMapping("/admin")
public class DashboardController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	StatusProService service;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	BlogsService blogsService;
	@Autowired
	UserService userService;
	@GetMapping("/dashboard")
	public String admin(Authentication auth,Model model) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		return "Dashboard/dashboard";
	}
}
