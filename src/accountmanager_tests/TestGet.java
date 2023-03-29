package accountmanager_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model_classes.AccountManager;
import model_classes.Notecards;
import model_classes.User;

class TestGet {

	@Test
	void testNullKey() {
		AccountManager testManager = new AccountManager();

		assertThrows(IllegalArgumentException.class, () -> {
			testManager.get(null);
		});
	}
	
	@Test
	void testValidKey() {
		AccountManager testManager = new AccountManager();
		User testKey = new User("M", "L");
		Notecards testValue = new Notecards();
		testManager.add(testKey, testValue);
		
		assertEquals(testValue, testManager.get(testKey));
	}
	
	@Test
	void TestInvalidKey() {
		AccountManager testManager = new AccountManager();
		User testKey = new User("M", "L");
		Notecards testValue = new Notecards();
		testManager.add(testKey, testValue);
		User invalidKey = new User("V", "L");
	
		assertEquals(null, testManager.get(invalidKey));
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
	
		assertEquals(testValue1, testManager.get(testKey1));
		assertEquals(testValue2, testManager.get(testKey2));
		assertEquals(testValue3, testManager.get(testKey3));
	}
	
	

}
