package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.User;




public interface  CartRepo extends JpaRepository<Cart, Long>{
	 Cart getByUser(User user) ;
	 

}
