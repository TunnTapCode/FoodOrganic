package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.Blogs;
import com.Project.FoodOrganic.Entity.CommentBlog;
import java.util.List;


public interface BlogCommentRepo extends JpaRepository<CommentBlog, Long>{

	List<CommentBlog> findByBlogs(Blogs blogs);
}
