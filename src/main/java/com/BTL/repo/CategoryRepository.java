package com.BTL.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BTL.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	List<Category> findByNameContaining(String name);
	
}
