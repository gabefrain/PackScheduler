package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * A class representing a course name validator
 * 
 * @author Gabe Frain
 */
public class CourseNameValidator {

	/** Valid End State of the FSM */
	private boolean validEndState;
	/** Letter Count */
	private int letterCount;
	/** Digit Count */
	private int digitCount;

	/** The current state of the CourseNameValidator */
	private State state;
	/** Final instance of the Initial State */
	private final State initialState = new InitialState();
	/** Final instance of the Letter State */
	private final State letterState = new LetterState();
	/** Final instance of the Digit State */
	private final State digitState = new DigitState();
	/** Final instance of the Suffix State */
	private final State suffixState = new SuffixState();

	/**
	 * Returns true if the course name is valid, based on a string matching Finite
	 * State Machine.
	 * 
	 * The course name must match the following format: (1-4 letters)(3
	 * digits)(optionally, a 1 letter suffix)
	 * 
	 * @param courseName the name of the course
	 * @return true if the course name is valid, or false if the course name is
	 *         invalid
	 * @throws InvalidTransitionException when the FSM attempts an invalid
	 *                                    transition
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		// Set the state field to be the initial FSM state
		state = initialState;
		// Set the letter count to 0
		letterCount = 0;
		// Set the digit count to 0
		digitCount = 0;
		// Set validEndState to false
		validEndState = false;

		// Create a variable to track the current character index
		int charIndex = 0;

		// Variable to keep track of the current input character being examined
		char c;

		// Iterate through the ID, examining one character at a time
		while (charIndex < courseName.length()) {
			// Set the current character being examined
			c = courseName.charAt(charIndex);

			if (state == initialState) {
				if (Character.isLetter(c)) {
					initialState.onLetter();
				} else if (Character.isDigit(c)) {
					initialState.onDigit();
				} else {
					initialState.onOther();
				}
			} else if (state == letterState) {
				if (Character.isLetter(c)) {
					letterState.onLetter();
				} else if (Character.isDigit(c)) {
					letterState.onDigit();
				} else {
					letterState.onOther();
				}
			} else if (state == digitState) {
				if (Character.isLetter(c)) {
					digitState.onLetter();
				} else if (Character.isDigit(c)) {
					digitState.onDigit();
				} else {
					digitState.onOther();
				}
			} else if (state == suffixState) {
				if (Character.isLetter(c)) {
					suffixState.onLetter();
				} else if (Character.isDigit(c)) {
					suffixState.onDigit();
				} else {
					suffixState.onOther();
				}
			}

			charIndex++;
		}

		return validEndState;
	}

	/**
	 * Abstract for states in the Application State Pattern. All concrete
	 * application states must implement the State interface. The State abstract is
	 * a private abstract of the CourseNameValidator class.
	 * 
	 * @author Gabe Frain
	 */
	public abstract class State {
		/**
		 * Updates the state of the FSM if the name is on a Letter.
		 * 
		 * @throws InvalidTransitionException if any concrete classes throw an
		 *                                    InvalidTransitionException
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Updates the state of the FSM if the name is on a Digit.
		 * 
		 * @throws InvalidTransitionException if any concrete classes throw an
		 *                                    InvalidTransitionException
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Updates the state of the FSM if the name is on other.
		 * 
		 * @throws InvalidTransitionException when on other
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * This class is the initial state of the name validator state pattern. It
	 * represents a name in the initial state.
	 * 
	 * @author Gabe Frain
	 */
	private class InitialState extends State {
		/**
		 * If the character is a letter in the initial state letter count is incremented
		 * and the state is set to the letter state
		 */
		public void onLetter() {
			letterCount++;
			state = letterState;
		}

		/**
		 * If the character is a digit in the initial state an exception is thrown.
		 * 
		 * @throws InvalidTransitionException if character is a digit
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * This class is the letter state of the name validator state pattern. It
	 * represents a name in the letter state.
	 * 
	 * @author Gabe Frain
	 */
	private class LetterState extends State {
		/** Maximum Letter Count */
		private static final int MAX_PREFIX_LETTERS = 4;

		/**
		 * If the character is a letter in the letter state and letter count is less
		 * than 4 letter count is incremented and the state does not change. However if
		 * the letter count is already 4 an exception is thrown.
		 * 
		 * @throws InvalidTransitionException if adding letters when letter count is
		 *                                    already 4
		 */
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				letterCount++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * If the character is a digit in the letter state digit count is incremented
		 * and the state is set to the digit state
		 */
		public void onDigit() {
			digitCount++;
			state = digitState;
		}
	}

	/**
	 * This class is the digit state of the name validator state pattern. It
	 * represents a name in the digit state.
	 * 
	 * @author Gabe Frain
	 */
	private class DigitState extends State {
		/** Course Number Length Count */
		private static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * If the character is a letter in the digit state and digit count does not
		 * equal 3 an InvalidTransitionException is thrown
		 * 
		 * @throws InvalidTransitionException if adding a letter in the digit state and
		 *                                    digit count does not equal 3
		 */
		public void onLetter() throws InvalidTransitionException {
			if (digitCount != COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			} else {
				state = suffixState;
			}
		}

		/**
		 * If the character is a digit in the digit state and digit count is less than 3
		 * digit count is incremented and the state does not change. However if the
		 * letter count is already 3 an exception is thrown.
		 * 
		 * @throws InvalidTransitionException if adding digits when digit count is
		 *                                    already 3
		 */
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				digitCount++;
				if (digitCount == COURSE_NUMBER_LENGTH) {
					validEndState = true;
				}
			} else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}

	/**
	 * This class is the suffix state of the name validator state pattern. It
	 * represents a name in the suffix state.
	 * 
	 * @author Gabe Frain
	 */
	private class SuffixState extends State {

		/**
		 * If the next character is a letter throws InvalidTransitionException
		 * 
		 * @throws InvalidTransitionException character is a letter
		 */
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * If the next character is a digit throws InvalidTransitionException
		 * 
		 * @throws InvalidTransitionException character is a digit
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}
