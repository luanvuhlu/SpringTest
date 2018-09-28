package com.luanvv.springtest.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvv.springtest.entities.Account;
import com.luanvv.springtest.repositories.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {
	private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountDao dao;
	
	@Override
	public Account save(Account account) {
		try {
			return dao.save(account);
		}catch (Exception e) {
			log.error("Save error", e);
			return null;
		}
	}

	@Override
	public List<Account> saveMulti(List<Account> accounts) {
		try {
			List<Account> newAccounts = new ArrayList<>();
			for (int i = 0; i < accounts.size(); i++) {
				if(i == 0) {
					newAccounts.add(dao.save(accounts.get(i)));
					continue;
				}
				throw new RuntimeException();
			}
			return newAccounts;
		}catch (Exception e) {
			log.error("", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<Account> saveMultiThrow(List<Account> accounts) throws Exception {
		List<Account> newAccounts = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			if(i == 0) {
				newAccounts.add(dao.save(accounts.get(i)));
				continue;
			}
			throw new RuntimeException();
		}
		return newAccounts;
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

}
