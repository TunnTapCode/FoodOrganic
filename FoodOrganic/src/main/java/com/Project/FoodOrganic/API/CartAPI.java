package com.Project.FoodOrganic.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.CartDetail;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.CartDetailService;
import com.Project.FoodOrganic.Service.CartService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.UserService;

@RestController
@RequestMapping("/api")
public class CartAPI {
	@Autowired
	ProductService productService;

	@Autowired
	CartDetailService cartDetailService;
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;

	@PostMapping("/updateQuantity")
	@ResponseBody
	public ResponseEntity<String> updateQuantity(@RequestParam Long id, @RequestParam int quantity,
			Authentication auth) {
		try {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			List<CartDetail> listCD = cartDetailService.findByCart(cart);
			Product p = productService.findProductById(id);

			if (!listCD.isEmpty()) {

				for (int i = 0; i < listCD.size(); i++) {
					if (listCD.get(i).getProduct() == p) {
						listCD.get(i).setQuantity(quantity);
						cartDetailService.Save(listCD.get(i));
						break;

					}
				}

				
				//return ResponseEntity.ok("Số lượng đã được cập nhật.");
			}

			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
		}
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<String> delete_product(@PathVariable Long id, Model model) {
		try {
			Product p = productService.findProductById(id);

			if (p != null) {
				productService.delete(p);
				return ResponseEntity.ok("Số lượng đã được cập nhật.");
			}

			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
		}
	}

}
