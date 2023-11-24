package com.Project.FoodOrganic.Controller;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Entity.Cart;
import com.Project.FoodOrganic.Entity.CartDetail;
import com.Project.FoodOrganic.Entity.OdersInfor;
import com.Project.FoodOrganic.Entity.OrderDetail;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.CartDetailService;
import com.Project.FoodOrganic.Service.CartService;
import com.Project.FoodOrganic.Service.OrderDetailService;
import com.Project.FoodOrganic.Service.OrderInforService;
import com.Project.FoodOrganic.Service.OrderService;
import com.Project.FoodOrganic.Service.ProductService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
@RequestMapping("/cart")

public class CartController {

	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	CartDetailService cartDetailService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderInforService orderInforService;
	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping()
	public String showcart(Model model, Authentication auth) {
		if (auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
		}
		User u = userService.findByUsername(auth.getName());
		Cart cart = cartService.getByUser(u);
		List<CartDetail> list = cartDetailService.findByCart(cart);
		model.addAttribute("listP", list);
		return "Cart/cart";

	}

	@PostMapping("/checkout")
	public String checkoutProduct(@RequestParam(name = "productSelect", required = false) List<Long> ListId,
			Authentication auth, Model model) {
		try {
			if (auth != null) {
				User u = userService.findByUsername(auth.getName());
				Cart cart = cartService.getByUser(u);
				Long count = cartDetailService.coutByCart(cart);
				model.addAttribute("count", count);
			}
			if (ListId != null) {
				User u = userService.findByUsername(auth.getName());
				Cart cart = cartService.getByUser(u);
				List<CartDetail> list = cartDetailService.findByCart(cart);
				List<CartDetail> newList = new ArrayList<>();
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < ListId.size(); j++) {
						if (list.get(i).getProduct().getProduct_id() == ListId.get(j)) {
							newList.add(list.get(i));
						}
					}

				}
				OdersInfor oInfor = new OdersInfor();
				double total = 0;
				for (int i = 0; i < newList.size(); i++) {
					total += (newList.get(i).getQuantity() * newList.get(i).getPrice());
				}

				model.addAttribute("oderI", oInfor);
				model.addAttribute("total", total);
				model.addAttribute("list", newList);
			} else {
				throw new Exception();
			}

