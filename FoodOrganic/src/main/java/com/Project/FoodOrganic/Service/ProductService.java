package com.Project.FoodOrganic.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Category;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Repository.ProductRepo;

@Service
public class ProductService {
	@Autowired 
	ProductRepo repo ;
	
	public List<Product> getAllProduct(){
		return (List<Product>) repo.findAll();
	}
	
	public Page<Product> findAll(Pageable pageable){
		return (Page<Product>) repo.findAll(pageable);
	}
	public Page<Product> findProductByCate(Pageable pageable,Long cid){
		return (Page<Product>) repo.findProductByCate(pageable,cid);
	}
	public List<Product> getAllProductByCategoryId( Long CategoryId){
		return repo.findAllByCategory_id(CategoryId);
	}
	public Optional<Product> getProductById(Long id) {
		return  repo.findById(id);
		
	}
	
	
	public Product findProductById(Long id) {
		return repo.findProductByProduct_id(id);
	}
	
	public void save (Product product) {
		 repo.save(product);
	}
	public void delete (Product product) {
		 repo.delete(product);
	}
	
	public List<Product> findByCategory(Category category){
		return repo.findByCategory(category);
	}
	

}
