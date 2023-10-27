package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
