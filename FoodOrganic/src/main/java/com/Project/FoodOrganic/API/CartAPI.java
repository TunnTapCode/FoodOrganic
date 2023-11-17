package com.Project.FoodOrganic.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project.FoodOrganic.Entity.CartDetail;
import com.Project.FoodOrganic.Entity.Product;
import com.Project.FoodOrganic.Service.CartDetailService;

import com.Project.FoodOrganic.Service.ProductService;


@RestController
@RequestMapping("/api")
public class CartAPI {
	@Autowired
	ProductService productService ;
	
	@Autowired
	CartDetailService cartDetailService ;
	
	
	@PostMapping("/updateQuantity")
    @ResponseBody
    public ResponseEntity<String> updateQuantity(@RequestParam Long id, @RequestParam int quantity) {
        try {
        	Product p = productService.findProductById(id);
    		List<CartDetail> listC = cartDetailService.findByProduct(p);
            if (!listC.isEmpty()) {
            	
            	listC.get(0).setQuantity(quantity);
               
                cartDetailService.Save(listC.get(0));
                return ResponseEntity.ok("Số lượng đã được cập nhật.");
              }
            
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
        }
    }
	
	@GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete_product(@PathVariable Long id,Model model) {
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
