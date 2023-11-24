package com.Project.FoodOrganic.Controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Entity.Reviews;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.CartDetailService;
import com.Project.FoodOrganic.Service.CartService;
import com.Project.FoodOrganic.Service.OrderService;
import com.Project.FoodOrganic.Service.ReviewService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
public class ReviewController {
	
	@Autowired
	OrderService orderService ;
	@Autowired
	UserService userService ;
	@Autowired
	CartService cartService;
	@Autowired
	CartDetailService cartDetailService;
	@Autowired
	ReviewService reviewService ;
	
	@GetMapping("/review")
	public String review(Model model,Authentication auth) {

		List<Reviews> listAll = reviewService.findAll();	
		List<Reviews> list5 = reviewService.findTop5NewestReview();	
		
		model.addAttribute("all", listAll);
		model.addAttribute("list5", list5);
		model.addAttribute("auth", auth);
		if(auth != null) {
			User u = userService.findByUsername(auth.getName());
			List<Orders> list = orderService.findAllOrderByStatusAndU("completed", u.getId());
			
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
			if(list.size() != 0) {
			model.addAttribute("size", list.size());	
			}
			return "Home/reviews";
		}
		
		
		return "Home/reviews";
	}
	
	@PostMapping("/comment")
	public String save_comment(@RequestParam(name = "star") Integer star , @RequestParam(name = "comment") String comment,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		Date date = new Date();

		Reviews reviews = new Reviews();
		reviews.setComment(comment);
		reviews.setReview_date(date);
		reviews.setUser(user);
		reviews.setStar(star);
		reviewService.save(reviews);
		return "redirect:/review" ;
	}
	

}
