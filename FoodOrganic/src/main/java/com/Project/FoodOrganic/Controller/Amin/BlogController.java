package com.Project.FoodOrganic.Controller.Amin;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Project.FoodOrganic.Entity.Blogs;

import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.BlogsService;
import com.Project.FoodOrganic.Service.CategoryService;
import com.Project.FoodOrganic.Service.OrderDetailService;
import com.Project.FoodOrganic.Service.OrderService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.StatusProService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
@RequestMapping("/admin/blogs")
public class BlogController {
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
	
	@GetMapping("/all-blogs")
	public String all_blog(Model model,Authentication auth,@RequestParam("p") Optional<Integer> p) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		org.springframework.data.domain.Pageable pageable = PageRequest.of(p.orElse(0), 12);
		Page<Blogs> page = blogsService.findAll(pageable);
		
		Blogs blogs = new Blogs();
		model.addAttribute("blogs", blogs);
		model.addAttribute("listB", page);
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
		blogs.setTitle(title.toUpperCase());
		blogsService.save(blogs);
		return "redirect:/admin/blogs/all-blogs";
	}

	@GetMapping("/blogs-detail/{id}")
	public String blog_detail(@PathVariable Long id,Model model,Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		Optional<Blogs> blogs = blogsService.findById(id);
		model.addAttribute("blogs", blogs.get());
		return "Blog/blog-detail-admin";
	}
	@GetMapping("/delete_blogs/{id}")
	public String delete_blog(@PathVariable Long id) {
		 Optional<Blogs> blogs = blogsService.findById(id);
		 if(blogs.get() != null) {
			 blogsService.delete(blogs.get());
			 return "redirect:/admin/blogs/all-blogs";
		 }else {
			 return "redirect:/admin/blogs/all-blogs";
		}
		
	}
	
	@PostMapping("/edit_blog")
	public String add_blog( @RequestParam(name = "title_edit") String title,@RequestParam(name = "BlogId") Long id,
			@RequestParam(name = "date_edit")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
			@RequestParam(name = "tag_edit") String tags,
			@RequestParam(name = "content_edit") String content) throws IOException {
	
		Optional<Blogs> blogs = blogsService.findById(id);
		if(blogs != null) {
		blogs.get().setContent(content);
		blogs.get().setTags(tags);
		blogs.get().setCreatedDate(date);
		blogs.get().getTags();
		blogs.get().setTitle(title.toUpperCase());
		blogsService.save(blogs.get());
		return "redirect:/admin/blogs/all-blogs";
		}
		
		return "redirect:/admin/blogs/all-blogs";
	}
	
	private String saveImage(MultipartFile file) throws IOException {
        
        String fileName =  file.getOriginalFilename();
		String uploadDir = "src/main/resources/static/images";

        File uploadPath = new File(uploadDir);
        System.out.println(uploadPath);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        File dest = new File(uploadPath.getAbsolutePath() + "/" + fileName);
		System.out.println(dest);
        file.transferTo(dest);

       
		return "/static/images/" + fileName;
    }

}