			return "Cart/checkout";
		} catch (Exception e) {
			return "redirect:/cart";
		}

	}

	@GetMapping("/checkout")
	public String checkout(Authentication auth, Model model) {
		if (auth != null) {
			User u = userService.findByUsername(auth.getName());
			Cart cart = cartService.getByUser(u);
			Long count = cartDetailService.coutByCart(cart);
			model.addAttribute("count", count);
		}
		return "Cart/checkout";
	}

	@PostMapping("/thanh-toan")
	public String thanh_toan(@ModelAttribute OdersInfor odersInfor,
			@RequestParam(name = "Cartid", required = false) List<Long> listId, Authentication auth) {
		try {
			User u = userService.findByUsername(auth.getName());
			Date currentTime = new Date();

			Orders orders = new Orders();
			Cart cart = cartService.getByUser(u);
			List<CartDetail> list = cartDetailService.findByCart(cart);
			List<CartDetail> newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < listId.size(); j++) {
					if (list.get(i).getCartDetailId() == listId.get(j)) {
						newList.add(list.get(i));
					}
				}

			}

			double total = 0;
			for (int i = 0; i < newList.size(); i++) {
				total += (newList.get(i).getQuantity() * newList.get(i).getPrice());
			}
			orders.setOrderDate(currentTime);
			orders.setTotal_amount(total);
			orders.setUser(u);
			orders.setStatus("processing");
			orderService.save(orders);
			odersInfor.setOrder(orders);
			orderInforService.save(odersInfor);

			for (int i = 0; i < newList.size(); i++) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder(orders);
				orderDetail.setPrice(newList.get(i).getPrice());
				orderDetail.setProduct(newList.get(i).getProduct());
				orderDetail.setQuatity(newList.get(i).getQuantity());
				orderDetailService.save(orderDetail);

			}
			
			for(int i = 0; i < list.size(); i++) {
				for (int j = 0; j < newList.size(); j++) {
					if(list.get(i).getCartDetailId() == newList.get(j).getCartDetailId()) {
						cartDetailService.Delete(list.get(i));
					}
				}
			}
			
			
			return "redirect:/home";

		} catch (Exception e) {
			return "redirect:/home";
		}

	}

	@SuppressWarnings("unused")
	@GetMapping("/{id}")
	public String cartHome(@PathVariable("id") Long id,Authentication auth) {

		Product p = productService.findProductById(id);
		User u = userService.findByUsername(auth.getName());
		Cart cart = cartService.getByUser(u);
		CartDetail cartDetail = new CartDetail();
		if (cart == null) {
			Cart cart1 = new Cart();
			cart1.setUser(u);
			cartService.Save(cart1);
			cartDetail.setCart(cart);
			cartDetail.setProduct(p);
			cartDetail.setQuantity(1);
			cartDetail.setImage(p.getImage());
			cartDetail.setPrice(p.getPrice());
			cartDetailService.Save(cartDetail);
			
			return "redirect:/cart";
		} else {
			
			List<CartDetail> listC = cartDetailService.findByCart(cart);    
			if (listC.isEmpty()) {
				cartDetail = new CartDetail(cart, p, 1, p.getPrice(), p.getImage());
				cartDetailService.Save(cartDetail);
				return "redirect:/cart";
			} else {
				int quanity1 = 0;
				for (int i = 0; i < listC.size(); i++) {
					if(listC.get(i).getProduct().getProduct_id() == id) {
						quanity1 = listC.get(i).getQuantity()+ 1;
						listC.get(i).setCart(cart);
						listC.get(i).setProduct(p);
						listC.get(i).setQuantity(quanity1);
						listC.get(i).setPrice(p.getPrice());
						listC.get(i).setImage(p.getImage());
						cartDetailService.Save(listC.get(i));
						break;
					}else {
						cartDetail.setCart(cart);
						cartDetail.setProduct(p);
						cartDetail.setQuantity(1);
						cartDetail.setImage(p.getImage());
						cartDetail.setPrice(p.getPrice());
						cartDetailService.Save(cartDetail);
						break;
					}
				}

				return "redirect:/cart";
			}

		}


	}

	@SuppressWarnings("unused")
	@PostMapping()
	public String cart(@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "quantity", required = false) int quantity,Authentication auth) {
		Product p = productService.findProductById(id);
		User u = userService.findByUsername(auth.getName());
		Cart cart = cartService.getByUser(u);
		CartDetail cartDetail = new CartDetail();
		if (cart == null) {
			Cart cart1 = new Cart();
			cart1.setUser(u);
			cartService.Save(cart1);
			cartDetail.setCart(cart);
			cartDetail.setProduct(p);
			cartDetail.setQuantity(quantity);
			cartDetail.setImage(p.getImage());
			cartDetail.setPrice(p.getPrice());
			cartDetailService.Save(cartDetail);
			
			return "redirect:/cart";
		} else {
			
			List<CartDetail> listC = cartDetailService.findByCart(cart);    
			if (listC.isEmpty()) {
				cartDetail = new CartDetail(cart, p, quantity, p.getPrice(), p.getImage());
				cartDetailService.Save(cartDetail);
				return "redirect:/cart";
			} else {
				int quanity1 = 0;
				for (int i = 0; i < listC.size(); i++) {
					if(listC.get(i).getProduct().getProduct_id() == id) {
						quanity1 = listC.get(i).getQuantity()+ quantity;
						listC.get(i).setCart(cart);
						listC.get(i).setProduct(p);
						listC.get(i).setQuantity(quanity1);
						listC.get(i).setPrice(p.getPrice());
						listC.get(i).setImage(p.getImage());
						cartDetailService.Save(listC.get(i));
						break;
					}else {
						cartDetail.setCart(cart);
						cartDetail.setProduct(p);
						cartDetail.setQuantity(quantity);
						cartDetail.setImage(p.getImage());
						cartDetail.setPrice(p.getPrice());
						cartDetailService.Save(cartDetail);
						break;
					}
				}

				return "redirect:/cart";
			}

		}



	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id,Authentication auth) {
		User u = userService.findByUsername(auth.getName());
		Cart cart = cartService.getByUser(u);
		List<CartDetail> listDetail = cartDetailService.findByCart(cart);
		for (CartDetail c : listDetail) {
			System.out.println(c);
		}
	   System.out.println(listDetail);
		
		if (listDetail.isEmpty()) {
			return "redirect:/cart";

		} else {

			for (int i = 0; i < listDetail.size(); i++) {
				if(listDetail.get(i).getProduct().getProduct_id() == id) {
					cartDetailService.Delete(listDetail.get(i));
					break;
				}
			}
			
			return "redirect:/cart";
		}

	}

}
