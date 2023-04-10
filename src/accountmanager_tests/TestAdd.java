package accountmanager_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model_classes.AccountManager;
import model_classes.Notecards;
import model_classes.User;

class TestAdd {

	@Test
	void testNullKey() {
		AccountManager testManager = new AccountManager();
		Notecards testValue = new Notecards();
		
		assertThrows(IllegalArgumentException.class, () -> {
			testManager.add(null, testValue);
		});
	}
	
	@Test
	void testNullValue() {
		AccountManager testManager = new AccountManager();
		User testUser = new User("michael", "lee");
		
		assertThrows(IllegalArgumentException.class, () -> {
			testManager.add(testUser, null);
		});
	}
	
	@Test
	void testUserAlreadyExistsInMap() {
		AccountManager testManager = new AccountManager();
		User testUser = new User("michael", "lee");
		Notecards testValue = new Notecards();
		testManager.add(testUser, testValue);
		
		boolean result =  testManager.add(testUser, testValue); // Why won't it pass when creating object in line
		
		assertEquals(1, testManager.size());
		assertEquals(false, result);
	}
	
	@Test
	void testValidAdd() {
		AccountManager testManager = new AccountManager();
		User testUser = new User("michael", "lee");
		Notecards testValue = new Notecards();
		
		boolean result =  testManager.add(testUser, testValue);
		
		assertEquals(1, testManager.size());
		assertEquals(true, result);
	}
	
	@Test
	void testAddMultiple() {
		AccountManager testManager = new AccountManager();
		User testUser1 = new User("michael", "lee");
		User testUser2 = new User("bruce", "wayne");
		User testUser3 = new User("clark", "kent");
		Notecards testValue = new Notecards();
		
		boolean result =  testManager.add(testUser1, testValue);
		assertEquals(true, result);
		int actual = testManager.size();
		assertEquals(1, actual);
		
		result =  testManager.add(testUser2, testValue);
		assertEquals(true, result);
		
		
		result =  testManager.add(testUser3, testValue);
		assertEquals(true, result);
		
		actual = testManager.size();
		assertEquals(3, actual);
	}

}
