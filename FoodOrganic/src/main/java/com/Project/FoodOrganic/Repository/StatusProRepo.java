package com.Project.FoodOrganic.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.StatusProduct;

public interface StatusProRepo extends JpaRepository<StatusProduct, Integer> {
  
 Optional<StatusProduct> findById(Integer id) ;
}
