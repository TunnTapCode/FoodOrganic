package com.Project.FoodOrganic.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Service.ProductService;


@Controller
public class HomeController {
	@Autowired 
	ProductService service ;

	
	@GetMapping("/home")
	
	public String getAllProduct(ModelMap model) {
		
		List<Product> list = service.getAllProduct();
		model.addAttribute("listP", list) ;
		return "home" ;
	}
	@GetMapping("/detail")
    public String detail() {
		
		
		return "detail" ;
	}
	
	
	
	

}
