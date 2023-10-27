package com.Project.FoodOrganic.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Promotions")
public class Promotions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String code ;
	private int discount ;
	private Date startDate ;
	private Date last_Date ;
	public Promotions(Long id, String code, int discount, Date startDate, Date last_Date) {
		super();
		this.id = id;
		this.code = code;
		this.discount = discount;
		this.startDate = startDate;
		this.last_Date = last_Date;
	}
	public Promotions() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getLast_Date() {
		return last_Date;
	}
	public void setLast_Date(Date last_Date) {
		this.last_Date = last_Date;
	}
	

}
