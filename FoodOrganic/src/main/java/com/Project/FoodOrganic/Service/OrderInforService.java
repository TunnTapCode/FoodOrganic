package com.Project.FoodOrganic.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.OdersInfor;

import com.Project.FoodOrganic.Repository.OrderInforRepo;
@Service
public class OrderInforService {
	@Autowired
	OrderInforRepo oRepo;

	public void save(OdersInfor o) {
		oRepo.save(o);
	}
}
