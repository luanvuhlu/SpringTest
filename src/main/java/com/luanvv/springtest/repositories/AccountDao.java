package com.luanvv.springtest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvv.springtest.entities.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {
	
}
