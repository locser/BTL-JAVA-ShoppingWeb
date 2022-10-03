//package com.BTL.service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
//
//import com.BTL.entity.AccountCustomer;
//
//public interface AccountCustomerService {
//	
//	
//	AccountCustomer login(String usernameCustomer, String passwordCustomer);
//	
//	public <S extends AccountCustomer> S save(S entity);
//
//	public <S extends AccountCustomer> Optional<S> findOne(Example<S> example);
//
//	public List<AccountCustomer> findAll();
//
//	public Page<AccountCustomer> findAll(Pageable pageable);
//
//	public List<AccountCustomer> findAll(Sort sort);
//
//	public List<AccountCustomer> findAllById(Iterable<String> ids);
//
//	public Optional<AccountCustomer> findById(String id);
//
//	public <S extends AccountCustomer> List<S> saveAll(Iterable<S> entities);
//
//	public void flush();
//
//	public <S extends AccountCustomer> S saveAndFlush(S entity);
//
//	public boolean existsById(String id);
//
//	public <S extends AccountCustomer> List<S> saveAllAndFlush(Iterable<S> entities);
//
//	public <S extends AccountCustomer> Page<S> findAll(Example<S> example, Pageable pageable);
//
//	public void deleteInBatch(Iterable<AccountCustomer> entities);
//
//	public <S extends AccountCustomer> long count(Example<S> example);
//
//	public void deleteAllInBatch(Iterable<AccountCustomer> entities);
//
//	public long count();
//	
//	public <S extends AccountCustomer> boolean exists(Example<S> example);
//
//	public void deleteById(String id);
//
//	public void deleteAllByIdInBatch(Iterable<String> ids);
//
//	public void delete(AccountCustomer entity);
//
//	public <S extends AccountCustomer, R> R findBy(Example<S> example,
//			Function<FetchableFluentQuery<S>, R> queryFunction);
//
//	public void deleteAllById(Iterable<? extends String> ids);
//
//	public void deleteAllInBatch();
//
//	public AccountCustomer getOne(String id);
//
//	public void deleteAll(Iterable<? extends AccountCustomer> entities);
//
//	public void deleteAll();
//
//	public AccountCustomer getById(String id);
//
//	public <S extends AccountCustomer> List<S> findAll(Example<S> example);
//
//	public <S extends AccountCustomer> List<S> findAll(Example<S> example, Sort sort);
//	
//}
