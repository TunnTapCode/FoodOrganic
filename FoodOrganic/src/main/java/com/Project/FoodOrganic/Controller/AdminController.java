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
import com.Project.FoodOrganic.Entity.OrderDetail;
import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Entity.StatusProduct;
import com.Project.FoodOrganic.Service.CategoryService;
import com.Project.FoodOrganic.Service.OrderDetailService;
import com.Project.FoodOrganic.Service.OrderService;
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
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	
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
			return "add-product" ;
		} catch (Exception e) {
			Product p = new Product();	
			model.addAttribute("msg1","Thêm sản phẩm thất bại .") ;
			model.addAttribute("Product", p);
			return "add-product" ;
		}
		
	}
	
	@GetMapping("/all-product")
	public String All_Product(Model model) {
		List<Product> listP = productService.getAllProduct();
		List<Category> listC = categoryService.getAll();
		List<StatusProduct> listS = service.findAll();	
		model.addAttribute("listS", listS);
		model.addAttribute("listC", listC);
		model.addAttribute("listP", listP);
		
		return "all-product" ;
	}
	@PostMapping("/update")
	public String update(
			@RequestParam("id") Long id , 
			@RequestParam("name") String name ,
			@RequestParam("image") String image ,
			@RequestParam("description") String description ,
			@RequestParam("price") Double price ,
			@RequestParam("quantity") int quantity ,
			@RequestParam("status") int status ,
			@RequestParam("category") Long category , Model model) {
		
		try {
			Product product = productService.findProductById(id);
		    Optional<Category> cate = categoryService.findById(category);
		    Optional<StatusProduct> sta = service.getById(status);
		    System.out.println(cate.get());
		    
			if(product == null) {
				throw new Exception() ;
			}else {
				product.setCategory(cate.get()) ;
				product.setDescription(description);
				product.setImage(image);
				product.setName(name);
				product.setPrice(price) ;
				product.setQuantity(quantity);
				product.setStatusProduct(sta.get());
				productService.save(product);
				return "redirect:/admin/all-product" ;
			}
			
		} catch (Exception e) {
			return "redirect:/admin/all-product" ;
		}
			
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id ) {
		Product product = productService.findProductById(id);
		try {
			if(product == null) {
				throw new Exception() ;
			}else {
				productService.delete(product);
				return "redirect:/admin/all-product" ;
			}
			
		} catch (Exception e) {
			return "redirect:/admin/all-product" ;
		}
		
		
	}
	
	@GetMapping("/order-processing/")
	public String all_order(Model model,@RequestParam(name = "status" ) String status) {
		List<Orders> listO = orderService.findAllOrderByStatus(status);
		model.addAttribute("listO", listO);
		return "order-processing" ;
	}
	@GetMapping("/detail-order/{id}")
	public String detail_order(@PathVariable Long id , Model model) {
		Optional<Orders> o = orderService.findById(id);
		List<OrderDetail> list = orderDetailService.findByOrder(o.get()) ;
		model.addAttribute("listO", list);
		return "detail-order" ;
	}
	@GetMapping("/update-status/{id}")
	public String update_status(@PathVariable Long id ,@RequestParam(name = "statusO") Integer status, Model model) {
		Optional<Orders> o = orderService.findById(id);
		System.out.println(status);
		System.out.println(o.get());
		if(o.isPresent()) {
			if(status == 1) {
				o.get().setStatus("transported");
				orderService.save(o.get());
				return "redirect:/admin/order-processing/?status=transported" ;
			}else {
				o.get().setStatus("canceled");
				orderService.save(o.get());
				return "redirect:/admin/order-processing/?status=canceled" ;
			}
		}
		
		return "redirect:/admin/order-processing" ;
	}
	
	

}
