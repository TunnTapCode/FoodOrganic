package com.Project.FoodOrganic.Service;

import java.util.List;
import java.util.Optional;

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
	public List<Product> getAllProductByCategoryId( Long CategoryId){
		return repo.findAllByCategory_id(CategoryId);
	}
	public Optional<Product> getProductById(Long id) {
		return  repo.findById(id);
		
	}
	public List<Product> findTop7ByCategoryOrderByProduct_id(Long id ){
		return repo.findTop7NewestProducts( id);
	}
	
	public Product findProductById(Long id) {
		return repo.findProductByProduct_id(id);
	}
	
	public void save (Product product) {
		 repo.save(product);
	}
	

}
