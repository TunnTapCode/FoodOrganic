package com.Project.FoodOrganic.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService service;

	@GetMapping("/product")
	public String product(Model model) {
		List<Product> list1 = service.findTop7ByCategoryOrderByProduct_id(1L);
		List<Product> list2 = service.findTop7ByCategoryOrderByProduct_id(2L);
		List<Product> list3 = service.findTop7ByCategoryOrderByProduct_id(3L);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		return "product" ;
	}
}
