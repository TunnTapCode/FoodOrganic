package com.Project.FoodOrganic.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;



@Entity
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;
	@Column(name = "name" ,nullable = false,length = 255)
	private String name ;
	@Column(name = "description", length = 10000 ,nullable = true)
	private String description ;
	@Column(name = "price",nullable = false)
	private Double price ;
	@Column(name = "quantity",nullable = false)
	private int quantity;
	@Column(name = "image",nullable = false , length = 255)
	private String image;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category ;
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public Product(Long product_id, String name, String description, Double price, int quantity, String image,
			Category category) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.category = category;
	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", description=" + description + ", price="
				+ price + ", quantity=" + quantity + ", image=" + image + ", category=" + category + "]";
	}
	
	
	
	
	
	
	 
	
	

}
