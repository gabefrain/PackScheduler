package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Tests the InvalidTransitionException class
 * 
 * @author Gabe Frain
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Tests InvalidTransitionException
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException cite = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", cite.getMessage());
	    InvalidTransitionException ite = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ite.getMessage());
	}
}
