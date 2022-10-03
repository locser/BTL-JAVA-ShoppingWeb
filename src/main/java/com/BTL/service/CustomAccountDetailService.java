//package com.BTL.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.BTL.entity.Account;
//import com.BTL.entity.AccountDetails;
//import com.BTL.repo.AccountRepository;
//
//@Service
//public class CustomAccountDetailService implements UserDetailsService{
//	
//	@Autowired
//	private AccountRepository accountRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<Account> opt=  accountRepository.getByUsername(username);
//		Account account =opt.get();
//		if(account == null) {
//			throw new UsernameNotFoundException("User not found");
//		}
//		
//		return new AccountDetails(account);
//	}
//
//}
