package com.Project.FoodOrganic.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "Status_Product")
public class StatusProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer status_id;
	@Column(name = "status_name" ,nullable = false,length = 255)
	private String status_name ;
	
	
	
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public StatusProduct(int status_id, String status_name) {
		super();
		this.status_id = status_id;
		this.status_name = status_name;
	}
	public StatusProduct() {
		super();
	}
	
	
	

}
