package com.Project.FoodOrganic.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Project.FoodOrganic.Entity.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	
	List<Product> findAllByCategory_id(Long Category_id) ;
	
	@Query(value = "SELECT * FROM products p WHERE p.category_id = :categoryId "
			+ "ORDER BY p.product_id DESC LIMIT 8", nativeQuery = true)
	List<Product> findTop7NewestProducts(@Param("categoryId") Long categoryId);
	
	@Query(value = "SELECT * FROM products p WHERE p.product_id = :product_id", nativeQuery = true)
	Product findProductByProduct_id(@Param("product_id") Long id) ;
	

}
