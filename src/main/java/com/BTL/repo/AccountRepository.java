package com.BTL.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.BTL.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	@Query("SELECT e FROM Account e WHERE e.username = :username")
	Optional<Account> getByUsername(@Param("username") String username);
	
	@Query("SELECT e FROM Account e WHERE e.username = :username")
	Account getByUsernameTest(@Param("username") String username);

	@Modifying
	@Query(value = "insert into Accounts (id, username, password, role) VALUES (1009, :username, :password, 'ROLE_CUSTOMER')", nativeQuery = true)
	@Transactional
	void saveAccountUser(@Param("username") String userName, @Param("password") String password);
	
//    @Modifying
//    @Query(value = "insert into Logger (redirect,user_id) VALUES (:insertLink,:id)", nativeQuery = true)
//    @Transactional
//    void logURI(@Param("insertLink") String insertLink, @Param("id") Long id);
}
