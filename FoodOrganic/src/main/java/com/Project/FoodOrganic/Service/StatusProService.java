package com.Project.FoodOrganic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.StatusProduct;
import com.Project.FoodOrganic.Repository.StatusProRepo;

@Service
public class StatusProService {
	@Autowired 
	StatusProRepo statusProRepo ;
	
	public Optional<StatusProduct> getById(Integer id) {
		return statusProRepo.findById(id);
	}
	public List<StatusProduct> findAll() {
		return statusProRepo.findAll();
	}
	
}
