package com.Project.FoodOrganic.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Project.FoodOrganic.Entity.OrderDetail;
import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Repository.OrderdetailRepo;

@Service
public class OrderDetailService {

	
	@Autowired
	OrderdetailRepo orderdetailRepo;
	
	public void save(OrderDetail o) {
		orderdetailRepo.save(o);
	}
	
	public List<OrderDetail> findByOrder(Orders orders){
		return orderdetailRepo.findByOrder(orders);
	}
}
