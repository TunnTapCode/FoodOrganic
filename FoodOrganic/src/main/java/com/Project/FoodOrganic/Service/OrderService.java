package com.Project.FoodOrganic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Repository.OrderRepo;

@Service
public class OrderService {
	@Autowired
	OrderRepo orderRepo ;
	
	public void save(Orders o) {
		orderRepo.save(o);
	}
	
	public List<Orders> findAllOrder(){
		return orderRepo.findAll();
	}
	public Optional<Orders> findById(Long id){
		return orderRepo.findById(id);
	}
	public List<Orders> findAllOrderByStatus(String status){
		return orderRepo.findAllOrderByStatus(status);
	}

}
