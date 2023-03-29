package accountmanager_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model_classes.AccountManager;
import model_classes.Notecards;
import model_classes.User;

class TestRemove {

	@Test
	void testNullKey() {
		AccountManager testManager = new AccountManager();

		assertThrows(IllegalArgumentException.class, () -> {
			testManager.remove(null);
		});
	}
	
	@Test
	void testValidRemove() {
		AccountManager testManager = new AccountManager();
		User testKey = new User("M", "L");
		Notecards testValue = new Notecards();
		testManager.add(testKey, testValue);
		
		assertEquals(1, testManager.size());
		
		boolean result = testManager.remove(testKey);
		assertEquals(0, testManager.size());
		assertEquals(true, result);
	}
	
	@Test
	void testInvalidRemove() {
		AccountManager testManager = new AccountManager();
		User testKey = new User("M", "L");
		Notecards testValue = new Notecards();
		testManager.add(testKey, testValue);
		
		User testKey2 = new User("V", "L");
		
		boolean result = testManager.remove(testKey2);
		
		assertEquals(1, testManager.size());
		assertEquals(false, result);
	}
	
	@Test
	void testValidRemovalWithMultipleEntries() {
		AccountManager testManager = new AccountManager();
		User testUser1 = new User("michael", "lee");
		User testUser2 = new User("bruce", "wayne");
		User testUser3 = new User("clark", "kent");
		Notecards testValue = new Notecards();
		
		testManager.add(testUser1, testValue);
		testManager.add(testUser2, testValue);
		testManager.add(testUser3, testValue);
		assertEquals(3, testManager.size());
		
		boolean result = testManager.remove(testUser2);
		assertEquals(true, result);
		assertEquals(2, testManager.size());
		assertEquals(false, testManager.containsKey(testUser2));
	}

}
