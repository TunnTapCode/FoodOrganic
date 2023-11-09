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

	@GetMapping({"/home", "/"})
	public String getAllProduct(ModelMap model) {
		List<Product> list1 = service.findTop7ByCategoryOrderByProduct_id(1L);
		List<Product> list2 = service.findTop7ByCategoryOrderByProduct_id(2L);
		List<Product> list3 = service.findTop7ByCategoryOrderByProduct_id(3L);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		return "home";
	}
	

	@GetMapping("/detail/{product_id}")
	public String detail_Product(@PathVariable Long product_id, Model model) {
		Optional<Product> product = service.getProductById(product_id);
	     if(product.isEmpty()) {
	    	 return"redirect:/home" ;
	    }else {
	    	
			List<Product> list = service.getAllProductByCategoryId(product.get().getCategory().getId());
			for(Product p : list) {
				if(p.getProduct_id() == product_id) {
					list.remove(p);
					break ;
				}
			}
			model.addAttribute("p", product);
			model.addAttribute("listP", list);

			return "detail";
	    }
		

	}
	
	

	

}
