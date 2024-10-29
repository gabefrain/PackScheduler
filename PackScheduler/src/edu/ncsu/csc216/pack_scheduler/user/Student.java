package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The purpose of the Student class is to store information about each student.
 * This includes the Student's first name, last name, id, email, password, and
 * the maximum amount of credits the student is taking. This class contains all
 * of the getter and setter methods for the fields listed, and implements
 * comparable to sort the students by last name, then first name, and then unity
 * id. This class can be called by other classes to update or store new Student
 * information
 * 
 * @author Emma McCue
 * @author Rosemary Elliott
 * @author Rujul Waval
 */
public class Student extends User implements Comparable<Student> {
	/** integrating schedule into student */
	private Schedule schedule;
	
	/** maximum number of credits a student can take */
	public static final int MAX_CREDITS = 18;

	/** minimum number of credits a student can take */
	public static final int MIN_CREDITS = 3;

	/** Student's max credits */
	private int maxCredits;

	/**
	 * Default constructor which constructs a Student object by calling the setters
	 * for each of the fields. If any of the fields are invalid (invalid student
	 * records stated in Javadoc of setter method) an IllegalArgumentException is
	 * thrown.
	 * 
	 * @param firstName  string value for first name
	 * @param lastName   string value for last name
	 * @param id         string value for id
	 * @param email      string value for email
	 * @param password   string value for password
	 * @param maxCredits integer value for max credits
	 * @throws IllegalArgumentException if any fields are invalid
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}

	/**
	 * Calls the other constructor with the default max credits value of 18 If any
	 * of the fields are invalid (invalid student records stated in Javadoc of
	 * setter method) an IllegalArgumentException is thrown.
	 * 
	 * @param firstName string value for first name
	 * @param lastName  string value for last name
	 * @param id        string value for id
	 * @param email     string value for email
	 * @param password  string value for password
	 * @throws IllegalArgumentException if any fields are invalid
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, 18);
		schedule = new Schedule();
	}

	/**
	 * returns max credit number
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets Student's credits. If max credits is below 3 or above 18 and
	 * IllegalArgumentException is thrown
	 * 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException for credits less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
			// Check message
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Converts Student to string with a comma separating all values.
	 * 
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCredits;
	}

	/**
	 * Compares students passed on last name, first name, and then unity id
	 * 
	 * @param o item being compared to
	 * @return 0 if equal, 1 if after, -1 if before parameter (item compared)
	 * @throws NullPointerException if student is being compared to a null case
	 */
	@Override
	public int compareTo(Student o) {
		if (o == null) {
			throw new NullPointerException("Cannot compare.");
		}

		int lastNameCompare = this.getLastName().compareTo(o.getLastName());
		int firstNameCompare = this.getFirstName().compareTo(o.getFirstName());

		if (lastNameCompare != 0) {
			return lastNameCompare;
		} else if (firstNameCompare != 0) {
			return firstNameCompare;
		} else {
			return this.getId().compareTo(o.getId());
		}

	}

	/**
	 * Overrides the hash code method in User
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Overrides the equals method in User
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}
	
	/**
	 * Returns the schedule from the student
	 * 
	 * @return schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
}
