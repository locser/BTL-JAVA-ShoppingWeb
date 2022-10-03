package com.BTL.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BTL.entity.OrderDetail;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {

}
