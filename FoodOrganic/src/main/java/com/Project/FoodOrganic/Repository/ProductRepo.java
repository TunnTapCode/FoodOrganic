package com.Project.FoodOrganic.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.Project.FoodOrganic.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
	
	List<Product> findAllByCategory_id(Long Category_id) ;
	

	

}
