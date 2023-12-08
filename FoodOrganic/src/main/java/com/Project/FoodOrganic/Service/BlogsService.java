package com.Project.FoodOrganic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.Project.FoodOrganic.Entity.Blogs;
import com.Project.FoodOrganic.Repository.BlogsRepo;

@Service
public class BlogsService {
	
	@Autowired
	BlogsRepo blogsRepo ;
	
	public void save (Blogs blogs) {
		 blogsRepo.save(blogs);
	}
	public List<Blogs> findAll () {
		return blogsRepo.findAll();
	}
	public Optional<Blogs> findById(Long id){
		return blogsRepo.findById(id);
	}
	public void delete (Blogs blogs) {
		 blogsRepo.delete(blogs);
	}

	public List<Blogs> findTop9Blog () {
		return blogsRepo.findTop9NewestBlog();
	}
	
	public Page<Blogs> findAll(Pageable pageable){
		return (Page<Blogs>) blogsRepo.findAll(pageable);
	}

}
