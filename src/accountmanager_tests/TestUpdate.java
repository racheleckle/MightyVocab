package accountmanager_tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model_classes.AccountManager;
import model_classes.Notecards;
import model_classes.User;

class TestUpdate {

	@Test
	void testNullKey() {
		AccountManager testManager = new AccountManager();
		Notecards testValue = new Notecards();

		assertThrows(IllegalArgumentException.class, () -> {
			testManager.update(null, testValue);
		});
	}
	
	@Test
	void testNullValue() {
		AccountManager testManager = new AccountManager();
		User testKey = new User("M", "L");
		
		assertThrows(IllegalArgumentException.class, () -> {
			testManager.update(testKey, null);
		});
	}
	
	@Test
	void testValidUpdate() {
		AccountManager testManager = new AccountManager();
		User testKey = new User("M", "L");
		Notecards testValue = new Notecards();
		testManager.add(testKey, testValue);
		
	}

}
