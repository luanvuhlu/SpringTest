package com.luanvv.springtest.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.luanvv.springtest.entities.Account;

/**
 * The Interface AccountService.
 * 
 * @author luanv
 */
@Transactional(readOnly = true)
public interface AccountService {
	
	@Transactional
	Account save(Account account);
	
	@Transactional
	List<Account> saveMulti(List<Account> accounts);
	
	@Transactional
	List<Account> saveMultiThrow(List<Account> accounts) throws Exception;
	
	List<Account> findAll();

	@Transactional
	void deleteAll();
}
