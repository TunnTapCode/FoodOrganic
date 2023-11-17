package com.Project.FoodOrganic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.FoodOrganic.Entity.OrderDetail;
import com.Project.FoodOrganic.Entity.Orders;

import java.util.List;


public interface OrderdetailRepo extends JpaRepository<OrderDetail, Long>{
    List<OrderDetail> findByOrder(Orders order);
    

}
