/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/**
 * CourseNameValidatorFSMTest class checks the FSM validator and has 100% coverage on the isValid method
 */
public class CourseNameValidatorFSMTest {
	/**valid course to test */
	private final String validCourse1 = "CSC226";
	/**valid course to test */
	private final String validCourse2 = "A123";
	/**valid course to test */
	private final String validCourse3 = "CS204";
	/**valid course to test */
	private final String validCourse4 = "BSNS393";
	/**checks initial state test */
	private final String invalidCourse1 = "**)#4";
	/**checks initial letter test */
	private final String invalidCourse2 = "123";
	/**checks that there can't be more than four letters */
	private final String invalidCourse3 = "ABABDS12443";
	/**checks that there can be only one number */
	private final String invalidCourse4 = "ABF4S";
	/**checks that there can't be only two numbers */
	private final String invalidCourse5 = "ABF43S";
	/**checks that there can't be more than three numbers */
	private final String invalidCourse6 = "ABF4435S";
	/**checks that there can't be more than one suffix */
	private final String invalidCourse7 = "ABF323SH";
	/**checks that there can't be any numbers after suffix */
	private final String invalidCourse8 = "ABF323S4";
	
	
	/**
	 * test the is valid fsm method in the CourseNameValidatorFSM class
	 * @throws InvalidTransitionException for an invalid transition case
	 */
	@Test
	public void testIsValid() throws InvalidTransitionException {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		assertTrue(fsm.isValid(validCourse1));
		assertTrue(fsm.isValid(validCourse2));
		assertTrue(fsm.isValid(validCourse3));
		assertTrue(fsm.isValid(validCourse4));
		
		Exception e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid(invalidCourse1));
		assertEquals("Course name can only contain letters and digits.", e.getMessage());
		e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid(invalidCourse2));
		assertEquals("Course name must start with a letter.", e.getMessage());
		e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid(invalidCourse3));
		assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		
		e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid(invalidCourse4));
		assertEquals("Course name must have 3 digits.", e.getMessage());
		e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid(invalidCourse5));
		assertEquals("Course name must have 3 digits.", e.getMessage());
		e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid(invalidCourse6));
		assertEquals("Course name can only have 3 digits.", e.getMessage());
		
		e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid(invalidCourse7));
		assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		e = assertThrows(InvalidTransitionException.class, () -> fsm.isValid(invalidCourse8));
		assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		
	}
}
