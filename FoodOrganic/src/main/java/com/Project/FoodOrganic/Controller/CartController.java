package com.Project.FoodOrganic.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.CartDetail;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.CartDetailService;
import com.Project.FoodOrganic.Service.CartService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.UserService;


@Controller
@RequestMapping("/cart")

public class CartController {
	
	@Autowired
	ProductService productService ;
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	CartDetailService cartDetailService ;
	
	@GetMapping()
	public String showcart(Model model) {
		User u = userService.findByUsername(userService.getUsername());
		Cart cart = cartService.getByUser(u);
		List<CartDetail> list = cartDetailService.findByCart(cart) ;
		model.addAttribute("listP", list);
		return "cart";
		
	}
	@GetMapping("/{id}")
	public String cartHome(@PathVariable("id") Long id) {
		
		Product p = productService.findProductById(id);
		User u = userService.findByUsername(userService.getUsername());
		Cart cart = cartService.getByUser(u);
		CartDetail cartDetail = null;
		if(cart == null) {
			Cart cart1 = new Cart();
			cart1.setUser(u);
			cartService.Save(cart1);
			cartDetail = new CartDetail(cart1, p, 1, p.getPrice(), p.getImage());
			cartDetailService.Save(cartDetail);
			return "redirect:/cart";
		}else {
			
			List<CartDetail> listC = cartDetailService.getByProduct(p);
				if(listC.isEmpty()) {
					 cartDetail = new CartDetail(cart, p, 1, p.getPrice(), p.getImage());
					cartDetailService.Save(cartDetail);
					return "redirect:/cart";
				}else {
					int quanity1 = 0 ;
				    quanity1 = listC.get(0).getQuantity() + 1;
					System.out.println(quanity1);
					
					listC.get(0).setCart(cart);
					listC.get(0).setProduct(p);
					listC.get(0).setQuantity(quanity1);
					listC.get(0).setPrice(p.getPrice());
					listC.get(0).setImage(p.getImage());

					cartDetailService.Save(listC.get(0));
					return "redirect:/cart";
				}

		}
		
	}

	

	@PostMapping()
	public String cart(@RequestParam("id") Long id , @RequestParam("quantity") int quantity) {
		Product p = productService.findProductById(id);
		User u = userService.findByUsername(userService.getUsername());
		Cart cart = cartService.getByUser(u);
		CartDetail cartDetail = null;
		if(cart == null) {
			Cart cart1 = new Cart();
			cart1.setUser(u);
			cartService.Save(cart1);
			cartDetail = new CartDetail(cart1, p, quantity, p.getPrice(), p.getImage());
			cartDetailService.Save(cartDetail);
			return "redirect:/cart";
		}else {
			
			List<CartDetail> listC = cartDetailService.getByProduct(p);
				if(listC.isEmpty()) {
					 cartDetail = new CartDetail(cart, p, quantity, p.getPrice(), p.getImage());
					cartDetailService.Save(cartDetail);
					return "redirect:/cart";
				}else {
					int quanity1 = 0 ;
				    quanity1 = listC.get(0).getQuantity() + quantity;
					System.out.println(quanity1);
					
					listC.get(0).setCart(cart);
					listC.get(0).setProduct(p);
					listC.get(0).setQuantity(quanity1);
					listC.get(0).setPrice(p.getPrice());
					listC.get(0).setImage(p.getImage());

					cartDetailService.Save(listC.get(0));
					return "redirect:/cart";
				}

		}

	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Product p = productService.findProductById(id);
		List<CartDetail> listC = cartDetailService.findByProduct(p);
		if (listC.isEmpty()) {
			return  "redirect:/cart"; 
			
		}else {
			cartDetailService.Delete(listC.get(0));
			return  "redirect:/cart"; 
		}
		
	}
	
	@GetMapping("/checkout")
	public String checkout() {
		return "checkout" ;
	}
	
	
	

}
