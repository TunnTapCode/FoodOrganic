package com.Project.FoodOrganic.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.CartDetail;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Repository.CartDetailRepo;



@Service
public class CartDetailService {

	@Autowired
	CartDetailRepo cartDetailRepo ;

	public void Save(CartDetail cartDetail) {
		cartDetailRepo.save(cartDetail) ;
	}
	
	public void Delete(CartDetail cartDetail) {
		cartDetailRepo.delete(cartDetail) ;
	}
	

	
	public List<CartDetail> findByCart(Cart cart){
		return cartDetailRepo.findByCart(cart);
	}
	
	public List<CartDetail> getByProduct(Product product){
		return cartDetailRepo.getByProduct(product);
	}
	public List<CartDetail> findByProduct(Product product){
		return cartDetailRepo.findByProduct(product);
	}
	 
	
}
