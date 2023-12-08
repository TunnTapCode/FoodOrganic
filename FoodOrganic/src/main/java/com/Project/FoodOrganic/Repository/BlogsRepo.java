package com.Project.FoodOrganic.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.Project.FoodOrganic.Entity.Blogs;



public interface BlogsRepo extends JpaRepository<Blogs, Long>{

	@Query(value = "SELECT * FROM blogs b ORDER BY b.id ASC LIMIT 9", nativeQuery = true)
	List<Blogs> findTop9NewestBlog();
	
	@Query(value = "SELECT * FROM blogs b ORDER BY b.id ASC ", nativeQuery = true)
    Page<Blogs> findAll(Pageable pageable) ;
}
