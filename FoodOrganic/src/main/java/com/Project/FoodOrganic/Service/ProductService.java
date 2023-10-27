package com.Project.FoodOrganic.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Repository.ProductRepo;

@Service
public class ProductService {
	@Autowired 
	ProductRepo repo ;
	
	public List<Product> getAllProduct(){
		return (List<Product>) repo.findAll();
	}

}
