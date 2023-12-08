package com.Project.FoodOrganic.Controller.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Project.FoodOrganic.Entity.Blogs;
import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.CommentBlog;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.BlogCommentService;
import com.Project.FoodOrganic.Service.BlogsService;
import com.Project.FoodOrganic.Service.CartDetailService;
import com.Project.FoodOrganic.Service.CartService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
@RequestMapping("/user/blogs")
public class BlogUserController {

	@Autowired
	BlogsService blogsService ;
	@Autowired
	UserService userService ;
	@Autowired
	CartDetailService cartDetailService ;
	@Autowired 
	CartService cartService ;
	@Autowired
	BlogCommentService blogCommentService;
	@GetMapping()
	public String getAllBlog(Authentication auth ,Model model,@RequestParam("p") Optional<Integer> p) {
		if(auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
			model.addAttribute("auth", auth);
		}
		org.springframework.data.domain.Pageable pageable = PageRequest.of(p.orElse(0), 12);
		Page<Blogs> page = blogsService.findAll(pageable);
		model.addAttribute("listB", page);
		return "Blog/blog-user";
	}
	@GetMapping("/blogs-detail/{id}")
	public String blog_detail(@PathVariable("id") Long id,Authentication auth ,Model model) {
		if(auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
			model.addAttribute("auth", auth);
		}
		Optional<Blogs> blogs = blogsService.findById(id);
		List<CommentBlog> listCB = blogCommentService.findByBlogs(blogs.get());
		
		model.addAttribute("listcmt", listCB);
		model.addAttribute("auth", auth);
		model.addAttribute("blog", blogs.get());
		return "Blog/blog-detail-user";
	}
	
	@PostMapping("/comment")
	public String SaveComment(@RequestParam(name = "blogId") Long blogId ,
			                  @RequestParam(name = "comment") String comment,Authentication auth
			                  ,Model model) {
		
		User u = userService.findByUsername(auth.getName());
		Optional<Blogs> blog = blogsService.findById(blogId);
		Date date = new Date();
		CommentBlog commentBlog = new CommentBlog();
		commentBlog.setBlogs(blog.get());
		commentBlog.setComment(comment);
		commentBlog.setDate_comment(date);
		commentBlog.setUser(u);
		blogCommentService.save(commentBlog);
		
		
		
		
		return "redirect:/user/blogs/blogs-detail/" + blogId;
	}
	
	
	
}
