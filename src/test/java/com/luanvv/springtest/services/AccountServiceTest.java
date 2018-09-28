package com.luanvv.springtest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

import com.luanvv.springtest.entities.Account;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

	@Autowired
	private AccountService service;

	@Before
	public void init() {
		service.save(new Account("luan", "vu"));
	}
	
	@Test(expected = JpaSystemException.class)
	public void testSaveCannotCatch() {
		String trashString = IntStream.range(0, 1000_000).mapToObj(String::valueOf).collect(Collectors.joining(""));
		Account account = new Account("luan", trashString);
		service.save(account);
		assertTrue(false);
	}
	
	@Test
	public void testSaveMultiHasRollback() {
		assertEquals(1, service.findAll().size());
		try {
			service.saveMultiThrow(Arrays.asList(new Account("luan 3", "vu 3"), new Account("luan 4", "vu 4"),
					new Account("luan 5", "vu 5")));
			assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
		assertEquals(1, service.findAll().size()); // Add no one. Roll back perfectly
	}
	
	@Test
	public void testSaveMultiNoRollback() {
		assertEquals(1, service.findAll().size());
		service.saveMulti(Arrays.asList(new Account("luan 3", "vu 3"), new Account("luan 4", "vu 4"),
					new Account("luan 5", "vu 5")));
		assertEquals(2, service.findAll().size()); // One more record -> Not roll back
	}
	
	@Test
	public void testDate() {
		YearMonth yearMonth = YearMonth.of(2018, 9);
		LocalDateTime start = yearMonth.atDay(1).atStartOfDay();
		LocalDateTime end = LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.MAX);
		assertEquals(start, LocalDateTime.of(2018, 9, 1, 0, 0, 0, 0));
		assertEquals(end, LocalDateTime.of(2018, 9, 30, 23, 59, 59, 999999999));
	}
	
	@After
	public void tearDown() {
		service.deleteAll();
	}
}
