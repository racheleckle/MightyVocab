package user_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model_classes.User;

class TestConstructor {

	@Test
	void testNullUsername() {
		assertThrows(IllegalArgumentException.class, () -> {
			new User(null, "$!detergent12!$");
		});
	}

	@Test
	void testEmptyUsername() {
		assertThrows(IllegalArgumentException.class, () -> {
			new User("", "$!detergent12!$");
		});
	}

	@Test
	void testNullPassword() {
		assertThrows(IllegalArgumentException.class, () -> {
			new User("Mr Clean", null);
		});
	}

	@Test
	void testEmptyPassword() {
		assertThrows(IllegalArgumentException.class, () -> {
			new User("Mr Clean", "");
		});
	}

	@Test
	void testValidConstructor() {
		User user = new User("Mr Clean", "$!detergent12!$");
		assertEquals("Mr Clean", user.getUsername(), "username is valid");
		assertEquals("$!detergent12!$", user.getPassword(), "password is valid");
	}
}