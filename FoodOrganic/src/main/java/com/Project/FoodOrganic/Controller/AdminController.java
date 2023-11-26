package com.Project.FoodOrganic.Controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Project.FoodOrganic.Entity.Blogs;
import com.Project.FoodOrganic.Entity.Category;
import com.Project.FoodOrganic.Entity.OrderDetail;
import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Entity.StatusProduct;
import com.Project.FoodOrganic.Service.BlogsService;
import com.Project.FoodOrganic.Service.CategoryService;
import com.Project.FoodOrganic.Service.OrderDetailService;
import com.Project.FoodOrganic.Service.OrderService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.StatusProService;

@Controller
@RequestMapping("/admin")
public class AdminController {
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

	@GetMapping("/dashboard")
	public String admin() {
		return "Dashboard/dashboard";
	}

	@GetMapping("/add-product")
	public String Add_product(Model model) {
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
	public String All_Product(Model model) {
		List<Product> listP = productService.getAllProduct();
		List<Category> listC = categoryService.getAll();
		List<StatusProduct> listS = service.findAll();
		model.addAttribute("listS", listS);
		model.addAttribute("listC", listC);
		model.addAttribute("listP", listP);

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
				return "redirect:/admin/all-product";
			}

		} catch (Exception e) {
			return "redirect:/admin/all-product";
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
				return "redirect:/admin/all-product";
			}

		} catch (Exception e) {
			return "redirect:/admin/all-product";
		}

	}

	@GetMapping("/order-processing")
	public String processing(Model model) {
		List<Orders> listO = orderService.findAllOrderByStatus("processing");
		model.addAttribute("listO", listO);

		return "ManagerOrder/order-processing";
	}

	@GetMapping("/order-transported")
	public String transported(Model model) {
		List<Orders> listO = orderService.findAllOrderByStatus("transported");
		model.addAttribute("listO", listO);

		return "ManagerOrder/order-transported";
	}

	@GetMapping("/order-canceled")
	public String canceled(Model model) {
		List<Orders> listO = orderService.findAllOrderByStatus("canceled");
		model.addAttribute("listO", listO);

		return "ManagerOrder/order-canceled";
	}

	@GetMapping("/order-completed")
	public String completed(Model model) {
		List<Orders> listO = orderService.findAllOrderByStatus("completed");
		model.addAttribute("listO", listO);

		return "ManagerOrder/order-completed";
	}

	@GetMapping("/detail-order/{id}")
	public String detail_order(@PathVariable Long id, Model model) {
		Optional<Orders> o = orderService.findById(id);
		List<OrderDetail> list = orderDetailService.findByOrder(o.get());
		model.addAttribute("listO", list);
		return "ManagerOrder/detail-order";
	}

	@GetMapping("/update-status/{id}")
	public String update_status(@PathVariable Long id, @RequestParam(name = "statusO") Integer status, Model model) {
		Optional<Orders> o = orderService.findById(id);
		System.out.println(status);
		System.out.println(o.get());
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

		return "redirect:/admin/order-processing";
	}

	@GetMapping("/blogs")
	public String all_blog(Model model) {
		List<Blogs> listB = blogsService.findAll();
		Blogs blogs = new Blogs();
		model.addAttribute("blogs", blogs);
		model.addAttribute("listB", listB);
		return "Blog/all-blogs-admin";
	}

	@PostMapping("/add_blogs")
	public String add_blog(@RequestParam(name = "input-file") MultipartFile file, @RequestParam(name = "title") String title,
			@RequestParam(name = "date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
			@RequestParam(name = "tags") String tags,
			@RequestParam(name = "content") String content) throws IOException {
		String image = saveImage(file);
		Blogs blogs = new Blogs();
		blogs.setContent(content);
		blogs.setTags(tags);
		blogs.setCreatedDate(date);
		blogs.setImage(image);
		blogs.getTags();
		blogs.setTitle(title);
		blogsService.save(blogs);
		return "redirect:/admin/blogs";
	}

	@GetMapping("/blogs-detail/{id}")
	public String blog_detail(@PathVariable Long id,Model model) {
		Optional<Blogs> blogs = blogsService.findById(id);
		model.addAttribute("blogs", blogs.get());
		return "Blog/blog-detail-admin";
	}
	@GetMapping("/delete_blogs/{id}")
	public String delete_blog(@PathVariable Long id) {
		 Optional<Blogs> blogs = blogsService.findById(id);
		 if(blogs.get() != null) {
			 blogsService.delete(blogs.get());
			 return "redirect:/admin/blogs";
		 }else {
			 return "redirect:/admin/blogs";
		}
		
	}
	
	private String saveImage(MultipartFile file) throws IOException {
        
        String fileName =  file.getOriginalFilename();
		String uploadDir = "src/main/resources/static/img";

        File uploadPath = new File(uploadDir);
        System.out.println(uploadPath);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        File dest = new File(uploadPath.getAbsolutePath() + "/" + fileName);
		System.out.println(dest);
        file.transferTo(dest);

       
		return "/static/img/" + fileName;
    }

}
