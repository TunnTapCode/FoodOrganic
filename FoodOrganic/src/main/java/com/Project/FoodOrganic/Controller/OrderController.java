package com.Project.FoodOrganic.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Project.FoodOrganic.Entity.OrderDetail;
import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.OrderDetailService;
import com.Project.FoodOrganic.Service.OrderService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService ;
	@Autowired
	UserService userService ;
	@Autowired 
	OrderDetailService orderDetailService ;
	

	@GetMapping("/tracking/{id}")
	public String tracking(Model model,@PathVariable Long id ) {
	    Optional<Orders> o = orderService.findById(id);
		List<OrderDetail> list = orderDetailService.findByOrder(o.get());
		model.addAttribute("id", id) ;
		model.addAttribute("status", o.get().getStatus()) ;
		model.addAttribute("listO", list) ;
		return "UserOrder/order-tracking";
	}
	@GetMapping("/my-order")
	public String test(Model model,Authentication auth) {
		User u = userService.findByUsername(auth.getName());
		
		List<Orders> listO = orderService.findAllOrderByStatusAndU("processing",u.getId());
		List<Orders> list1 = orderService.findAllOrderByStatusAndU("transported",u.getId());
		List<Orders> list3 = orderService.findAllOrderByStatusAndU("canceled",u.getId());
		List<Orders> list2 = orderService.findAllOrderByStatusAndU("completed",u.getId());
		model.addAttribute("user", u);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list1", list1);
		model.addAttribute("listO", listO);
		return "UserOrder/my-order";
	}

}
