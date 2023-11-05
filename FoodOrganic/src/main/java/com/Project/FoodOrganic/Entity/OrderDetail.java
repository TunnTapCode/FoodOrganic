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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderDetail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column
	private Long quatity ;
	@Column
	private double price ;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	private Product product ;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "order_id")
	private Orders order ;

}
