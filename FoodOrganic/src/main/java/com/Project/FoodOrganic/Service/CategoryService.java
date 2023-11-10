package com.Project.FoodOrganic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Category;
import com.Project.FoodOrganic.Repository.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	CategoryRepo categoryRepo ;
	
	public List<Category> getAll(){
		return categoryRepo.findAll();
	}
	public Optional<Category> findById(Long id) {
		return categoryRepo.findById(id);
	}
}
