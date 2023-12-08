package com.Project.FoodOrganic.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Project.FoodOrganic.Entity.Category;
import com.Project.FoodOrganic.Entity.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	
	List<Product> findAllByCategory_id(Long Category_id) ;
	

	@Query(value = "SELECT * FROM products p WHERE p.product_id = :product_id", nativeQuery = true)
	Product findProductByProduct_id(@Param("product_id") Long id) ;
	
    
     Page<Product> findAll(Pageable pageable) ;
    
     List<Product> findByCategory(Category category);
     
     long countByCategory(Category category);
    	
   
}
