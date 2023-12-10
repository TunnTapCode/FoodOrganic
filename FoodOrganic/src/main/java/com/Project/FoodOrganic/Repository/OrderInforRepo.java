package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.OdersInfor;
import com.Project.FoodOrganic.Entity.Orders;

import java.util.List;


public interface OrderInforRepo extends JpaRepository<OdersInfor, Long>{

	List<OdersInfor> findByOrder(Orders order);
}
