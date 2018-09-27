# SpringTest
Sometims, I face some seriously trouble with Spring. So I want to write down some test to know exactly how spring code run

## How to run
Use maven to run
```
git clone https://github.com/luanvuhlu/SpringTest.git
cd SpringTest
mvn test
```
## Test cases list
### com.luanvv.springtest.services.AccountServiceTest
#### testSaveMultiNoRollback
A service maybe throw an exception. However, we will catch these exception to know Is transaction rollbacked.  
Service method like this
```
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
 ```
#### testSaveMultiHasRollback
A little difference, it throw an exception too, but we do not catch.
```
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
```
#### testSaveCannotCatch
Someome does not believe that we cannot catch some exceptions are thrown from repository methodes. Try this:  
A perfectly try-catch service method
```
@Override
	public Account save(Account account) {
		try {
			return dao.save(account);
		}catch (Exception e) {
			log.error("Save error", e);
			return null;
		}
	}
```
but you cannot handle it
```
@Test(expected = JpaSystemException.class)
	public void testSaveCannotCatch() {
		String trashString = IntStream.range(0, 1000_000).mapToObj(String::valueOf).collect(Collectors.joining(""));
		Account account = new Account("luan", trashString);
		service.save(account);
		assertTrue(false);
	}
```
## Authors

* **Luan Vu** - [luanvuhlu](https://github.com/luanvuhlu)
