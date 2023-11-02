package com.Project.FoodOrganic.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService service;

	@GetMapping("/home")

	public String getAllProduct(ModelMap model) {

		List<Product> list1 = service.getAllProductByCategoryId(1L);
		List<Product> list2 = service.getAllProductByCategoryId(2L);
		List<Product> list3 = service.getAllProductByCategoryId(3L);

		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		return "home";
	}

	@GetMapping("/detail/{id}")
	public String detail_Product(@PathVariable Long id, Model model) {
		Optional<Product> product = service.getProductById(id);

		List<Product> list = service.getAllProductByCategoryId(product.get().getCategory().getId());

		model.addAttribute("p", product);
		model.addAttribute("listP", list);

		return "detail";

	}

	@GetMapping("/detail1/1")
	public String detail() {
		
		return "detail";

	}

}
