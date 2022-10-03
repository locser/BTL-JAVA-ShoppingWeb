package com.BTL.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BTL.entity.CartItem;



@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

}
