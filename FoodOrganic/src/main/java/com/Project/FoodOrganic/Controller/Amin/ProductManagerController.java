package com.Project.FoodOrganic.Controller.Amin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
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
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.BlogsService;
import com.Project.FoodOrganic.Service.CategoryService;
import com.Project.FoodOrganic.Service.OrderDetailService;
import com.Project.FoodOrganic.Service.OrderService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.StatusProService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
@RequestMapping("/admin/product")
public class ProductManagerController {
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

	

	@GetMapping("/add-product")
	public String Add_product(Model model,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		Product p = new Product();
		List<Category> listC = categoryService.getAll();
		model.addAttribute("Product", p);
		model.addAttribute("listC", listC);
		return "ManagerProduct/add-product";
	}

	@PostMapping("/add-product")

	public String Add(@ModelAttribute Product product, @RequestParam("category") Long id, Model model) {
		try {
			Optional<Category> c = categoryService.findById(id);
			Optional<StatusProduct> s = service.getById(1);

			product.setCategory(c.get());
			product.setStatusProduct(s.get());
			productService.save(product);
			Product p = new Product();
			model.addAttribute("Product", p);
			model.addAttribute("msg", "Thêm sản phẩm thành công .");
			return "ManagerProduct/add-product";
		} catch (Exception e) {
			Product p = new Product();
			model.addAttribute("msg1", "Thêm sản phẩm thất bại .");
			model.addAttribute("Product", p);
			return "ManagerProduct/add-product";
		}

	}


	
	@GetMapping("/all-product")
	public String paging(Model model,@RequestParam("page") Optional<Integer> p,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		List<Category> listC = categoryService.getAll();
		List<StatusProduct> listS = service.findAll();
		org.springframework.data.domain.Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<Product> page = productService.findAll(pageable);
		model.addAttribute("listS", listS);
		model.addAttribute("listC", listC);
		model.addAttribute("listP", page);
		
		
		return "ManagerProduct/all-product";
		
	}

	@PostMapping("/update")
	public String update(@RequestParam("id") Long id, @RequestParam("name") String name,
			@RequestParam("image") String image, @RequestParam("description") String description,
			@RequestParam("price") Double price, @RequestParam("quantity") int quantity,
			@RequestParam("status") int status, @RequestParam("category") Long category, Model model) {

		try {
			Product product = productService.findProductById(id);
			Optional<Category> cate = categoryService.findById(category);
			Optional<StatusProduct> sta = service.getById(status);
			System.out.println(cate.get());

			if (product == null) {
				throw new Exception();
			} else {
				product.setCategory(cate.get());
				product.setDescription(description);
				product.setImage(image);
				product.setName(name);
				product.setPrice(price);
				product.setQuantity(quantity);
				product.setStatusProduct(sta.get());
				productService.save(product);
				return "redirect:/admin/product/all-product";
			}

		} catch (Exception e) {
			return "redirect:/admin/product/all-product";
		}

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Product product = productService.findProductById(id);
		try {
			if (product == null) {
				throw new Exception();
			} else {
				productService.delete(product);
				return "redirect:/admin/product/all-product";
			}

		} catch (Exception e) {
			return "redirect:/admin/product/all-product";
		}

	}

	
	
}
