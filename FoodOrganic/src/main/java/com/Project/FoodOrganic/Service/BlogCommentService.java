package com.Project.FoodOrganic.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Blogs;
import com.Project.FoodOrganic.Entity.CommentBlog;
import com.Project.FoodOrganic.Repository.BlogCommentRepo;

@Service
public class BlogCommentService {

	@Autowired
	BlogCommentRepo blogCommentRepo ;
	
	public void save(CommentBlog commentBlog) {
		blogCommentRepo.save(commentBlog);
	}
	
	public List<CommentBlog> findByBlogs(Blogs blogs){
		return blogCommentRepo.findByBlogs(blogs);
	}
	
}
