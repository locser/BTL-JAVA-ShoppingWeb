//package com.BTL.service.impl;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.BTL.entity.Account;
//import com.BTL.repo.AccountRepository;
//import com.BTL.service.AccountService;
//
//@Service
//public class AccountServiceImpl implements AccountService{
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	
//	
//
//	@Autowired
//	AccountRepository accountRepository;
//	
//	@Override
//	public Account login(String username, String password) {
//		try {
//			Optional<Account> optExist = findById(Integer.parseInt(username));
//			
//			if(optExist.isPresent() && bCryptPasswordEncoder.matches(password, optExist.get().getPassword())) {
//				optExist.get().setPassword("");;
//				return optExist.get();
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public <S extends Account> S save(S entity) {
//		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
//		
//		return accountRepository.save(entity);
//	}
//
//	@Override
//	public <S extends Account> Optional<S> findOne(Example<S> example) {
//		return accountRepository.findOne(example);
//	}
//
//	@Override
//	public List<Account> findAll() {
//		return accountRepository.findAll();
//	}
//
//	@Override
//	public Page<Account> findAll(Pageable pageable) {
//		return accountRepository.findAll(pageable);
//	}
//
//	@Override
//	public List<Account> findAll(Sort sort) {
//		return accountRepository.findAll(sort);
//	}
//
//	@Override
//	public Optional<Account> findById(Integer id) {
//		return accountRepository.findById(id);
//	}
//
//	@Override
//	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
//		return accountRepository.saveAll(entities);
//	}
//
//	@Override
//	public void flush() {
//		accountRepository.flush();
//	}
//
//	@Override
//	public <S extends Account> S saveAndFlush(S entity) {
//		return accountRepository.saveAndFlush(entity);
//	}
//
//
//	@Override
//	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
//		return accountRepository.saveAllAndFlush(entities);
//	}
//
//	@Override
//	public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
//		return accountRepository.findAll(example, pageable);
//	}
//
//	@Override
//	public void deleteInBatch(Iterable<Account> entities) {
//		accountRepository.deleteInBatch(entities);
//	}
//
//	@Override
//	public <S extends Account> long count(Example<S> example) {
//		return accountRepository.count(example);
//	}
//
//	@Override
//	public void deleteAllInBatch(Iterable<Account> entities) {
//		accountRepository.deleteAllInBatch(entities);
//	}
//
//	@Override
//	public long count() {
//		return accountRepository.count();
//	}
//
//	@Override
//	public <S extends Account> boolean exists(Example<S> example) {
//		return accountRepository.exists(example);
//	}
//
//
//	@Override
//	public void delete(Account entity) {
//		accountRepository.delete(entity);
//	}
//
//	@Override
//	public <S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
//		return accountRepository.findBy(example, queryFunction);
//	}
//
//
//	@Override
//	public void deleteAllInBatch() {
//		accountRepository.deleteAllInBatch();
//	}
//
//	@Override
//	public Account getOne(int id) {
//		return accountRepository.getOne(id);
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends Account> entities) {
//		accountRepository.deleteAll(entities);
//	}
//
//	@Override
//	public void deleteAll() {
//		accountRepository.deleteAll();
//	}
//
//	@Override
//	public Account getById(int id) {
//		return accountRepository.getById(id);
//	}
//
//	@Override
//	public <S extends Account> List<S> findAll(Example<S> example) {
//		return accountRepository.findAll(example);
//	}
//
//	@Override
//	public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
//		return accountRepository.findAll(example, sort);
//	}
//
//	@Override
//	public boolean existsById(String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void deleteById(String id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAllByIdInBatch(Iterable<String> ids) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//}