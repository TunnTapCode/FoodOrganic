package com.Project.FoodOrganic.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Repository.CartRepo;

@Service
public class CartService {
	
	@Autowired
	CartRepo cartRepo ;
	
	public void Save(Cart cart) {
		 cartRepo.save(cart);
	}
	public Cart getByUser(User user) {
		return cartRepo.getByUser(user) ;
	}

}
