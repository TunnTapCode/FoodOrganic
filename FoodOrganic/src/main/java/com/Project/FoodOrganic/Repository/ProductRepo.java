package com.Project.FoodOrganic.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.Project.FoodOrganic.Entity.Category;
import com.Project.FoodOrganic.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
	
	List<Product> findAllByCategory_id(Long Category_id) ;
	

	@Query(value = "SELECT * FROM products p WHERE p.product_id = :product_id", nativeQuery = true)
	Product findProductByProduct_id(@Param("product_id") Long id) ;
	
    
     Page<Product> findAll(Pageable pageable) ;
    
     List<Product> findByCategory(Category category);
     
     @Query(value = "SELECT * FROM products p WHERE p.category_id = :category_id", nativeQuery = true)
     Page<Product> findProductByCate(Pageable pageable,@Param("category_id") Long cid) ;
    	
   
}
