package com.Project.FoodOrganic.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Reviews;
import com.Project.FoodOrganic.Repository.ReviewRepo;

@Service
public class ReviewService {

	@Autowired
	ReviewRepo repo;
	
	public List<Reviews> findAll (){
		return repo.findAllReview();
	}
	public void save(Reviews reviews) {
		repo.save(reviews);
	}
	
	public List<Reviews> findTop5NewestReview (){
		return repo.findTop5NewestReview();
	}
	
}
