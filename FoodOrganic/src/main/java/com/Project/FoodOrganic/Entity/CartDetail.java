package com.Project.FoodOrganic.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CartDetail")

public class CartDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartDetailId;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;
	private BigDecimal price;
	public Long getCartDetailId() {
		return cartDetailId;
	}
	public void setCartDetailId(Long cartDetailId) {
		this.cartDetailId = cartDetailId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public CartDetail(Long cartDetailId, Cart cart, Product product, int quantity, BigDecimal price) {
		super();
		this.cartDetailId = cartDetailId;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	public CartDetail() {
		
	}
	
	

}
