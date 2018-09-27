package com.luanvv.springtest.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvv.springtest.entities.Account;

@Service
public class AccountFacadeServiceImpl implements AccountFacadeService {
	private static final Logger log = LogManager.getLogger(AccountFacadeServiceImpl.class);

	@Autowired
	private AccountService service;
	
	@Override
	public List<Account> saveMulti(List<Account> accounts) {
		return saveAccounts(accounts);
	}
	
	private List<Account> saveAccounts(List<Account> accounts) {
		List<Account> newAccounts = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			if(i == 0) {
				newAccounts.add(service.save(accounts.get(i)));
				continue;
			}
			throw new RuntimeException();
		}
		return newAccounts;
	}

	@Override
	public List<Account> findAll() {
		return service.findAll();
	}

	@Override
	public void save(Account account) {
		service.save(account);
	}

	@Override
	public void deleteAll() {
		service.deleteAll();
	}

}
