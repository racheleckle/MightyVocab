package notecards_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;

import model_classes.Notecard;
import model_classes.Notecards;

class TestConstructor {

	@Test
	void testDefaultNotecardsConstructor() {
		Notecards notecards = new Notecards();
		List<Notecard> notecardList2 = notecards.getNotecards();
		assertEquals(notecardList2, notecards.getNotecards(), "test constructor");
	}
	
	@Test
	void testNullNameConstructor() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Notecards(null);
		});
	}
	
	@Test
	void testEmptyNameConstructor() {
		assertThrows(IllegalArgumentException.class, () -> {
			 new Notecards("");
		});
	}
  
	@Test
	void testValidNotecardsConstructor() {
		Notecards notecards = new Notecards("Test Notecards");
		List<Notecard> notecardList2 = notecards.getNotecards();
		assertEquals(notecardList2, notecards.getNotecards(), "test constructor");
		assertEquals("Test Notecards", notecards.getName());
	}
}