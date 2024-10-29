/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the Activity class
 */
class ActivityTest {

	/**
	 * Tests CheckConflict
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330,
				1445);
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1000, 1330);
		Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1345, 1430);
		Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1445, 1600);
		Activity a7 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1000, 1130);
		Activity a8 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1800, 2130);

		// Test non overlapping dates
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));

		// Test one overlapping date with exact same times
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a3));
		assertEquals("Schedule conflict.", e1.getMessage());

		// Test one overlapping date with start before and end on other start
		Exception e2 = assertThrows(ConflictException.class, () -> a1.checkConflict(a4));
		assertEquals("Schedule conflict.", e2.getMessage());

		// Test one overlapping date with start and end between other start and end
		Exception e3 = assertThrows(ConflictException.class, () -> a1.checkConflict(a5));
		assertEquals("Schedule conflict.", e3.getMessage());

		// Test one overlapping date with start on end of other and end after
		Exception e4 = assertThrows(ConflictException.class, () -> a1.checkConflict(a6));
		assertEquals("Schedule conflict.", e4.getMessage());

		// Test one overlapping date with start and end before other start and end
		assertDoesNotThrow(() -> a1.checkConflict(a7));

		// Test one overlapping date with start and end after other start and end
		assertDoesNotThrow(() -> a1.checkConflict(a8));

	}

}
