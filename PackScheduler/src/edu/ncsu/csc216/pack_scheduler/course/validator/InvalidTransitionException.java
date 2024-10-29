package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * This class defines a invalid transition exception
 * 
 * @author Gabe Frain
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Parameterized constructor that constructs an instance of invalid transition exception
	 * using the message
	 * 
	 * @param message specific message for exception object
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}

	/**
	 * Parameterless constructor that constructs an instance of invalid transition exception
	 * using the default message
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
