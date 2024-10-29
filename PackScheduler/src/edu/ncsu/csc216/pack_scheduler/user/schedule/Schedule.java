package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Manages a user's course schedule, allowing courses to be added, removed, 
 * and checked for conflicts.
 * 
 * @author Rujul Waval
 */
public class Schedule {
	
	/** List of courses in the schedule. */
	private ArrayList<Course> schedule;

	/** The title of the schedule. */
	private String title;
    
	/**
	 * Constructs an empty schedule with the default title. 
	 */
    public Schedule() {
        this.schedule = new ArrayList<>();
        this.title = "My Schedule";
    }
    
    /**
     * Returns the title of the schedule.
     * @return title of the schedule
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title of the schedule.
     * 
     * @param s the new title of the schedule
     * @throws IllegalArgumentException if the title is null
     */
    public void setTitle(String s) {
        if(s == null) {
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = s;
    }
    
    /**
     * Adds a course to the schedule, ensuring no duplicates or conflicts.
     * 
     * @param course the course to be added to the schedule
     * @return true if the course was successfully added, false otherwise
     * @throws IllegalArgumentException if the course is a duplicate or has a conflict
     * @throws NullPointerException if the course is null
     */
    public boolean addCourseToSchedule(Course course) {
        if (course == null) {
            throw new NullPointerException("Cannot add null course.");
        }

        for (Course c : schedule) {
            if (c.equals(course) || c.isDuplicate(course)) {
                throw new IllegalArgumentException("You are already enrolled in " + course.getName());
            }
        }


        try {
            for (Course c : schedule) {
                c.checkConflict(course);
            }
        } catch (ConflictException e) {
            throw new IllegalArgumentException("The course cannot be added due to a conflict.");
        }


        return schedule.add(course);
    }
    
    /**
     * Removes the specified course from the schedule.
     * 
     * @param course the course to be removed
     * @return true if the course was successfully removed, false otherwise
     */
    public boolean removeCourseFromSchedule(Course course) {
        return schedule.remove(course);
    }
    
    /**
     * Resets the schedule by clearing all courses and resetting the title to "My Schedule".
     */
    public void resetSchedule() {
        schedule.clear();
        this.title = "My Schedule";
    }
    
    /**
     * Returns a 2D array of course details for all courses in the schedule.
     * Each row contains the course name, section, title, and meeting information.
     * 
     * @return a 2D array representing the scheduled courses
     */
    public String[][] getScheduledCourses() {
        String[][] scheduledCourses = new String[schedule.size()][4];
        for (int i = 0; i < schedule.size(); i++) {
            Course c = schedule.get(i);
            scheduledCourses[i][0] = c.getName();
            scheduledCourses[i][1] = c.getSection();
            scheduledCourses[i][2] = c.getTitle();
            scheduledCourses[i][3] = c.getMeetingString();
        }
        return scheduledCourses;
    }
}