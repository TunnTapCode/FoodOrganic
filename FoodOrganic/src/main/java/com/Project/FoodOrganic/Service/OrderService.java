package com.Project.FoodOrganic.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public List<Orders> findAllOrderByStatusAndU(String status,Long id){
		return orderRepo.findAllOrderByStatusAndUser_id(status,id);
	}
	
	public Page<Orders> findAll(Pageable pageable,String status){
		return (Page<Orders>) orderRepo.findAll(pageable,status);
	}

}
