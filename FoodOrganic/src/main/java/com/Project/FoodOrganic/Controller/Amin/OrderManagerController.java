package com.Project.FoodOrganic.Controller.Amin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Project.FoodOrganic.Entity.OrderDetail;
import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.BlogsService;
import com.Project.FoodOrganic.Service.CategoryService;
import com.Project.FoodOrganic.Service.OrderDetailService;
import com.Project.FoodOrganic.Service.OrderService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.StatusProService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
@RequestMapping("/admin/order")
public class OrderManagerController {
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
	
	@GetMapping("/order-processing")
	public String processing(Model model,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		List<Orders> listO = orderService.findAllOrderByStatus("processing");
		model.addAttribute("listO", listO);

		return "ManagerOrder/order-processing";
	}

	@GetMapping("/order-transported")
	public String transported(Model model,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		List<Orders> listO = orderService.findAllOrderByStatus("transported");
		model.addAttribute("listO", listO);

		return "ManagerOrder/order-transported";
	}

	@GetMapping("/order-canceled")
	public String canceled(Model model,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		List<Orders> listO = orderService.findAllOrderByStatus("canceled");
		model.addAttribute("listO", listO);

		return "ManagerOrder/order-canceled";
	}

	@GetMapping("/order-completed")
	public String completed(Model model,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		List<Orders> listO = orderService.findAllOrderByStatus("completed");
		model.addAttribute("listO", listO);

		return "ManagerOrder/order-completed";
	}

	@GetMapping("/detail-order/{id}")
	public String detail_order(@PathVariable Long id, Model model,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		Optional<Orders> o = orderService.findById(id);
		List<OrderDetail> list = orderDetailService.findByOrder(o.get());
		model.addAttribute("listO", list);
		return "ManagerOrder/detail-order";
	}

	@GetMapping("/update-status/{id}")
	public String update_status(@PathVariable Long id, @RequestParam(name = "statusO") Integer status, Model model) {
		Optional<Orders> o = orderService.findById(id);
		
		if (o.isPresent()) {
			if (status == 1) {
				o.get().setStatus("transported");
				orderService.save(o.get());
				return "redirect:/admin/order-transported";
			} else if (status == 2) {
				o.get().setStatus("canceled");
				orderService.save(o.get());
				return "redirect:/admin/order-canceled";
			} else {
				o.get().setStatus("completed");
				orderService.save(o.get());
				return "redirect:/admin/order-completed";
			}
		}

		return "redirect:/admin/order/order-processing";
	}

}