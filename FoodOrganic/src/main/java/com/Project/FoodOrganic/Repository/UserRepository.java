package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
    List<User> findByEmail(String email);
}
