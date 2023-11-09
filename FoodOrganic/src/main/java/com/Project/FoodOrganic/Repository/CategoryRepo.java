package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.Category;

import java.util.Optional;


public interface CategoryRepo extends JpaRepository<Category, Long> {
   Optional<Category> findById(Long id);
}
