package com.Project.FoodOrganic.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.OdersInfor;
import com.Project.FoodOrganic.Entity.Orders;
import com.Project.FoodOrganic.Repository.OrderInforRepo;
@Service
public class OrderInforService {
	@Autowired
	OrderInforRepo oRepo;

	public void save(OdersInfor o) {
		oRepo.save(o);
	}
	public List<OdersInfor> findByOrder(Orders orders){
		return oRepo.findByOrder(orders);
	}
}
