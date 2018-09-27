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
public interface AccountFacadeService {
	
	@Transactional
	List<Account> saveMulti(List<Account> accounts);
	
	List<Account> findAll();
}
