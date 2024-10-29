/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This class defines a conflict exception
 * 
 * @author Gabe Frain
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor that constructs an instance of conflict exception
	 * using the message
	 * 
	 * @param message specific message for exception object
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Parameterless constructor that constructs an instance of conflict exception
	 * using the default message
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
