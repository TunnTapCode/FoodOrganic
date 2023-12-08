package com.Project.FoodOrganic.API;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project.FoodOrganic.Entity.Blogs;
import com.Project.FoodOrganic.Entity.CommentBlog;
import com.Project.FoodOrganic.Entity.Image_Avt;

import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.BlogCommentService;
import com.Project.FoodOrganic.Service.BlogsService;
import com.Project.FoodOrganic.Service.ImageService;
import com.Project.FoodOrganic.Service.ReviewService;
import com.Project.FoodOrganic.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserAPI {
	@Autowired
	UserService userService;
	@Autowired
	ImageService imageService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	BlogCommentService blogCommentService;
	@Autowired
	BlogsService blogsService;

	@GetMapping("/comment-blog/{id}")
	@ResponseBody
	public List<CommentBlog> getAllComment(@PathVariable Long id) {
		Optional<Blogs> blogs = blogsService.findById(id);
		return blogCommentService.findByBlogs(blogs.get());
	}

	@PostMapping("/update-avatar")
	@ResponseBody
	public ResponseEntity<String> updateAvatar(@RequestParam(name = "id") Long id, Authentication auth) {
		try {
			System.out.println(id);
			User u = userService.findByUsername(auth.getName());
			Optional<Image_Avt> image = imageService.findById(id);

			if (u != null) {

				u.setImage_Avt(image.get());
				userService.save(u);
				return ResponseEntity.ok("Số lượng đã được cập nhật.");
			}

			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
		}
	}

}
