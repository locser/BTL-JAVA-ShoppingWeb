//package com.BTL.entity;
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AccountDetails implements UserDetails{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private Account account;
//
//	public AccountDetails(Account account) {
//		super();
//		this.account = account;
//	}
//	
//	
//
//	public AccountDetails() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return Collections.singleton(new SimpleGrantedAuthority(account.getRole()));
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return account.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return account.getUsername();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
