package com.Project.FoodOrganic.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Project.FoodOrganic.Entity.Orders;



public interface OrderRepo extends JpaRepository<Orders, Long> {
    
     Optional<Orders> findById(Long id) ;
     
     @Query(value = "SELECT * FROM orders o where o.status = :status  order by o.status asc;" , nativeQuery = true)
 	 List<Orders> findAllOrderByStatus(@Param("status") String status);
     
     @Query(value = "SELECT * FROM orders o where o.status = :status and o.user_id = :id ;" , nativeQuery = true)
 	 List<Orders> findAllOrderByStatusAndUser_id(@Param("status") String status,@Param("id") Long id );
     
     @Query(value = "SELECT * FROM orders o where o.status = :status  order by o.status asc;" , nativeQuery = true)
     Page<Orders> findAll(Pageable pageable,@Param("status") String status) ;

}
