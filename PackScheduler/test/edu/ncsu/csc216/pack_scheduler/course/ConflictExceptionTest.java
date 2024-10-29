/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Conflict Exception class
 * 
 * @author Gabe Frain
 */
class ConflictExceptionTest {

	/**
	 * Tests the parameterized constructor for conflict exception
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Tests the parameterless constructor for conflict exception
	 */
	@Test
	public void testConflictException() {
	    ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
