package com.luanvv.springtest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.luanvv.springtest.entities.Account;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountFacadeServiceTest {

	@Autowired
	private AccountFacadeService service;

	@Before
	public void init() {

	}

	@Test
	public void testSaveMulti() {
		assertEquals(3, service.findAll().size());
		try {
			service.saveMulti(Arrays.asList(new Account("luan 3", "vu 3"), new Account("luan 4", "vu 4"),
					new Account("luan 5", "vu 5")));
			assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
		assertEquals(3, service.findAll().size()); // Add no one. Roll back perfectly
	}
}
