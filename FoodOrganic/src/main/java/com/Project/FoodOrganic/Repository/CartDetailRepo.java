package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.CartDetail;
import java.util.List;
import com.Project.FoodOrganic.Entity.Product;

public interface CartDetailRepo extends JpaRepository<CartDetail, Long> {
	List<CartDetail> findByCart(Cart cart);

	List<CartDetail> getByProduct(Product product);

	List<CartDetail> findByProduct(Product product);
	long countByCart(Cart cart);

}
