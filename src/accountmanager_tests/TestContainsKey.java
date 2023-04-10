package accountmanager_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model_classes.AccountManager;
import model_classes.Notecards;
import model_classes.User;

class TestContainsKey {
	
	@Test
	void TestNullKey() {
		AccountManager testManager = new AccountManager();
		
		assertThrows(IllegalArgumentException.class, () -> {
			testManager.containsKey(null);
		});
	}
	
	@Test
	void TestValidKey() {
		AccountManager testManager = new AccountManager();
		User testKey = new User("M", "L");
		Notecards testValue = new Notecards();
		testManager.add(testKey, testValue);
		
		boolean result  = testManager.containsKey(testKey);
	
		assertEquals(true, result);
	}
	
	@Test
	void TestInvalidKey() {
		AccountManager testManager = new AccountManager();
		User testKey = new User("M", "L");
		Notecards testValue = new Notecards();
		testManager.add(testKey, testValue);
		User invalidKey = new User("V", "L");
		
		boolean result  = testManager.containsKey(invalidKey);
	
		assertEquals(false, result);
	}
	
	@Test
	void TestValidKeyWithMultipleEntries() {
		AccountManager testManager = new AccountManager();
		
		User testKey1 = new User("M", "L");
		Notecards testValue1 = new Notecards();
		testManager.add(testKey1, testValue1);
		
		User testKey2 = new User("J", "C");
		Notecards testValue2 = new Notecards();
		testManager.add(testKey2, testValue2);
		
		User testKey3 = new User("R", "E");
		Notecards testValue3 = new Notecards();
		testManager.add(testKey3, testValue3);
		
		
		boolean result  = testManager.containsKey(testKey3);
	
		assertEquals(true, result);
	}
	
	

}
