package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Project.FoodOrganic.Entity.Role;



public interface RoleRepo extends JpaRepository<Role, Long> {
		Role findByName(String name);
	}
