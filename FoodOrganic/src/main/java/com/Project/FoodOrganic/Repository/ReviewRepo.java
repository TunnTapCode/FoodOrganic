package com.Project.FoodOrganic.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import com.Project.FoodOrganic.Entity.Reviews;

public interface ReviewRepo extends JpaRepository<Reviews, Long>{

	@Query(value = "SELECT * FROM reviews r ORDER BY r.review_date DESC LIMIT 5", nativeQuery = true)
	List<Reviews> findTop5NewestReview();
	
	@Query(value = "SELECT * FROM reviews r ORDER BY r.review_date DESC ", nativeQuery = true)
	List<Reviews> findAllReview();
}
