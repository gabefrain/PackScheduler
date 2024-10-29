/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * A class representing a course
 * 
 * @author Gabe Frain
 */
public class Course extends Activity implements Comparable<Course> {

	/** Section length. */
	static final private int SECTION_LENGTH = 3;
	/** Maximum credits. */
	static final private int MAX_CREDITS = 5;
	/** Minimum credits. */
	static final private int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course name validator */
	private CourseNameValidator validator;

	/**
	 * Constructs a Course object with values for all fields
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity ID for Course
	 * @param meetingDays  meetings days for Course as series of chars
	 * @param startTime    start time of Course
	 * @param endTime      end time of Course
	 * 
	 * @throws IllegalArgumentException if any methods called throw an
	 *                                  IllegalArgumentException
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorID,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity ID for Course
	 * @param meetingDays  meetings days for Course as series of chars
	 * 
	 * @throws IllegalArgumentException if any methods called throw an
	 *                                  IllegalArgumentException
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Return's the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set's the Course's name. If the name is null, has a length of less than 5 or
	 * more than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		// Throw exception if name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		validator = new CourseNameValidator();
		try {
			if (!validator.isValid(name)) {
				throw new IllegalArgumentException("Invalid course name.");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Return's the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Set's the Course's section. If the section is null, not three characters, or
	 * any of the characters are not digits, an IllegalArgumentException is thrown.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {
		// Check if section is null or not 3 characters
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		// Check if any of sections characters are not digits
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}

	/**
	 * Return's the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Set's the Course's credits. If the credits are less than the minimum or more
	 * than the maximum an IllegalArgumentException is thrown.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the credits parameter is invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Return's the Course's instructor ID.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Set's the Course's instructor ID. If the instructor id is null or empty than
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructor id is invalid
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Overrides the hash code
	 * 
	 * @return overridden hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Overrides the equals method
	 * 
	 * @return if two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Overrides the getShortDisplayArray
	 * 
	 * @return short display array
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] output = { name, section, getTitle(), getMeetingString() };
		return output;
	}

	/**
	 * Overrides the getLongDisplayArray
	 * 
	 * @return long display array
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] output = { name, section, getTitle(), Integer.toString(credits), instructorId, getMeetingString(),
				"" };
		return output;
	}

	/**
	 * Sets Courses meeting days and times by overriding activity
	 * 
	 * @param meetingDays meeting days of course
	 * @param startTime   start time of course
	 * @param endTime     end time of course
	 * @throws IllegalArgumentException if meetingDays is null or empty
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Checks if meetingDays is null or empty
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		int mCount = 0;
		int tCount = 0;
		int wCount = 0;
		int hCount = 0;
		int fCount = 0;
		int aCount = 0;
		// Counts letters and checks for invalid ones
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) == 'M') {
				mCount++;
			} else if (meetingDays.charAt(i) == 'T') {
				tCount++;
			} else if (meetingDays.charAt(i) == 'W') {
				wCount++;
			} else if (meetingDays.charAt(i) == 'H') {
				hCount++;
			} else if (meetingDays.charAt(i) == 'F') {
				fCount++;
			} else if (meetingDays.charAt(i) == 'A') {
				aCount++;
			} else {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		// Checks if only A
		if (aCount == 1 && (mCount >= 1 || tCount >= 1 || wCount >= 1 || hCount >= 1 || fCount >= 1)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		// Checks if any letter counts are greater than 1
		if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Overrides the isDuplicate method in the activity class
	 * 
	 * @param course is the activity being passed through and checked as a course
	 * @return if there is duplicate
	 * @throws IllegalArgumentException if activity is not a course
	 */
	@Override
	public boolean isDuplicate(Activity course) {
		if (getClass() != course.getClass()) {
			return false;
		}
		Course other = (Course) course;
		if (other instanceof Course) {
			return getName().equals(other.getName());
		}
		throw new IllegalArgumentException("");
	}

	/**
	 * Compares two course objects
	 * 
	 * @param o other object being compared
	 * @return -1 if less, 0 if equal, and 1 if more
	 */
	@Override
	public int compareTo(Course o) {
		if (getName().compareTo(o.getName()) > 0) {
			return 1;
		} else if (getName().compareTo(o.getName()) < 0) {
			return -1;
		} else {
			if (getSection().compareTo(o.getSection()) > 0) {
				return 1;
			} else if (getSection().compareTo(o.getSection()) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
		
	}

}
