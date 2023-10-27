package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.FoodOrganic.Entity.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
		Role findByName(String name);
	}
