package com.Project.FoodOrganic.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
	public class ProductImage {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String imageUrl;
	    
	   
	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product Product ;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getImageUrl() {
			return imageUrl;
		}


		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}


		public Product getProduct() {
			return Product;
		}


		public void setProduct(Product product) {
			Product = product;
		}


		public ProductImage(Long id, String imageUrl, com.Project.FoodOrganic.Entity.Product product) {
			super();
			this.id = id;
			this.imageUrl = imageUrl;
			Product = product;
		}
		public ProductImage() {
			super();
			
		}
	    
	    
	    
	    
	}

