package edu.ncsu.csc216.pack_scheduler.course;

/**
 * A class representing an activity
 * 
 * @author Gabe Frain
 */
public abstract class Activity implements Conflict {

	/** Upper hour. */
	private static final int UPPER_HOUR = 24;
	/** Upper minute. */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Initializes an instance of the activity object using the given parameters
	 * 
	 * @param title       title of activity
	 * @param meetingDays meeting days for activities
	 * @param startTime   start time of activities
	 * @param endTime     end time of activities
	 * @throws IllegalArgumentException if the parameters are not able to be
	 *                                  initialized
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Return's the Course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set's the Course's title. If the title is null or empty an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setTitle(String title) {
		// Check if title is null or empty
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Return's the Course's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Return's the Course's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Return's the Course's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Return's the Course's meeting string.
	 * 
	 * @return the meetingString
	 */
	public String getMeetingString() {
		// Checking for arranged
		if ("A".equals(meetingDays)) {
			return "Arranged";
		}

		// Convert to standard time
		int sHour = startTime / 100;
		int sMinute = startTime % 100;
		int eHour = endTime / 100;
		int eMinute = endTime % 100;
		String ssth = "AM";
		if (sHour > 11) {
			ssth = "PM";
		}
		if (sHour > 12) {
			sHour = sHour - 12;
		}
		String esth = "AM";
		if (eHour > 11) {
			esth = "PM";
		}
		if (eHour > 12) {
			eHour = eHour - 12;
		}

		// Formulate string to output
		String output = meetingDays + " " + sHour + ":";
		if (sMinute < 10) {
			output = output + "0" + sMinute;
		} else {
			output = output + sMinute;
		}

		output = output + ssth + "-" + eHour + ":";
		if (eMinute < 10) {
			output = output + "0" + eMinute;
		} else {
			output = output + eMinute;
		}
		output = output + esth;
		return output;
	}

	/**
	 * Set's the Activities meeting days and times. If meeting days is null, empty,
	 * or contains invalid characters, if non-zero start/end times, start time is
	 * invalid, end time is invalid, or if end time is less than start time an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param meetingDays the meetingDays to set
	 * @param startTime   the startTime to set
	 * @param endTime     the endTime to set
	 * @throws IllegalArgumentException if meetingDays, startTime, or endTime is an
	 *                                  invalid parameter
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		} else {
			// Breaking apart startTime and endTime into hours and minutes
			int sHours = startTime / 100;
			int sMinutes = startTime % 100;
			int eHours = endTime / 100;
			int eMinutes = endTime % 100;

			// Checking if startTime and endTime are valid
			if (sHours < 0 || sHours >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (sMinutes < 0 || sMinutes >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (eHours < 0 || eHours >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (eMinutes < 0 || eMinutes >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (sHours > eHours || sHours == eHours && sMinutes > eMinutes) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Gets the short display array
	 * 
	 * @return string array of display
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Gets the long display array
	 * 
	 * @return string array of display
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks if activity is duplicate of the parameter activity
	 * 
	 * @param activity other activity
	 * @return if they are duplicates
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Overrides hash code method
	 * 
	 * @return overridden hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Overrides equals method
	 * 
	 * @return if the two objects share all equal values
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Checks if there is a conflict when adding a new activity, two events are
	 * conflicting if they are on the same day and share any overlapping minutes
	 * 
	 * @param possibleConflictingActivity the activity which is being checked as
	 *                                    conflicting
	 * @throws ConflictException if the add activity conflicts with the preexisting
	 *                           one
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String currentMeetingDays = this.getMeetingDays();
		String newMeetingDays = possibleConflictingActivity.getMeetingDays();
		for (int i = 0; i < currentMeetingDays.length(); i++) {
			if ("A".equals(currentMeetingDays) || "A".equals(newMeetingDays)) {
				break;
			}
			for (int j = 0; j < newMeetingDays.length(); j++) {
				if (currentMeetingDays.charAt(i) == newMeetingDays.charAt(j)) {
					int curS = this.getStartTime();
					int curF = this.getEndTime();
					int newS = possibleConflictingActivity.getStartTime();
					int newF = possibleConflictingActivity.getEndTime();
					if (newS >= curS && newS <= curF) {
						throw new ConflictException();
					}
					if (newF >= curS && newF <= curF) {
						throw new ConflictException();
					}
					if (newS < curS && newF > curF) {
						throw new ConflictException();
					}
				}
			}
		}

	}

}