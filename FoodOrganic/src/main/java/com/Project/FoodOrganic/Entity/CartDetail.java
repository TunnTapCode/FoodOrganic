package com.Project.FoodOrganic.Entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartDetail")

public class CartDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartDetailId;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product product;
    @Column
	private int quantity;
    @Column
	private Double price;
    @Column
    private String image ;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public CartDetail( Cart cart, Product product, int quantity, Double price, String image) {
		
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
	}
	public CartDetail() {
		
	}
	@Override
	public String toString() {
		return "CartDetail [cartDetailId=" + cartDetailId + ", cart=" + cart + ", product=" + product + ", quantity="
				+ quantity + ", price=" + price + ", image=" + image + "]";
	}
	
	
	
	

}
