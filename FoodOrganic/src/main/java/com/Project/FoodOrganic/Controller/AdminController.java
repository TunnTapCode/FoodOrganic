package com.Project.FoodOrganic.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Project.FoodOrganic.Entity.Category;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Entity.StatusProduct;
import com.Project.FoodOrganic.Service.CategoryService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.StatusProService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	CategoryService categoryService ;
	@Autowired 
	ProductService productService ;
	@Autowired
	StatusProService service ;
	
	@GetMapping("/dashboard")
	public String admin() {
		return "dashboard" ;
	}
	
	@GetMapping("/add-product")
	public String Add_product(Model model) {
		Product p = new Product();
		List<Category> listC = categoryService.getAll();
		model.addAttribute("Product", p);
		model.addAttribute("listC", listC);
		return "add-product" ;
	}
	@PostMapping("/add-product")
	
	public String Add(@ModelAttribute Product product , @RequestParam("category") Long id,Model model) {
		try {
			Optional<Category> c = categoryService.findById(id);
			Optional<StatusProduct> s = service.getById(1);
			
			product.setCategory(c.get());
			product.setStatusProduct(s.get());
			productService.save(product);
			Product p = new Product();	
			model.addAttribute("Product", p);
			model.addAttribute("msg","Thêm sản phẩm thành công .") ;
			return "redirect:/admin/add-product" ;
		} catch (Exception e) {
			Product p = new Product();	
			model.addAttribute("msg1","Thêm sản phẩm thất bại .") ;
			model.addAttribute("Product", p);
			return "redirect:/admin/add-product" ;
		}
		
	}
	
	@GetMapping("/all-product")
	public String All_Product(Model model) {
		List<Product> listP = productService.getAllProduct();
		
		model.addAttribute("listP", listP);
		
		return "all-product" ;
	}
	@GetMapping("/update/{id}")
	public String update_Product(@PathVariable("id") Long id ,Model model) {
		try {
			Product product = productService.findProductById(id);
			if(product == null) {
				throw new Exception() ;
			}else {
				model.addAttribute("p", product);
				return "update-produt" ;
			}
			
		} catch (Exception e) {
			return "redirect:/admin/all-product" ;
		}
			
	}
	

}
