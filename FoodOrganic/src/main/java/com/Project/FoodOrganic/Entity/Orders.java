package com.Project.FoodOrganic.Entity;


import java.util.Date;

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
@Table(name = "Orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;
	@Column(name = "orderDate")
	private Date orderDate;
	@Column(name = "total_amount")
	private double total_amount;
	@Column(name = "status")
	private String status ;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

	public Orders(Long order_id, Date orderDate, double total_amount, String status, User user) {
		super();
		this.order_id = order_id;
		this.orderDate = orderDate;
		this.total_amount = total_amount;
		this.status = status;
		this.user = user;
	}

	public Orders() {
		super();
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", orderDate=" + orderDate + ", total_amount=" + total_amount
				+ ", status=" + status + ", user=" + user + "]";
	}
	

	

}
