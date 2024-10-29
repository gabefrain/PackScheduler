/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This interface defines a conflict
 * 
 * @author Gabe Frain
 */
public interface Conflict {

	/**
	 * Checks if a conflict exists
	 * 
	 * @param possibleConflictingActivity is the activity that is conflicting
	 * @throws ConflictException if activity is a conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
